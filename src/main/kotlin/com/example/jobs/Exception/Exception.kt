package com.example.jobs.Exception

class JobNotFoundException(jobId: Long) : RuntimeException("Job with id $jobId not found")
class EmployerNotFoundException(employerId: Long) : RuntimeException("Employer with id $employerId not found")
class MaterialNotFoundException(materialId: Long) : RuntimeException("Material with id $materialId not found")
class UserNotFoundException(userId: Long) : RuntimeException("User with id $userId not found")
