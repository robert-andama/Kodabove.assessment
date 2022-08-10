package com.kodabove.assessment.ui

import android.app.Application
import com.kodabove.assessment.BuildConfig
import com.kodabove.assessment.ui.di.component.ApplicationComponent
import com.kodabove.assessment.ui.di.component.DaggerApplicationComponent
import com.kodabove.assessment.ui.di.module.ApplicationModule

class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // add logs ....
        }
    }

    private fun setup() {
        component = DaggerApplicationComponent.builder()
            .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}