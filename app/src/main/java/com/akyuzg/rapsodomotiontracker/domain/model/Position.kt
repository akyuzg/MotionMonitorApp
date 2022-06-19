package com.akyuzg.rapsodomotiontracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Position(
    val x: Float,
    val y: Float,
    val z: Float,


    val recordId: Int,

    @PrimaryKey
    val id: Int? = null

)
