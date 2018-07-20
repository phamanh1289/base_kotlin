package com.appcyclone.basekotlin.dagger.component

import com.appcyclone.basekotlin.dagger.module.ActivityModule
import com.appcyclone.basekotlin.dagger.scope.ActivityScope
import com.appcyclone.basekotlin.ui.album.AlbumFragment
import com.appcyclone.basekotlin.ui.main.MainActivity
import dagger.Subcomponent

/**
 * Created by sunil on 5/30/2017.
 */
@ActivityScope
@Subcomponent(modules = [(ActivityModule::class)])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)

    fun inject(albumFragment: AlbumFragment)
}