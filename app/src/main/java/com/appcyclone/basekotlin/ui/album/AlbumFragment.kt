package com.appcyclone.basekotlin.ui.album

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.appcyclone.basekotlin.R
import com.appcyclone.basekotlin.data.network.model.AlbumModel
import com.appcyclone.basekotlin.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_album.*
import javax.inject.Inject

/**
 * * Created by Anh Pham on 07/19/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
class AlbumFragment : BaseFragment(), AlbumContract.AlbumView {

    @Inject
    lateinit var presenter: AlbumPresenter
    private val adapterAlbum = AlbumAdapter(
            onClick = { it ->
                Toast.makeText(context, mData[it].title, Toast.LENGTH_SHORT).show()
            },
            onDelete = {
                onDelete(it)
            })
    private var mData: MutableList<AlbumModel> = ArrayList()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_album, container, false)
    }

    fun onDelete(index: Int) {
        mData.removeAt(index)
       adapterAlbum.notifyItemRemoved(index)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        init()
    }

    fun init() {
        rvAlbum.apply {
            adapter = adapterAlbum
            layoutManager = LinearLayoutManager(context)
            setHasFixedSize(true)
        }
        presenter.getAllAlbum()
        showLoading()
        Toast.makeText(context, getSharePreferences().deviceToken, Toast.LENGTH_SHORT).show()

    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.onDetach()
    }

    override fun loadAllAlbum(list: MutableList<AlbumModel>) {
        mData = list
        adapterAlbum.submitList(mData)
        hideLoading()
    }
}