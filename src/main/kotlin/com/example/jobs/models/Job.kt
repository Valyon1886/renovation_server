package com.example.jobs.models

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name="job")
data class Job(
    @Column(nullable = false) var name: String,
    @Column(nullable = true) var description: String?,
    @Column(nullable = true) var beginDate: Date?,
    @Column(nullable = true) var endDate: Date?,
    @Column(nullable = true) var type: Int,

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
){
    constructor() : this("", "", null, null, 0, mutableListOf(), mutableListOf(), mutableListOf()) {
    }
}