package com.kodabove.assessment.ui.di.component

import com.kodabove.assessment.ui.BaseApp
import com.kodabove.assessment.ui.di.module.ApplicationModule
import dagger.Component

@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    fun inject(application: BaseApp)
}