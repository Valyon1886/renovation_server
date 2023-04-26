package com.example.jobs.services

import com.example.jobs.Exception.EmployerNotFoundException
import com.example.jobs.Exception.JobNotFoundException
import com.example.jobs.Exception.MaterialNotFoundException
import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.repository.EmployerRepository
import com.example.jobs.repository.JobRepository
import com.example.jobs.repository.MaterialRepository
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.util.*
import kotlin.jvm.Throws
import kotlin.math.log


@Service
class JobServiceImpl(
    private val jobRepository: JobRepository,
    private val employerRepository: EmployerRepository,
    private val materialRepository: MaterialRepository,
): JobService{

    override fun addJob(job: Job): Job {
        return jobRepository.save(job)
    }

    override fun addSubTask(subTask: Job, jobId: Long): Job {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        subTask.type = job.type + 1
        subTask.materials?.forEach {material ->  materialRepository.save(material)}
        jobRepository.save(subTask)
        job.subTasks?.add(subTask)
        jobRepository.save(job)
        return subTask
    }

    override fun deleteSubTask(subTaskId: Long, jobId: Long) {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val subTask = jobRepository.findById(subTaskId).orElseThrow() { JobNotFoundException(subTaskId) }
        job.subTasks?.remove(subTask)
        jobRepository.delete(subTask)
        jobRepository.save(job)
    }


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

    override fun findSubTask(jobId: Long): List<Job> {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        return job.subTasks.orEmpty()
    }

    override fun deleteTaskFromJob(jobId: Long, subtaskId: Long): Job {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val subtask = jobRepository.findById(subtaskId).orElseThrow() { JobNotFoundException(jobId) }
        job.subTasks?.remove(subtask)
        return jobRepository.save(job)
    }

    override fun deleteJob(id: Long) = jobRepository.deleteById(id)

    override fun findJob(): List<Job> = jobRepository.findByTypeAndAssigned(0, false)

    override fun findJobById(@PathVariable id: Long): Optional<Job> = jobRepository.findById(id)

//    override fun updateJob(id: Long, jobUpdate: Map<String, Any>): Job {
//        val job = jobRepository.findById(id).orElseThrow() { JobNotFoundException(id) }
//        jobUpdate.forEach { (nameUpdate, valueUpdate) ->
//            when(nameUpdate){
//                "name" -> job.name = valueUpdate as String
//                "description" -> job.description = valueUpdate as String
//                "beginDate" -> job.beginDate = Date(valueUpdate as Long)
//                "endDate" -> job.endDate = Date(valueUpdate as Long)
//                "employers" -> job.employers = valueUpdate as MutableList<Employer>
//                "materials" -> job.materials = valueUpdate as MutableList<Material>
//                else -> throw IllegalArgumentException("Invalid field name: $nameUpdate")
//            }
//        }
//        return jobRepository.save(job)
//    }

    override fun updateJob(@PathVariable id: Long, @RequestBody jobUpdate: Job): Job{
        val job = jobRepository.findById(id).orElseThrow() {JobNotFoundException(id)}
        jobUpdate.name?.let { job.name = it }
        jobUpdate.phone?.let { job.phone = it }
        jobUpdate.address?.let { job.address = it }
        jobUpdate.image?.let { job.image = it }
        jobUpdate.description?.let { job.description = it }
        jobUpdate.beginDate?.let { job.beginDate = it }
        jobUpdate.endDate?.let { job.endDate = it }
        jobUpdate.employers?.let { job.employers = it }
        jobUpdate.materials?.let { job.materials = it }

        return jobRepository.save(job)
    }

//    override fun updateJob(@PathVariable id: Long, @RequestBody job: Job): Job { // TODO исправить update везде
//        return jobRepository.findById(id).map{
//            it.name = job.name
//            it.description = job.description
//            it.beginDate = job.beginDate
//            it.endDate = job.endDate
//            it.type = job.type
//            it.employers = job.employers
//            jobRepository.save(it)
//        }.orElseThrow { EntityNotFoundException("Job not found with id $id") }
//    }

}