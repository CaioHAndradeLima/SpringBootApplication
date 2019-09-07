package com.mechanical.core

import java.util.ArrayList

fun getCoordinates(latitude: Double, longitude: Double, margin: Double): List<Double> {
    val listCoordinates = ArrayList<Double>()

    val longMore = (longitude) + margin
    val longLess = (longitude) - margin

    val latMore = (latitude) + margin
    val latLess = (latitude) - margin

    listCoordinates.add(latMore)
    listCoordinates.add(latLess)
    listCoordinates.add(longMore)
    listCoordinates.add(longLess)

    return listCoordinates
}
