package com.kodabove.assessment.ui.schedule

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodabove.assessment.databinding.FragmentScheduleBinding
import com.kodabove.assessment.ui.models.Schedules
import com.kodabove.assessment.ui.schedule.adapters.SchedulesAdapter

class ScheduleFragment : Fragment(), ScheduleContract.View, SchedulesAdapter.OnItemClickListener {

    private var _binding: FragmentScheduleBinding? = null
    private val presenter by lazy { SchedulePresenter() }
    private val binding get() = _binding!!

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
        if (show) {
            binding.progressBar.visibility = View.VISIBLE
        } else {
            binding.progressBar.visibility = View.GONE
        }
    }

    override fun loadScheduleSuccess(scheduleList: List<Schedules>) {
        println("eventsList :: $scheduleList")
        val adapter = SchedulesAdapter(requireContext(), scheduleList.toMutableList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        // TODO:: add swipe to delete logic here
    }

    override fun loadScheduleError(localizedMessage: String?) {
        println("localizedMessage :: $localizedMessage")
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