package com.example.jobs.models

import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Column(nullable = true) var idToken: String,
//    @Column(nullable = true) var password: String,
    @Column(nullable = true) var image: String,
    @Column(nullable = false) var firstName: String,
    @Column(nullable = true) var secondName: String,
    @Column(nullable = false) var lastName: String,

    //var completedTasks: List<Job>
    //val documents: Map<Date, String>
    @OneToMany
    @Column(nullable = true)
    var jobs: MutableList<Job>?,
    @OneToMany
    @Column(nullable = true)
    var employers: MutableList<Employer>?,
    @Id @GeneratedValue val id: Long? = null
) {
    constructor() : this("", "", "", "", "", mutableListOf(), mutableListOf()) {

    }
}