package com.kodabove.assessment.ui.di.component

import com.kodabove.assessment.ui.di.module.FragmentModule
import com.kodabove.assessment.ui.events.EventsFragment
import com.kodabove.assessment.ui.schedule.ScheduleFragment
import dagger.Component

@Component(modules = [FragmentModule::class])
interface FragmentComponent {

    fun inject(eventsFragment: EventsFragment)

    fun inject(scheduleFragment: ScheduleFragment)

}