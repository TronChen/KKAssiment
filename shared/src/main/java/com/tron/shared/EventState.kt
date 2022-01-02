package com.tron.shared

sealed class EventState {
    object LOADING : EventState()
    object SUCCESS : EventState()
    object INITIAL : EventState()
    object ERROR : EventState()
}
