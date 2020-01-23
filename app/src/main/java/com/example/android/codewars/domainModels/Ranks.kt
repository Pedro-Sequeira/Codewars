package com.example.android.codewars.domainModels

data class Ranks(
    val overall: Overall,
    val languages: Languages
) {
    override fun toString(): String {
        return overall.name
    }
}