package com.kodabove.assessment.ui.di.module

import com.kodabove.assessment.ui.api.ApiServiceInterface
import com.kodabove.assessment.ui.events.EventsContract
import com.kodabove.assessment.ui.events.EventsPresenter
import com.kodabove.assessment.ui.schedule.ScheduleContract
import com.kodabove.assessment.ui.schedule.SchedulePresenter
import dagger.Module
import dagger.Provides

@Module
class FragmentModule {

    @Provides
    fun provideEventPresenter(): EventsContract.Presenter {
        return EventsPresenter()
    }

    @Provides
    fun provideSchedulePresenter(): ScheduleContract.Presenter {
        return SchedulePresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}