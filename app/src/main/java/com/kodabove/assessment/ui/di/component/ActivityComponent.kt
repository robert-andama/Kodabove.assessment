package com.kodabove.assessment.ui.di.component

import com.kodabove.assessment.ui.di.module.ActivityModule
import com.kodabove.assessment.ui.main.MainActivity
import dagger.Component

@Component(modules = [ActivityModule::class])
interface ActivityComponent {
    fun inject(mainActivity: MainActivity)
}