package com.example.jobs.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="users")
data class User(
    @Column(nullable = false) var userName: String,
    @Column(nullable = false) var password: String,
    @Column(nullable = true) var image: String,
    @Column(nullable = false) var firstName: String,
    @Column(nullable = true) var secondName: String,
    @Column(nullable = false) var lastName: String,
    @Id @GeneratedValue val id: Long? = null,
    //var completedTasks: List<Job>

    //val documents: Map<Date, String>
) {
    constructor() : this("", "", "", "", "", "") {

    }
}