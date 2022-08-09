package com.kodabove.assessment.ui.video

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.kodabove.assessment.R
import com.kodabove.assessment.ui.events.EventsFragment.Companion.EVENT_VIDEO_URL

class EventVideoFragment : Fragment() {
    private lateinit var eventVideoUrl: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        eventVideoUrl = arguments?.getString(EVENT_VIDEO_URL).toString()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_event_video, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val videoView = view.findViewById<VideoView>(R.id.videoView)
        val uri: Uri = Uri.parse(eventVideoUrl)
        videoView.setVideoURI(uri)
        val mediaController = MediaController(requireContext())
        mediaController.setAnchorView(videoView)
        mediaController.setMediaPlayer(videoView)
        videoView.setMediaController(mediaController)
        videoView.start()
    }

}