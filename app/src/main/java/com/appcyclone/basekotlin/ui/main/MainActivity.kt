package com.appcyclone.basekotlin.ui.main

import android.os.Bundle
import android.widget.Toast
import com.appcyclone.basekotlin.R
import com.appcyclone.basekotlin.data.network.model.PostModel
import com.appcyclone.basekotlin.ui.album.AlbumFragment
import com.appcyclone.basekotlin.ui.base.BaseActivity
import com.appcyclone.basekotlin.utils.SharedPrefUtils
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : BaseActivity(), MainContract.MainView {


    @Inject
    lateinit var presenter: MainPresenterImp

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        getActivityComponent().inject(this)
        presenter.onAttach(this)
        tvClick.setOnClickListener { presenter.getListStories() }
        tvAddFragment.setOnClickListener { addFragment(AlbumFragment(), true, true) }
        getSharePreferences().deviceToken = "Hello"
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onDetach()
    }

    override fun loadListPost(list: List<PostModel>) {
        Toast.makeText(this, list.toString(), Toast.LENGTH_SHORT).show()
    }
}
