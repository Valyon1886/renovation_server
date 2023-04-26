package com.example.jobs.models

import jakarta.persistence.*
import java.time.LocalDate
import java.util.*

@Entity
@Table(name="job")
data class Job(
    @Column(nullable = false) var name: String?,
    @Column(nullable = true) var phone: String?,
    @Column(nullable = true) var address: String?,
    @Column(nullable = true) var image: String?,
    @Column(nullable = true) var description: String?,
    @Column(nullable = true) var beginDate: String?,
    @Column(nullable = true) var endDate: String?,
    @Column(nullable = true) var type: Int,
    @Column(nullable = true) var assigned: Boolean,
    @ManyToMany
    @Column(nullable = true)
    var materials: MutableList<Material>?,
    @ManyToMany
    @Column(nullable = true)
    var employers: MutableList<Employer>?,
    @ManyToMany
    @Column(nullable = true)
    var subTasks: MutableList<Job>?,
    @Id @GeneratedValue val id: Long? = null
) {
    constructor() : this("", "", "", "", "", "", "", 0, false, mutableListOf(), mutableListOf(), mutableListOf()) {
    }
}