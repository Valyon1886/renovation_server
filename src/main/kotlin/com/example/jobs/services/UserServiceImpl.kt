package com.example.jobs.services

import com.example.jobs.Exception.EmployerNotFoundException
import com.example.jobs.Exception.JobNotFoundException
import com.example.jobs.Exception.UserNotFoundException
import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.models.User
import com.example.jobs.repository.EmployerRepository
import com.example.jobs.repository.JobRepository
import com.example.jobs.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl(
    private val userRepository: UserRepository,
    private val jobRepository: JobRepository,
    private val employerRepository: EmployerRepository
): UserService {

    override fun checkIdTokenUser(idToken: String): Boolean {
        return userRepository.findByIdToken(idToken) != null
    }

    override fun addUser(user: User): User = userRepository.save(user)

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

    override fun findUser(): List<User> = userRepository.findAll()

    override fun findUserById(id: Long): Optional<User> = userRepository.findById(id)

    override fun findUserByIdToken(idToken: String): User? {
        return userRepository.findByIdToken(idToken)
    }

    override fun updateUser(id: Long, user: User): User {
        return userRepository.findById(id).map{
            it.idToken = user.idToken
//            it.password = user.password
//            it.image = user.image
            it.firstName = user.firstName
            it.secondName = user.secondName
            it.lastName = user.lastName
            it.imgSrc = user.imgSrc
            userRepository.save(it)
        }.orElseThrow { EntityNotFoundException("User not found with id $id") }
    }

    override fun choiseTask(userId: Long, jobId: Long): User {
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        if(!user.jobs?.toMutableSet()?.contains(job)!!)
        {
            user.jobs?.add(job)
        }
        job.assigned = true
        jobRepository.save(job)
        return userRepository.save(user)
    }

    override fun getUserTask(userId: Long): List<Job> {
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        return user.jobs.orEmpty()
    }

    override fun deleteTask(userId: Long, jobId: Long): User {
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        val job = jobRepository.findById(jobId).orElseThrow() { EmployerNotFoundException(jobId) }
        user.jobs?.remove(job)
        jobRepository.delete(job)
        return userRepository.save(user)
    }

    override fun getUserMaterial(jobId: Long): List<Material> = jobRepository.findById(jobId).orElseThrow() {JobNotFoundException(jobId)} .materials.orEmpty()

    override fun addEmployerToUser(userId: Long, employer: Employer): User {
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        employerRepository.save(employer)
        if(!user.employers?.toMutableSet()?.contains(employer)!!)
        {
            user.employers?.add(employer)
        }
        return userRepository.save(user)
    }

    override fun deleteEmployerFromUser(userId: Long, employerId: Long){
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        val employer = employerRepository.findById(employerId).orElseThrow() { EmployerNotFoundException(employerId) }
        user.employers?.remove(employer)
        employerRepository.delete(employer)
        userRepository.save(user)
    }

    override fun getUserEmployer(userId: Long): List<Employer> {
        val user = userRepository.findById(userId).orElseThrow() { UserNotFoundException(userId) }
        return user.employers.orEmpty()
    }
}