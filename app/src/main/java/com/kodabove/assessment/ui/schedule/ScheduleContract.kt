package com.kodabove.assessment.ui.schedule

import com.kodabove.assessment.ui.base.BaseContract

class ScheduleContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadScheduleSuccess(message: String)
        fun loadScheduleError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadSchedules()
    }
}