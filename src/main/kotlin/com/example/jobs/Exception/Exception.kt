package com.example.jobs.Exception

class JobNotFoundException(jobId: Long) : RuntimeException("Job with id $jobId not found")
class EmployerNotFoundException(employerId: Long) : RuntimeException("Employer with id $employerId not found")
class MaterialNotFoundException(materialId: Long) : RuntimeException("Employer with id $materialId not found")
