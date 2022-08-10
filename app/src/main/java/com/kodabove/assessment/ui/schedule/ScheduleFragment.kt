package com.kodabove.assessment.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodabove.assessment.R
import com.kodabove.assessment.databinding.FragmentScheduleBinding
import com.kodabove.assessment.ui.di.component.DaggerFragmentComponent
import com.kodabove.assessment.ui.di.module.FragmentModule
import com.kodabove.assessment.ui.models.Schedules
import com.kodabove.assessment.ui.schedule.adapters.SchedulesAdapter
import javax.inject.Inject

class ScheduleFragment : Fragment(), ScheduleContract.View, SchedulesAdapter.OnItemClickListener {

    private var _binding: FragmentScheduleBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: ScheduleContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val scheduleComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        scheduleComponent.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentScheduleBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    private fun initView() {
        presenter.loadSchedules()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) binding.progressBar.visibility = View.VISIBLE
        else binding.progressBar.visibility = View.GONE
    }

    override fun loadScheduleSuccess(scheduleList: List<Schedules>) {
        when {
            scheduleList.isEmpty() -> {
                binding.recyclerView.visibility = View.GONE
                binding.emptySchedule.visibility = View.VISIBLE
                binding.emptySchedule.text = getString(R.string.empty_event_notice)
                binding.emptySchedule
                    .setCompoundDrawablesRelativeWithIntrinsicBounds(
                        R.drawable.ic_baseline_event_busy_24,
                        0,
                        0,
                        0
                    )
            }
            else -> {
                val adapter = SchedulesAdapter(requireContext(), scheduleList.toMutableList(), this)
                binding.recyclerView.layoutManager = LinearLayoutManager(activity)
                binding.recyclerView.adapter = adapter

                // TODO:: add swipe to delete logic here
            }
        }
    }

    override fun loadScheduleError(localizedMessage: String?) {
        binding.recyclerView.visibility = View.GONE
        binding.emptySchedule.visibility = View.VISIBLE
        binding.emptySchedule.text = localizedMessage

        // TODO:: handle error states
        // i.e network, crash etd
    }

    override fun itemRemoveClick(Schedules: Schedules) {
        // TODO:: add swipe to delete logic here
    }

    override fun itemDetail(Schedules: Schedules) {
        // TODO:: add item details here
    }
}