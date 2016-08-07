package com.wacode.yuki.annictforandroid.UI.Main.RecyclerView

import android.content.Context
import android.support.v7.recyclerview.R
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import butterknife.bindView
import com.wacode.yuki.annictapp.Entities.Work
import com.wacode.yuki.annictapp.Entities.Works
import java.util.*

/**
 * Created by yuki on 2016/07/25.
 */
class CardRecyclerAdapter(context: Context, private val resource: Int, private val works: ArrayList<Work>,userWorks: Works) : RecyclerView.Adapter<CardRecyclerAdapter.ViewHolder>() {
    private val layoutInflater: LayoutInflater

    init {
        layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
    }

    override fun getItemCount() = works.size

    override fun onCreateViewHolder(p0: ViewGroup?, p1: Int) = ViewHolder(layoutInflater.inflate(resource, p0, false))

    inner class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
    }
}