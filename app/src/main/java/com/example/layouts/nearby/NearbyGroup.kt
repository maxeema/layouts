package com.example.layouts.nearby

data class NearbyGroup(
    val id: String,
    val name: String,
    val items: List<NearbyItem>
)
