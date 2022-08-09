package com.kodabove.assessment.ui.events

import com.kodabove.assessment.ui.base.BaseContract
import com.kodabove.assessment.ui.models.Events

class EventsContract {

    interface View : BaseContract.View {
        fun showProgress(show: Boolean)
        fun loadEventsSuccess(message: List<Events>)
        fun loadEventsError(localizedMessage: String?)
    }

    interface Presenter : BaseContract.Presenter<View> {
        fun loadEvents()
    }

}