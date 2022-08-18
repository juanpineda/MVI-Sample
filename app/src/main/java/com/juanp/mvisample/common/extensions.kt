package com.juanp.mvisample.common

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

fun ViewGroup.inflate(@LayoutRes layoutRes: Int, attachToRoot: Boolean = true): View =
    LayoutInflater.from(context).inflate(layoutRes, this, attachToRoot)

fun RecyclerView.setupAdapter(context: Context) {
    val layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
    this.layoutManager = layoutManager
    val dividerItemDecoration = DividerItemDecoration(
        context,
        layoutManager.orientation
    )
    this.addItemDecoration(dividerItemDecoration)
}
