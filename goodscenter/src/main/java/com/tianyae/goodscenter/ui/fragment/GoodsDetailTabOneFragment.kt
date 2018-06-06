package com.tianyae.goodscenter.ui.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.*
import android.view.animation.AlphaAnimation
import android.view.animation.Animation
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import com.eightbitlab.rxbus.Bus
import com.eightbitlab.rxbus.registerInBus
import com.tianyae.baselibrary.ui.fragment.BaseMvpFragment
import com.tianyae.goodscenter.R
import com.kotlin.base.utils.YuanFenConverter
import com.tianyae.baselibrary.ext.onClick
import com.tianyae.baselibrary.ui.activity.BaseActivity
import com.tianyae.baselibrary.widgets.BannerImageLoader
import com.tianyae.goodscenter.common.GoodsConstant
import com.tianyae.goodscenter.data.protocol.Goods
import com.tianyae.goodscenter.event.AddCartEvent
import com.tianyae.goodscenter.event.GoodsDetailImageEvent
import com.tianyae.goodscenter.event.SkuChangedEvent
import com.tianyae.goodscenter.event.UpdateCartSizeEvent
import com.tianyae.goodscenter.ext.setBackgroundAlpha
import com.tianyae.goodscenter.injection.component.DaggerGoodsComponent
import com.tianyae.goodscenter.injection.module.GoodsModule
import com.tianyae.goodscenter.presenter.GoodsDetailPresenter
import com.tianyae.goodscenter.presenter.view.GoodsDetailView
import com.tianyae.goodscenter.ui.activity.GoodsDetailActivity
import com.tianyae.goodscenter.widgets.GoodsSkuPopView
import com.youth.banner.BannerConfig
import com.youth.banner.Transformer
import kotlinx.android.synthetic.main.fragment_goods_detail_tab_one.*
import org.jetbrains.anko.contentView

class GoodsDetailTabOneFragment : BaseMvpFragment<GoodsDetailPresenter>(), GoodsDetailView {

    private lateinit var mSkuPop: GoodsSkuPopView
    //SKU弹层出场动画
    private lateinit var mAnimationStart: AnimationSet
    //SKU弹层退场动画
    private lateinit var mAnimationEnd: Animation

    private var mCurGoods: Goods? = null

    private lateinit var mRootView: View


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        mRootView = inflater.inflate(R.layout.fragment_goods_detail_tab_one, container, false)
        return mRootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        initAnim()
        initSkuPop()
        loadData()
        initObserve()
    }


    /*
        初始化视图
     */
    private fun initView() {
        mGoodsDetailBanner.setImageLoader(BannerImageLoader())
        mGoodsDetailBanner.setBannerAnimation(Transformer.Default)
        mGoodsDetailBanner.isAutoPlay(false)

        //设置指示器位置（当banner模式中有指示器时）
        mGoodsDetailBanner.setIndicatorGravity(BannerConfig.RIGHT)

        //sku弹层
        mSkuView.onClick {
            mSkuPop.showAtLocation((activity as GoodsDetailActivity).contentView
                    , Gravity.BOTTOM and Gravity.CENTER_HORIZONTAL,
                    0, 0
            )
            (activity as BaseActivity).contentView?.startAnimation(mAnimationStart)

            (activity as GoodsDetailActivity).setBackgroundAlpha(0.5f)

        }
    }

    /*
      初始化缩放动画
   */
    private fun initAnim() {

        mAnimationStart = AnimationSet(true)
        val mScaleAnimationStart = ScaleAnimation(
                1f, 0.95f, 1f, 0.95f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationStart.duration = 300
        mAnimationStart.fillAfter = true

        val mAlphaAnimation = AlphaAnimation(1f, 1.5f)
        mAnimationStart.duration = 300
        mAnimationStart.fillAfter = true
        mAnimationStart.addAnimation(mScaleAnimationStart)
        mAnimationStart.addAnimation(mAlphaAnimation)


        mAnimationEnd = ScaleAnimation(
                0.95f, 1f, 0.95f, 1f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f)
        mAnimationEnd.duration = 300
        mAnimationEnd.fillAfter = true

    }


    /*
        初始化sku弹层
     */
    private fun initSkuPop() {
        mSkuPop = GoodsSkuPopView(activity as Activity)
        mSkuPop.setOnDismissListener {
            (activity as BaseActivity).contentView?.startAnimation(mAnimationEnd)
            (activity as GoodsDetailActivity).setBackgroundAlpha(1f)
        }
    }

    /*
    加载数据
 */
    private fun loadData() {
        mPresenter.getGoodsDetailList(activity?.intent!!.getIntExtra(GoodsConstant.KEY_GOODS_ID, -1))
    }

    /*
        Dagger注册
     */
    override fun injectComponent() {
        DaggerGoodsComponent.builder().activityComponent(mActivityComponent).goodsModule(GoodsModule()).build().inject(this)
        mPresenter.mView = this
    }

    /*
        获取商品详情 回调
     */
    override fun onGetGoodsDetailResult(result: Goods) {
        mCurGoods = result

        mGoodsDetailBanner.setImages(result.goodsBanner.split(","))
        mGoodsDetailBanner.start()

        mGoodsDescTv.text = result.goodsDesc
        mGoodsPriceTv.text = YuanFenConverter.changeF2YWithUnit(result.goodsDefaultPrice)
        mSkuSelectedTv.text = result.goodsDefaultSku

        Bus.send(GoodsDetailImageEvent(result.goodsDetailOne, result.goodsDetailTwo))

        loadPopData(result)

    }

    /*
        加载SKU数据
     */
    fun loadPopData(result: Goods) {
        mSkuPop.setGoodsIcon(result.goodsDefaultIcon)
        mSkuPop.setGoodsCode(result.goodsCode)
        mSkuPop.setGoodsPrice(result.goodsDefaultPrice)

        mSkuPop.setSkuData(result.goodsSku)
    }

    /*
        监听SKU变化及加入购物车事件
     */
    private fun initObserve() {
        Bus.observe<SkuChangedEvent>()
                .subscribe {
                    mSkuSelectedTv.text = mSkuPop.getSelectSku() + GoodsConstant.SKU_SEPARATOR + mSkuPop.getSelectCount() + "件"
                }.registerInBus(this)

        Bus.observe<AddCartEvent>()
                .subscribe {
                    addCart()
                }.registerInBus(this)
    }

    /*
        取消事件监听
     */
    override fun onDestroy() {
        super.onDestroy()
        Bus.unregister(this)
    }

    /*
        加入购物车
     */
    private fun addCart() {
        mCurGoods?.let {
            mPresenter.addCart(it.id,
                    it.goodsDesc,
                    it.goodsDefaultIcon,
                    it.goodsDefaultPrice,
                    mSkuPop.getSelectCount(),
                    mSkuPop.getSelectSku()
            )
        }

    }

    /*
        加入购物车 回调
     */
    override fun onAddCartResult(result: Int) {
        Bus.send(UpdateCartSizeEvent())
    }

}