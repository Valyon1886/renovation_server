package com.example.jobs.services

import com.example.jobs.models.Employer
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface EmployerService {
    fun addEmployer(employer: Employer): Employer
    fun deleteEmployer(id: Long)
    fun findEmployer(): List<Employer>
    fun findEmployerById(@PathVariable id: Long): Optional<Employer>
    fun updateEmployer(@PathVariable id: Long, @RequestBody employer: Employer): Employer
}