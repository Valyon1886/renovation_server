package com.example.jobs.services

import com.example.jobs.Exception.EmployerNotFoundException
import com.example.jobs.Exception.JobNotFoundException
import com.example.jobs.Exception.MaterialNotFoundException
import com.example.jobs.models.Job
import com.example.jobs.repository.EmployerRepository
import com.example.jobs.repository.JobRepository
import com.example.jobs.repository.MaterialRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*
import kotlin.jvm.Throws
import kotlin.math.log


@Service
class JobServiceImpl(
    private val jobRepository: JobRepository,
    private val employerRepository: EmployerRepository,
    private val materialRepository: MaterialRepository,
): JobService{

    override fun addJob(job: Job): Job = jobRepository.save(job)

    override fun addEmployerToJob(jobId: Long, employerId: Long): Job {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val employer = employerRepository.findById(employerId).orElseThrow() { EmployerNotFoundException(employerId) }
        if(!job.employers?.toMutableSet()?.contains(employer)!!)
        {
            job.employers?.add(employer)
        }
        return jobRepository.save(job)
    }

    override fun deleteEmployerFromJob(jobId: Long, employerId: Long): Job{
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val employer = employerRepository.findById(employerId).orElseThrow() { EmployerNotFoundException(employerId) }
        job.employers?.remove(employer)
        return jobRepository.save(job)
    }

    override fun addMaterialToJob(jobId: Long, materialId: Long): Job{
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val material = materialRepository.findById(materialId).orElseThrow() { MaterialNotFoundException(materialId) }
        if(!job.materials?.toMutableSet()?.contains(material)!!)
        {
            job.materials?.add(material)
        }
        return jobRepository.save(job)
    }

    override fun deleteMaterialFromJob(jobId: Long, materialId: Long): Job{
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val material = materialRepository.findById(materialId).orElseThrow() { MaterialNotFoundException(materialId) }
        job.materials?.remove(material)
        return jobRepository.save(job)
    }

    override fun addTaskToJob(jobId: Long, subtaskId: Long): Job {
        if (jobId!=subtaskId) {
            val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
            val subtask = jobRepository.findById(subtaskId).orElseThrow() { JobNotFoundException(jobId) }
            if (!job.subTasks?.toMutableSet()?.contains(subtask)!!) {
                subtask.type = job.type + 1
                job.subTasks?.add(subtask)
            }
            return jobRepository.save(job)
        }
        throw UnsupportedOperationException("not used")
    }

    override fun deleteTaskFromJob(jobId: Long, subtaskId: Long): Job {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val subtask = jobRepository.findById(subtaskId).orElseThrow() { JobNotFoundException(jobId) }
        job.subTasks?.remove(subtask)
        return jobRepository.save(job)
    }

    override fun deleteJob(id: Long) = jobRepository.deleteById(id)

    override fun findJob(type: Int): List<Job> = jobRepository.findByType(type).toList()

    override fun findJobById(@PathVariable id: Long): Optional<Job> = jobRepository.findById(id)

    override fun updateJob(@PathVariable id: Long, @RequestBody job: Job): Job {
        return jobRepository.findById(id).map{
            it.name = job.name
            it.description = job.description
            it.beginDate = job.beginDate
            it.endDate = job.endDate
            it.type = job.type
            it.employers = job.employers
            jobRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Job not found with id $id") }
    }

}