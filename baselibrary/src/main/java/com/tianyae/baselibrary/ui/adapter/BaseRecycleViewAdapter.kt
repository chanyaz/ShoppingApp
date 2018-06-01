package com.tianyae.baselibrary.ui.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView

abstract class BaseRecycleViewAdapter<T, VH : RecyclerView.ViewHolder>(var mContext: Context) : RecyclerView.Adapter<VH>() {


    var mItemClickListener: OnItemClickListener<T>? = null

    var dataList: MutableList<T> = mutableListOf()

    interface OnItemClickListener<in T> {
        fun onItemClick(item: T, position: Int)
    }

    fun setData(sources: MutableList<T>) {
        dataList = sources
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: VH, position: Int) {
        holder.itemView.setOnClickListener {
            if (mItemClickListener != null) {
                mItemClickListener!!.onItemClick(dataList[position], position)
            }
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }

    fun setOnItemClickListener(listener: OnItemClickListener<T>) {
        this.mItemClickListener = listener
    }
}