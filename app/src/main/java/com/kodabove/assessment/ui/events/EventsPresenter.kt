package com.kodabove.assessment.ui.events

import com.kodabove.assessment.ui.api.ApiServiceInterface
import com.kodabove.assessment.ui.models.Events
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class EventsPresenter : EventsContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: EventsContract.View

    override fun loadEvents() {

        val subscribe = api.getEventsList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Events>? ->
                view.showProgress(false)
                view.loadEventsSuccess(list!!.take(15))
            }, { error ->
                view.showProgress(false)
                view.loadEventsError(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: EventsContract.View) {
        this.view = view
    }

}