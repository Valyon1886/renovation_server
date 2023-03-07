package com.example.jobs.services

import com.example.jobs.models.Job
import com.example.jobs.models.Material
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface JobService {
    fun addJob(job: Job): Job
    fun addEmployerToJob(jobId: Long, employerId: Long): Job
    fun deleteEmployerFromJob(jobId: Long, employerId: Long): Job
    fun addMaterialToJob(jobId: Long, materialId: Long): Job
    fun deleteMaterialFromJob(jobId: Long, materialId: Long): Job
    fun addTaskToJob(jobId: Long, subtaskId: Long): Job
    fun deleteTaskFromJob(jobId: Long, subtaskId: Long): Job
    fun deleteJob(id: Long)
    fun findJob(type: Int): List<Job>
    fun findJobById(@PathVariable id: Long): Optional<Job>
    fun updateJob(@PathVariable id: Long, @RequestBody job: Job): Job
}