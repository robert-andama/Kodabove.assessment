package com.kodabove.assessment.ui.schedule

import com.kodabove.assessment.ui.api.ApiServiceInterface
import com.kodabove.assessment.ui.models.Events
import com.kodabove.assessment.ui.models.Schedules
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class SchedulePresenter : ScheduleContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ScheduleContract.View

    override fun loadSchedules() {

        val subscribe = api.getSchedulesList().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ list: List<Schedules> ->
                view.showProgress(false)
                view.loadScheduleSuccess(list.take(2))
            }, { error ->
                view.showProgress(false)
                view.loadScheduleError(error.localizedMessage)
            })

        subscriptions.add(subscribe)
    }

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ScheduleContract.View) {
        this.view = view
    }

}