package com.tianyae.goodscenter.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.tianyae.baselibrary.ui.adapter.BaseRecyclerViewAdapter
import com.tianyae.goodscenter.R
import kotlinx.android.synthetic.main.layout_search_history_item.view.*


class SearchHistoryAdapter(context: Context) : BaseRecyclerViewAdapter<String, SearchHistoryAdapter.ViewHolder>(context) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(mContext)
                .inflate(R.layout.layout_search_history_item, parent, false)

        return ViewHolder(view)
    }


    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        super.onBindViewHolder(holder, position)
        val model = dataList[position]
        holder.itemView.mSearchHistoryTv.text = model

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view)
}