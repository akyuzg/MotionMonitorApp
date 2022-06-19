package com.akyuzg.rapsodomotiontracker.data.mapper

interface Mapper<T : Any, R : Any> {
    fun toModel(value: T): R
    fun fromModel(value: R): T
}