package com.example.jobs.services

import com.example.jobs.models.Material
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import java.util.*

interface MaterialService {
    fun addMaterial(material: Material): Material
    fun addMaterialToJob(material: Material, jobId: Long): Material
    fun deleteMaterialFromJob(materialId: Long, jobId: Long)
    fun deleteMaterial(id: Long)
    fun findMaterial(): List<Material>
    fun findMaterialById(@PathVariable id: Long): Optional<Material>
    fun updateMaterial(@PathVariable materialId: Long, @RequestBody updateMaterial: Material): Material
}