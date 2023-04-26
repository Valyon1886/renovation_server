package com.example.jobs.services

import com.example.jobs.Exception.JobNotFoundException
import com.example.jobs.Exception.MaterialNotFoundException
import com.example.jobs.models.Material
import com.example.jobs.repository.JobRepository
import com.example.jobs.repository.MaterialRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class MaterialServiceImpl(private val materialRepository: MaterialRepository, private val jobRepository: JobRepository): MaterialService {
    override fun addMaterial(material: Material): Material = materialRepository.save(material)

    override fun addMaterialToJob(material: Material, jobId: Long): Material {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        materialRepository.save(material)
        job.materials?.add(material)
        jobRepository.save(job)
        return material
    }

    override fun deleteMaterialFromJob(materialId: Long, jobId: Long) {
        val job = jobRepository.findById(jobId).orElseThrow() { JobNotFoundException(jobId) }
        val material = materialRepository.findById(materialId).orElseThrow() { MaterialNotFoundException(materialId) }
        job.materials?.remove(material)
        materialRepository.delete(material)
        jobRepository.save(job)
    }

    override fun deleteMaterial(id: Long) = materialRepository.deleteById(id)

    override fun findMaterial(): List<Material> = materialRepository.findAll()

    override fun findMaterialById(id: Long): Optional<Material> = materialRepository.findById(id)

    override fun updateMaterial(materialId: Long, updateMaterial: Material): Material {
        val material = materialRepository.findById(materialId).orElseThrow() {MaterialNotFoundException(materialId)}
        updateMaterial.name?.let { material.name = it }
        updateMaterial.cost?.let { material.cost = it }
        updateMaterial.count?.let { material.count = it }
        return materialRepository.save(material)
    }
}