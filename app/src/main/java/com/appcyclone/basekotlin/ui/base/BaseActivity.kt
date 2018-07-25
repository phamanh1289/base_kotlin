package com.appcyclone.basekotlin.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.appcyclone.basekotlin.R
import com.appcyclone.basekotlin.dagger.component.ActivityComponent
import com.appcyclone.basekotlin.data.local.CustomTransaction
import com.appcyclone.basekotlin.others.dialog.LoadingDialog
import com.appcyclone.basekotlin.ui.main.MainActivity
import com.appcyclone.basekotlin.utils.AppUtils
import com.appcyclone.basekotlin.utils.SharedPrefUtils

/**
 * * Created by Anh Pham on 07/10/2018.     **
 * * Copyright (c) 2018 by AppsCyclone      **
 */
open class BaseActivity : AppCompatActivity(), BaseView {

    private lateinit var mActivityComponent: ActivityComponent
    private lateinit var sharePreferences: SharedPrefUtils
    private var mDialogView: LoadingDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityComponent = BaseApplication.instance.getApplicationComponent().getActivityComponent()
        sharePreferences = SharedPrefUtils(this)
    }

    fun getSharePreferences(): SharedPrefUtils {
        return sharePreferences
    }

    fun getActivityComponent(): ActivityComponent {
        return mActivityComponent
    }

    override fun showLoading() {
        if (isNetworkConnected()) {
            if (mDialogView == null)
                mDialogView = LoadingDialog(this)
            mDialogView?.setCanceledOnTouchOutside(false)
            mDialogView?.show()
        }
    }

    override fun hideLoading() {
        mDialogView?.dismiss()
    }

    override fun isNetworkConnected(): Boolean {
        return AppUtils.getNetwork(this)
    }

    override fun addFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        addReplaceFragment(CustomTransaction(isAnimation = isAnimation), fragment, false, isAddToBackStack)
    }

    override fun replaceFragment(fragment: BaseFragment, isAddToBackStack: Boolean, isAnimation: Boolean) {
        addReplaceFragment(CustomTransaction(isAnimation = isAnimation), fragment, true, isAddToBackStack)
    }

    @SuppressLint("CommitTransaction")
    private fun addReplaceFragment(customTransaction: CustomTransaction, fragment: BaseFragment?, isReplace: Boolean, isAddToBackStack: Boolean) {

        fun getCurrentViewID(view: Int): Int {
            return when {
                view != 0 -> view
                this is MainActivity -> android.R.id.tabcontent
                else -> R.id.frmContainer
            }
        }

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager?.beginTransaction()
        fragmentTransaction?.let { it ->
            if (customTransaction.isAnimation)
                it.setCustomAnimations(R.anim.enter_from_right, R.anim.exit_to_left,
                        R.anim.enter_from_left, R.anim.exit_to_right)
            if (isReplace)
                it.replace(getCurrentViewID(customTransaction.containerViewId), fragment)
            else {
                val currentFragment = supportFragmentManager.findFragmentById(getCurrentViewID(customTransaction.containerViewId))
                currentFragment?.let { current -> it.hide(current) }
                it.add(getCurrentViewID(customTransaction.containerViewId), fragment, fragment!!::class.java.simpleName)
            }
            if (isAddToBackStack)
                it.addToBackStack(fragment!!::class.java.simpleName)
            it.commit()
        }
    }


    fun clearAllBackStack() {
        val fm = supportFragmentManager
        val count = fm?.backStackEntryCount
        count?.let {
            for (i in 0 until it) {
                fm.popBackStack()
            }
        }
    }
}