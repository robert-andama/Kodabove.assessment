package com.kodabove.assessment.ui.schedule

import com.kodabove.assessment.ui.base.BaseContract
import com.kodabove.assessment.ui.models.Events
import com.kodabove.assessment.ui.models.Schedules

class ScheduleContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadScheduleSuccess(scheduleList: List<Schedules>)
        fun loadScheduleError(localizedMessage: String?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadSchedules()
    }
}