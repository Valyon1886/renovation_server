package com.example.jobs.services

import com.example.jobs.models.User
import com.example.jobs.repository.UserRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class UserServiceImpl(private val userRepository: UserRepository): UserService {
    override fun addUser(user: User): User = userRepository.save(user)

    override fun deleteUser(id: Long) = userRepository.deleteById(id)

    override fun findUser(): List<User> = userRepository.findAll()

    override fun findUserById(id: Long): Optional<User> = userRepository.findById(id)

    override fun updateUser(id: Long, user: User): User {
        return userRepository.findById(id).map{
            it.userName = user.userName
            it.password = user.password
            it.image = user.image
            it.firstName = user.firstName
            it.secondName = user.secondName
            it.lastName = user.lastName
            userRepository.save(it)
        }.orElseThrow { EntityNotFoundException("User not found with id $id") }
    }
}