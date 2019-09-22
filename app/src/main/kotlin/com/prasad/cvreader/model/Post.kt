package com.prasad.cvreader.model

data class Post(
        val firstName: String,
        val lastName: String,
        val gender: String,
        val dob: String,
        val email: String,
        val address: Address,
        val phoneNumber: List<PhoneNumber>,
        val summary: String,
        val skills: List<String>,
        val projects: List<Project>
)