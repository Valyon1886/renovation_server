package com.example.jobs.models

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.Id
import jakarta.persistence.Table

@Entity
@Table(name="material")
data class Material(
    @Column(nullable = true) var name: String?,
    @Column(nullable = true) var count: Int?,
    @Column(nullable = true) var cost: Int?,
    @Id @GeneratedValue val id: Long? = null
) {
    constructor() : this("", 0, 0) {

    }
}