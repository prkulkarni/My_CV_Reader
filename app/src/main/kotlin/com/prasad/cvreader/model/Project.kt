package com.prasad.cvreader.model

data class Project(
        val name: String,
        val start: String,
        val end: String,
        val type: String,
        val role: String,
        val details: String,
        val environment: String
)