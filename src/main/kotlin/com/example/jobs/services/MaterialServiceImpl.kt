package com.example.jobs.services

import com.example.jobs.models.Material
import com.example.jobs.repository.MaterialRepository
import jakarta.persistence.EntityNotFoundException
import org.springframework.stereotype.Service
import java.util.*


@Service
class MaterialServiceImpl(private val materialRepository: MaterialRepository): MaterialService {
    override fun addMaterial(material: Material): Material = materialRepository.save(material)

    override fun deleteMaterial(id: Long) = materialRepository.deleteById(id)

    override fun findMaterial(): List<Material> = materialRepository.findAll()

    override fun findMaterialById(id: Long): Optional<Material> = materialRepository.findById(id)

    override fun updateMaterial(id: Long, material: Material): Material {
        return materialRepository.findById(id).map{
            it.name = material.name
            it.count = material.count
            it.cost = material.cost
            materialRepository.save(it)
        }.orElseThrow { EntityNotFoundException("Material not found with id $id") }
    }
}