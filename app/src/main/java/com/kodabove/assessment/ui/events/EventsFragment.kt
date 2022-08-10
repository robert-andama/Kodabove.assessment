package com.kodabove.assessment.ui.events

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.kodabove.assessment.R
import com.kodabove.assessment.databinding.FragmentEventsBinding
import com.kodabove.assessment.ui.di.component.DaggerFragmentComponent
import com.kodabove.assessment.ui.di.module.FragmentModule
import com.kodabove.assessment.ui.events.adapters.EventsAdapter
import com.kodabove.assessment.ui.models.Events
import javax.inject.Inject

class EventsFragment : Fragment(), EventsContract.View, EventsAdapter.OnItemClickListener {

    private var _binding: FragmentEventsBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var presenter: EventsContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val eventComponent = DaggerFragmentComponent.builder()
            .fragmentModule(FragmentModule())
            .build()

        eventComponent.inject(this)
    }

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
        when {
            show -> binding.progressBar.visibility = View.VISIBLE
            else -> binding.progressBar.visibility = View.GONE
        }
    }

    override fun loadEventsSuccess(eventsList: List<Events>) {
        if (eventsList.isEmpty()) {
            binding.recyclerView.visibility = View.GONE
            binding.emptyEvent.visibility = View.VISIBLE
            binding.emptyEvent.text = getString(R.string.empty_event_notice)
            binding.emptyEvent
                .setCompoundDrawablesRelativeWithIntrinsicBounds(
                    R.drawable.ic_baseline_event_busy_24,
                    0,
                    0,
                    0
                )
        } else {
            val adapter = EventsAdapter(requireContext(), eventsList.toMutableList(), this)
            binding.recyclerView.layoutManager = LinearLayoutManager(activity)
            binding.recyclerView.adapter = adapter

            // TODO:: add swipe to delete logic here
        }
    }

    override fun loadEventsError(localizedMessage: String?) {
        binding.recyclerView.visibility = View.GONE
        binding.emptyEvent.visibility = View.VISIBLE
        binding.emptyEvent.text = localizedMessage
    }

    override fun itemRemoveClick(events: Events) {
        // TODO:: add swipe to delete logic here
    }

    override fun itemDetail(events: Events) {
        val bundle = bundleOf(EVENT_VIDEO_URL to events.videoUrl)
        findNavController().navigate(R.id.navigation_video, bundle)
    }

    companion object {
        const val EVENT_VIDEO_URL = "my_video_url"
    }
}