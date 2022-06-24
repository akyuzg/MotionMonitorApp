package com.akyuzg.rapsodomotiontracker.domain.utils

import javax.inject.Inject

class RecordFaker @Inject constructor(
    private val randomStringGenerator: RandomStringGenerator
) {

    companion object{
        const val LENGTH = 8
    }

    fun getName():String {
        return randomStringGenerator.generateRandomString(LENGTH)
    }

    fun getDescription():String {
        return randomStringGenerator.generateRandomString(LENGTH) + " " + randomStringGenerator.generateRandomString(LENGTH)
    }

}