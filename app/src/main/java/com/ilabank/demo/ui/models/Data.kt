package com.ilabank.demo.ui.models

/**
 * Data Model class for the json response.
 */
data class Data(
    val data: ArrayList<SubData>
)

data class SubData(
    val image: Int,
    val data: ArrayList<String>
)
