package com.example.jobs.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="employer")
data class Employer(
    @Column(nullable = false) var firstName: String,
    @Column(nullable = true) var secondName: String,
    @Column(nullable = false) var lastName: String,
    @Column(nullable = false) var post: String,
    @Column(nullable = false) var cost: Int,
    @Column(nullable = false) var clock: Int,
    @Id @GeneratedValue val id: Long? = null
) {
    constructor() : this("", "", "", "", 0,0) {

    }
}