package com.sample.testgps

data class LocationX(
    val calling_code: String? = "",
    val capital: String? = "",
    val country_flag: String? = "",
    val country_flag_emoji: String? = "",
    val country_flag_emoji_unicode: String? = "",
    val geoname_id: Int? = 0,
    val is_eu: Boolean? = false,
    val languages: List<Language>? = listOf()
)