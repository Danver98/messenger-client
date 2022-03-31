package com.danver.messenger.data.interfaces

interface IMessage {
    val dataType: String

    fun getData(): Any
}