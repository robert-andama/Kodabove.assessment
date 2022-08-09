package com.kodabove.assessment.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodabove.assessment.databinding.FragmentEventsBinding
import com.kodabove.assessment.ui.events.adapters.EventsAdapter
import com.kodabove.assessment.ui.models.Events

class EventsFragment : Fragment(), EventsContract.View, EventsAdapter.OnItemClickListener {

    private var _binding: FragmentEventsBinding? = null
    private val presenter by lazy { EventsPresenter() }

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentEventsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    private fun initView() {
        presenter.loadEvents()
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

    override fun loadEventsSuccess(eventsList: List<Events>) {
        println("eventsList :: $eventsList")
        val adapter = EventsAdapter(requireContext(), eventsList.toMutableList(), this)
        binding.recyclerView.layoutManager = LinearLayoutManager(activity)
        binding.recyclerView.adapter = adapter

        // TODO:: add swipe to delete logic here
    }

    override fun loadEventsError(localizedMessage: String?) {
        println("localizedMessage :: $localizedMessage")
    }

    override fun itemRemoveClick(events: Events) {
        // TODO:: add swipe to delete logic here
    }

    override fun itemDetail(events: Events) {
        // TODO:: add item details here
    }
}