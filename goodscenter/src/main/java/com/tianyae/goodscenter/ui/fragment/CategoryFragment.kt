package com.tianyae.goodscenter.ui.fragment

import android.content.Context
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tianyae.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.tianyae.baselibrary.ui.fragment.BaseMvpFragment
import com.tianyae.goodscenter.R
import com.tianyae.goodscenter.data.protocol.Category
import com.tianyae.goodscenter.injection.component.DaggerCategoryComponent
import com.tianyae.goodscenter.injection.module.CategoryModule
import com.tianyae.goodscenter.presenter.CategoryPresenter
import com.tianyae.goodscenter.presenter.view.CategoryView
import com.tianyae.goodscenter.ui.adapter.TopCategoryAdapter
import kotlinx.android.synthetic.main.fragment_category.*
import com.kennyc.view.MultiStateView
import com.tianyae.baselibrary.common.ResultCode
import com.tianyae.baselibrary.ext.setVisible
import com.tianyae.baselibrary.ext.startLoading
import com.tianyae.baselibrary.rx.BaseException
import com.tianyae.goodscenter.common.GoodsConstant
import com.tianyae.goodscenter.ui.activity.GoodsActivity
import com.tianyae.goodscenter.ui.adapter.SecondCategoryAdapter
import org.jetbrains.anko.support.v4.startActivity
import org.jetbrains.anko.support.v4.toast

class CategoryFragment : BaseMvpFragment<CategoryPresenter>(), CategoryView {


    lateinit var topAdapter: TopCategoryAdapter

    //二级分类Adapter
    lateinit var secondAdapter: SecondCategoryAdapter


    override fun injectComponent() {
        DaggerCategoryComponent.builder().activityComponent(mActivityComponent).categoryModule(CategoryModule()).build().inject(this)
        mPresenter.mView = this
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        return inflater.inflate(R.layout.fragment_category, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadData()
    }

    private fun initView() {
        mTopCategoryRv.layoutManager = LinearLayoutManager(context)
        topAdapter = TopCategoryAdapter(context as Context)
        mTopCategoryRv.adapter = topAdapter
        //单项点击事件
        topAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                for (category in topAdapter.dataList) {
                    category.isSelected = item.id == category.id
                }
                topAdapter.notifyDataSetChanged()

                loadData(item.id)
            }
        })

        mSecondCategoryRv.layoutManager = GridLayoutManager(context, 3)
        secondAdapter = SecondCategoryAdapter(context as Context)
        mSecondCategoryRv.adapter = secondAdapter
        secondAdapter.setOnItemClickListener(object : BaseRecyclerViewAdapter.OnItemClickListener<Category> {
            override fun onItemClick(item: Category, position: Int) {
                startActivity<GoodsActivity>(GoodsConstant.KEY_CATEGORY_ID to item.id)
            }
        })

    }

    /*
        加载数据
     */
    private fun loadData(parentId: Int = 0) {
        if (parentId != 0) {
            mMultiStateView.startLoading()
        }

        mPresenter.getCategory(parentId)

    }


    override fun onGetCategoryResult(result: MutableList<Category>?) {
        if (result != null && result.size > 0) {
            if (result[0].parentId == 0) {
                result[0].isSelected = true
                topAdapter.setData(result)
                mPresenter.getCategory(result[0].id)
            } else {
                secondAdapter.setData(result)
                mTopCategoryIv.setVisible(true)
                mCategoryTitleTv.setVisible(true)
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_CONTENT
            }
        } else {
            //没有数据
            mTopCategoryIv.setVisible(false)
            mCategoryTitleTv.setVisible(false)
            mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
        }
    }

    override fun onError(exception: BaseException) {
        when (exception.status) {
            ResultCode.EMPTY -> {
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_EMPTY
            }
            ResultCode.NOT_LOGIN -> {
                mMultiStateView.viewState = MultiStateView.VIEW_STATE_ERROR
            }

            ResultCode.NOT_NETWORK -> {
                toast("未找到网络")
            }
        }
    }


}