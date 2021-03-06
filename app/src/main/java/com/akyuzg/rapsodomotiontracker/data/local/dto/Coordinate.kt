package com.akyuzg.rapsodomotiontracker.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Coordinate(
    val x: Float,
    val y: Float,
    val z: Float,


    var recordId: Long? = null,

    @PrimaryKey
    val id: Int? = null

)
