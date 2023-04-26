package com.example.jobs.services

import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.models.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface UserService {
    fun checkIdTokenUser(idToken: String): Boolean
    fun addUser(user: User): User
    fun deleteUser(id: Long)
    fun findUser(): List<User>
    fun findUserById(@PathVariable id: Long): Optional<User>
    fun findUserByIdToken(@PathVariable idToken: String): User?
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User
    fun choiseTask(userId: Long, jobId: Long): User
    fun getUserTask(userId: Long): List<Job>
    fun getUserMaterial(jobId: Long): List<Material>
    fun deleteTask(userId: Long, jobId: Long): User
    fun addEmployerToUser(userId: Long, employer: Employer): User
    fun deleteEmployerFromUser(userId: Long, employerId: Long)
    fun getUserEmployer(userId: Long): List<Employer>
}