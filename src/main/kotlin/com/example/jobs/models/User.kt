package com.example.jobs.models

import jakarta.persistence.*

@Entity
@Table(name="users")
data class User(
    @Column var idToken: String?,
//    @Column(nullable = true) var password: String,
    @Column var firstName: String?,
    @Column var secondName: String?,
    @Column var lastName: String?,
    @Column var imgSrc: String?,
    @OneToMany
    @Column
    var completedTasks: MutableList<Job>?,
    //val documents: Map<Date, String>
    @OneToMany
    @Column
    var jobs: MutableList<Job>?,
    @OneToMany
    @Column
    var employers: MutableList<Employer>?,
    @Id @GeneratedValue val id: Long? = null
) {
    constructor() : this("", "", "", "", "", mutableListOf(), mutableListOf(), mutableListOf()) {

    }
}