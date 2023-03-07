package com.example.jobs.services

import com.example.jobs.models.Employer
import com.example.jobs.repository.EmployerRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*

@Service
class EmployerServiceImpl(private val employerRepository: EmployerRepository): EmployerService {
    override fun addEmployer(employer: Employer): Employer = employerRepository.save(employer)

    override fun deleteEmployer(id: Long) = employerRepository.deleteById(id)

    override fun findEmployer(): List<Employer> = employerRepository.findAll()

    override fun findEmployerById(id: Long): Optional<Employer> = employerRepository.findById(id)

    override fun updateEmployer(id: Long, employer: Employer): Employer {
        return employerRepository.findById(id).map{
            it.firstName = employer.firstName
            it.lastName = employer.lastName
            it.secondName = employer.secondName
            it.post = employer.post
            it.cost = employer.cost
            it.clock = employer.clock
            employerRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Employer not found with id $id") }
    }

}