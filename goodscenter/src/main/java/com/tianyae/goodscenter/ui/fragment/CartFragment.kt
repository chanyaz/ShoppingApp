package com.tianyae.goodscenter.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.alibaba.android.arouter.launcher.ARouter
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.kennyc.view.MultiStateView
import com.kotlin.base.utils.AppPrefsUtils
import com.kotlin.base.utils.YuanFenConverter
import com.kotlin.provider.common.ProviderConstant
import com.tianyae.baselibrary.common.ResultCode
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ext.setVisible
import com.tianyae.baselibrary.ext.startLoading
import com.tianyae.baselibrary.rx.BaseException
import com.tianyae.baselibrary.ui.fragment.BaseMvpFragment
import com.tianyae.goodscenter.R
import com.tianyae.goodscenter.common.GoodsConstant
import com.tianyae.goodscenter.data.protocol.CartGoods
import com.tianyae.goodscenter.event.CartAllCheckedEvent
import com.tianyae.goodscenter.event.UpdateCartSizeEvent
import com.tianyae.goodscenter.event.UpdateTotalPriceEvent
import com.tianyae.goodscenter.injection.component.DaggerCartComponent
import com.tianyae.goodscenter.injection.module.CartModule
import com.tianyae.goodscenter.presenter.CartListPresenter
import com.tianyae.goodscenter.presenter.view.CartListView
import com.tianyae.goodscenter.ui.adapter.CartGoodsAdapter
import com.tianyae.provider.router.RouterPath
import kotlinx.android.synthetic.main.fragment_cart.*
import org.jetbrains.anko.support.v4.toast

/*
    购物车 Fragment
 */
class CartFragment : BaseMvpFragment<CartListPresenter>(), CartListView {


    private lateinit var mAdapter: CartGoodsAdapter

    private var mTotalPrice: Long = 0

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerCartComponent.builder().activityComponent(mActivityComponent).cartModule(CartModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        return inflater.inflate(R.layout.fragment_cart, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initObserve()
    }


    override fun onStart() {
        super.onStart()
        loadData()
    }


    private fun initView() {
        mCartGoodsRv.layoutManager = LinearLayoutManager(context)
        mAdapter = CartGoodsAdapter(context as Context)
        mCartGoodsRv.adapter = mAdapter

        mHeaderBar.getRightView().onClick {
            refreshEditStatus()
        }

        //全选按钮事件
        mAllCheckedCb.onClick {
            for (item in mAdapter.dataList) {
                item.isSelected = mAllCheckedCb.isChecked
            }
            mAdapter.notifyDataSetChanged()
            updateTotalPrice()
        }

        //删除按钮事件
        mDeleteBtn.onClick {
            val cartIdList: MutableList<Int> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartIdList) { it.id }
            if (cartIdList.size == 0) {
                toast("请选择需要删除的数据")
            } else {
                mPresenter.deleteCartList(cartIdList)
            }
        }

        //结算按钮事件
        mSettleAccountsBtn.onClick {
            val cartGoodsList: MutableList<CartGoods> = arrayListOf()
            mAdapter.dataList.filter { it.isSelected }
                    .mapTo(cartGoodsList) { it }
            if (cartGoodsList.size == 0) {
                toast("请选择需要提交的数据")
            } else {
                mPresenter.submitCart(cartGoodsList, mTotalPrice)
            }
        }


    }

    /*
        刷新是否为编辑状态
     */
    private fun refreshEditStatus() {
        val isEditStatus = getString(R.string.common_edit) == mHeaderBar.getRightText()
        mTotalPriceTv.setVisible(isEditStatus.not())
        mSettleAccountsBtn.setVisible(isEditStatus.not())
        mDeleteBtn.setVisible(isEditStatus)

        mHeaderBar.getRightView().text = if (isEditStatus) getString(R.string.common_complete) else getString(R.string.common_edit)
    }

    /*
        加载数据
     */
    private fun loadData() {
        mMultiStateView.startLoading()
        mPresenter.getCartList()
    }

    private fun initObserve() {

        Bus.observe<CartAllCheckedEvent>().subscribe {
            run {
                mAllCheckedCb.isChecked = it.isAllChecked
                updateTotalPrice()
            }

        }.registerInBus(this)

        Bus.observe<UpdateTotalPriceEvent>().subscribe {
            updateTotalPrice()
        }.registerInBus(this)

    }


    /*
        获取购物车列表回调
     */
    override fun onGetCartListResult(result: MutableList<CartGoods>?) {
        if (result != null && result.size > 0) {
            mAdapter.setData(result)
            mHeaderBar.getRightView().setVisible(true)
            mAllCheckedCb.isChecked = false
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
        } else {
            mHeaderBar.getRightView().setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }

        //本地存储并发送事件刷新UI
        AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, result?.size ?: 0)
        Bus.send(UpdateCartSizeEvent())
        //更新总价
        updateTotalPrice()

    }

    /*
        删除购物车回调
     */
    override fun onDeleteCartListResult(result: Boolean) {
        toast("删除成功")
        refreshEditStatus()
        loadData()

    }


    /*
        提交购物车回调
     */
    override fun onSubmitCartListResult(result: Int) {
//        ARouter.getInstance().build(RouterPath.OrderCenter.PATH_ORDER_CONFIRM)
//                .withInt(ProviderConstant.KEY_ORDER_ID,result)
//                .navigation()
        toast(result.toString())
    }

    /*
        更新总价
     */
    private fun updateTotalPrice() {
        mTotalPrice = mAdapter.dataList
                .filter { it.isSelected }
                .map { it.goodsCount * it.goodsPrice }
                .sum()
        mTotalPriceTv.text = "合计:${YuanFenConverter.changeF2YWithUnit(mTotalPrice)}"
    }

    /*
        设置Back是否可见
     */
    fun setBackVisible(isVisible: Boolean) {
        mHeaderBar.getLeftView().setVisible(isVisible)
    }

    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }


    override fun onError(exception: BaseException) {
        when (exception.status) {
            ResultCode.EMPTY -> {
                mHeaderBar.getRightView().setVisible(false)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
                //本地存储并发送事件刷新UI
                AppPrefsUtils.putInt(GoodsConstant.SP_CART_SIZE, 0)
                Bus.send(UpdateCartSizeEvent())
                //更新总价
                updateTotalPrice()

            }
            ResultCode.NOT_LOGIN -> {
                mHeaderBar.getRightView().setVisible(false)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
            }

            ResultCode.NOT_NETWORK -> {
                toast("未找到网络")
            }
        }

    }


}