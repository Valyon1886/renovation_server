package com.example.jobs.controller

import com.example.jobs.models.Employer
import com.example.jobs.models.Job
import com.example.jobs.repository.EmployerRepository
import com.example.jobs.services.EmployerService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/employer")
class EmployerController(val employerService: EmployerService) {
    @PostMapping("/add")
    @ResponseBody
    fun addEmployer(@RequestBody employer: Employer) = employerService.addEmployer(employer)

    @GetMapping("/get")
    @ResponseBody
    fun findEmployer(): List<Employer> = employerService.findEmployer()

    @GetMapping("/get/{id}")
    @ResponseBody
    fun findEmployerById(@PathVariable id: Long) = employerService.findEmployerById(id)

    @PutMapping("/update/{id}")
    @ResponseBody
    fun updateEmployer(@PathVariable id: Long, @RequestBody employer: Employer) = employerService.updateEmployer(id, employer)

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    fun deleteEmployer(@PathVariable id: Long) = employerService.deleteEmployer(id)
}