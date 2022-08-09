package com.kodabove.assessment.ui.video

import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.URLUtil
import android.widget.MediaController
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.kodabove.assessment.R
import com.kodabove.assessment.ui.events.EventsFragment.Companion.EVENT_VIDEO_URL


class EventVideoFragment : Fragment() {
    private lateinit var eventVideoUrl: String
    private lateinit var mVideoView: VideoView
    private lateinit var mBufferingTextView: TextView

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
        mVideoView = view.findViewById(R.id.videoView)
        mBufferingTextView = view.findViewById(R.id.buffering_textview);

        if (savedInstanceState != null) mCurrentPosition = savedInstanceState.getInt(PLAYBACK_TIME)

        val mediaController = MediaController(requireContext())
        mediaController.setMediaPlayer(mVideoView)
        mVideoView.setMediaController(mediaController)
    }

    override fun onStart() {
        super.onStart()
        initializePlayer()
    }

    override fun onPause() {
        super.onPause()
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.N) {
            mVideoView.pause()
        }
    }

    override fun onStop() {
        super.onStop()
        releasePlayer()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(PLAYBACK_TIME, mVideoView.currentPosition)
    }

    private fun initializePlayer() {
        mBufferingTextView.visibility = VideoView.VISIBLE
        val videoUri: Uri = getMedia(eventVideoUrl)
        mVideoView.setVideoURI(videoUri)
        mVideoView.setOnPreparedListener {
            mBufferingTextView.visibility = VideoView.INVISIBLE

            if (mCurrentPosition > 0) mVideoView.seekTo(mCurrentPosition)
            else mVideoView.seekTo(1)

            mVideoView.start()
        }

        mVideoView.setOnCompletionListener {
            Toast.makeText(requireContext(),
                "Video Ended",
                Toast.LENGTH_SHORT).show()
            mVideoView.seekTo(0)
        }
    }

    private fun releasePlayer() {
        mVideoView.stopPlayback()
    }

    private fun getMedia(mediaName: String): Uri {
        // TODO Add default sample video on else
        return when {
            URLUtil.isValidUrl(mediaName) -> Uri.parse(mediaName)
            else -> Uri.parse("android.resource://${requireActivity().packageName}/raw/$mediaName")
        }
    }

    companion object{
        const val PLAYBACK_TIME = "play_time"
        private var mCurrentPosition = 0
    }

}