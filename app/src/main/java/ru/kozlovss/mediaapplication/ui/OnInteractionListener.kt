package ru.kozlovss.mediaapplication.ui

import ru.kozlovss.mediaapplication.dto.Track

interface OnInteractionListener {
    fun switch(track: Track)
}