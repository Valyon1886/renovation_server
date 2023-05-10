package com.example.jobs.controller

import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.repository.JobRepository
import com.example.jobs.services.JobService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*
import java.util.*
import kotlin.collections.List

@Controller
@RequestMapping("/job")
class JobController(private val jobService: JobService) {

    @PostMapping("/add")
    @ResponseBody
    fun addJob(@RequestBody job: Job) = jobService.addJob(job)

    @PostMapping("/add/task/{jobId}")
    @ResponseBody
    fun addSubTask(@RequestBody subTask: Job, @PathVariable jobId: Long) = jobService.addSubTask(subTask, jobId)

    @PostMapping("/finish/task/{subTaskId}/{jobId}")
    @ResponseBody
    fun finishSubTask(@PathVariable subTaskId: Long, @PathVariable jobId: Long) = jobService.finishSubTask(subTaskId, jobId)

    @DeleteMapping("/delete/task/{subTaskId}/{jobId}")
    @ResponseBody
    fun deleteSubTask(@PathVariable subTaskId: Long, @PathVariable jobId: Long) = jobService.deleteSubTask(subTaskId, jobId)

    @PostMapping("/{jobId}/addEmTo/{employerId}")
    @ResponseBody
    fun addEmployerToJob(@PathVariable jobId: Long, @PathVariable employerId: Long): Job = jobService.addEmployerToJob(jobId, employerId)

    @DeleteMapping("/{jobId}/deleteEmFrom/{employerId}")
    @ResponseBody
    fun deleteEmployerFromJob(@PathVariable jobId: Long, @PathVariable employerId: Long): Job = jobService.deleteEmployerFromJob(jobId, employerId)

    @PostMapping("/{jobId}/addMatTo/{materialId}")
    @ResponseBody
    fun addMaterialToJob(@PathVariable jobId: Long, @PathVariable materialId: Long): Job = jobService.addMaterialToJob(jobId, materialId)

    @DeleteMapping("/{jobId}/deleteMatFrom/{materialId}")
    @ResponseBody
    fun deleteMaterialFromJob(@PathVariable jobId: Long, @PathVariable materialId: Long): Job = jobService.deleteMaterialFromJob(jobId, materialId)

    @PostMapping("/{jobId}/addSubTo/{subtaskId}")
    @ResponseBody
    fun addSubtaskToJob(@PathVariable jobId: Long, @PathVariable subtaskId: Long): Job = jobService.addTaskToJob(jobId, subtaskId)

    @DeleteMapping("/{jobId}/deleteSubFrom/{subtaskId}")
    @ResponseBody
    fun deleteSubtaskFromJob(@PathVariable jobId: Long, @PathVariable subtaskId: Long): Job = jobService.deleteTaskFromJob(jobId, subtaskId)

    @GetMapping("/getAll")
    @ResponseBody
    fun findJob(): List<Job> = jobService.findJob()

    @GetMapping("/get/allTask/{id}")
    @ResponseBody
    fun findSubJobForJob(@PathVariable id: Long): List<Job> = jobService.findSubTask(id)

    @GetMapping("/get/{id}")
    @ResponseBody
    fun findJobById(@PathVariable id: Long) = jobService.findJobById(id)

//    @PutMapping("/update/{id}")
//    @ResponseBody
//    fun updateJob(@PathVariable id: Long, @RequestBody job: Map<String, Any>) = jobService.updateJob(id, job)

    @PutMapping("/update/{id}")
    @ResponseBody
    fun updateJob(@PathVariable id: Long, @RequestBody job: Job) = jobService.updateJob(id, job)

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    fun deleteJob(@PathVariable id: Long) = jobService.deleteJob(id)
}