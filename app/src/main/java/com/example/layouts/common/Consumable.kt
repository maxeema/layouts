package com.example.layouts.common

class Consumable<T>(private var data: T?) {

    fun consume() = data?.also { data = null }

}