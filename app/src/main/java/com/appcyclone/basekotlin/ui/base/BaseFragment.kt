package com.appcyclone.basekotlin.ui.base

import android.support.v4.app.Fragment
import com.appcyclone.basekotlin.dagger.component.ActivityComponent
import com.appcyclone.basekotlin.utils.SharedPrefUtils
import io.realm.Realm

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
open class BaseFragment : Fragment(), BaseView {
    override fun showLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).showLoading()
        }
    }

    override fun hideLoading() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).hideLoading()
        }
    }

    override fun isNetworkConnected(): Boolean {
        return activity is BaseActivity && (activity as BaseActivity).isNetworkConnected()
    }

    override fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).addFragment(fragment, false, isAnimation)
        }
    }

    override fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        if (activity is BaseActivity) {
            (activity as BaseActivity).replaceFragment(fragment, isAddToBackStack, isAnimation)
        }
    }

    fun getActivityComponent(): ActivityComponent {
        return (activity as BaseActivity).getActivityComponent()
    }

    fun getSharePreferences(): SharedPrefUtils {
        return (activity as BaseActivity).getSharePreferences()
    }

    fun clearAllBackStack() {
        if (activity is BaseActivity) {
            (activity as BaseActivity).clearAllBackStack()
        }
    }

}