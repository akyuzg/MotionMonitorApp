package com.akyuzg.rapsodomotiontracker.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Record(
    val title: String,
    val createdAt: Long,

    @PrimaryKey
    val id: Int? = null
)