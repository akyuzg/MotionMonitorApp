package com.akyuzg.rapsodomotiontracker.data.local.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    val name: String,
    val description: String,
    val createdAt: Long,

    @PrimaryKey
    val id: Int? = null
)