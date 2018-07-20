package com.appcyclone.basekotlin.ui.album

import android.support.v7.recyclerview.extensions.ListAdapter
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.appcyclone.basekotlin.R
import com.appcyclone.basekotlin.data.network.model.AlbumModel
import kotlinx.android.synthetic.main.item_album.view.*

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class AlbumAdapter(val onClick: (Int) -> Unit, val onDelete: (Int) -> Unit) : ListAdapter<AlbumModel, AlbumAdapter.AlbumHolder>(AlbumDiff()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumHolder {
        return AlbumHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_album, parent, false))
    }

    override fun onBindViewHolder(holder: AlbumHolder, position: Int) {
        holder.bindData(getItem(position), onClick, onDelete)
    }

    inner class AlbumHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindData(model: AlbumModel, onClick: (Int) -> Unit, onDelete: (Int) -> Unit) {
            itemView.itemAlbum_tvTitle.text = model.title
            itemView.itemAlbum_tvTitle.setOnClickListener { onClick(adapterPosition) }
            itemView.itemAlbum_tvDelete.setOnClickListener { onDelete(adapterPosition) }
        }
    }
}