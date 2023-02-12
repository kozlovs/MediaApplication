package ru.kozlovss.mediaapplication.ui

import ru.kozlovss.mediaapplication.dto.Track

interface OnInteractionListener {
    fun onPlay(track: Track)

    fun onPause()

    fun isPlay(): Boolean
}