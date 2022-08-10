package com.kodabove.assessment.ui.di.module

import android.app.Application
import com.kodabove.assessment.ui.BaseApp
import com.kodabove.assessment.ui.di.scope.PerApplication
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class ApplicationModule(private val baseApp: BaseApp) {

    @Provides
    @Singleton
    @PerApplication
    fun provideApplication(): Application {
        return baseApp
    }
}