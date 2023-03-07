package com.example.jobs.repository

import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.models.User
import org.springframework.data.jpa.repository.JpaRepository


interface EmployerRepository: JpaRepository<Employer, Long> {
}

interface JobRepository: JpaRepository<Job, Long> {
    fun findByType(type: Int): List<Job>
}

interface MaterialRepository: JpaRepository<Material, Long> {
}

interface UserRepository:JpaRepository<User, Long> {
}