package com.sample.testgps

data class LocationResponse(
    val city: String? = "",
    val continent_code: String? = "",
    val continent_name: String? = "",
    val country_code: String? = "",
    val country_name: String? = "",
    val ip: String? = "",
    val latitude: Double? = 0.0,
    val location: LocationX? = LocationX(),
    val longitude: Double? = 0.0,
    val region_code: String? = "",
    val region_name: String? = "",
    val type: String? = "",
    val zip: Any? = Any()
)