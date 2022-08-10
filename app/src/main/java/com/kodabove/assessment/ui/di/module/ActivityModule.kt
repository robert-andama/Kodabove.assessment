package com.kodabove.assessment.ui.di.module

import android.app.Activity
import com.kodabove.assessment.ui.main.MainContract
import com.kodabove.assessment.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}