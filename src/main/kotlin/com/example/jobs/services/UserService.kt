package com.example.jobs.services

import com.example.jobs.models.User
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface UserService {
    fun addUser(user: User): User
    fun deleteUser(id: Long)
    fun findUser(): List<User>
    fun findUserById(@PathVariable id: Long): Optional<User>
    fun updateUser(@PathVariable id: Long, @RequestBody user: User): User
}