package com.kodabove.assessment.ui.events

import com.kodabove.assessment.ui.base.BaseContract

class EventsContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadEventsSuccess(message: String)
        fun loadEventsError()
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadEvents()
    }

}