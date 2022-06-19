package com.akyuzg.rapsodomotiontracker.domain.utils

object RecordHelper{
    private fun generateRandomString(len: Int = 15): String{
        val alphanumerics = CharArray(26) { it -> (it + 97).toChar() }.toSet()
            .union(CharArray(9) { it -> (it + 48).toChar() }.toSet())
        return (0..len-1).map {
            alphanumerics.toList().random()
        }.joinToString("")
    }

    fun getRandomName(): String {
        return generateRandomString(5)
    }

    fun getRandomDescription(): String {
        return generateRandomString(5) + " " + generateRandomString(5)
    }

}
