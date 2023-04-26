package com.example.jobs.controller

import com.example.jobs.models.Job
import com.example.jobs.models.Material
import com.example.jobs.repository.MaterialRepository
import com.example.jobs.services.MaterialService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*


@Controller
@RequestMapping("/material")
class MaterialController(private val materialService: MaterialService) {
    @PostMapping("/add")
    @ResponseBody
    fun addMaterial(@RequestBody material: Material) = materialService.addMaterial(material)

    @PostMapping("/add/toJob/{jobId}")
    @ResponseBody
    fun addMaterialToJob(@RequestBody material: Material, @PathVariable jobId: Long) = materialService.addMaterialToJob(material, jobId)

    @DeleteMapping("/delete/fromJob/{materialId}/{jobId}")
    @ResponseBody
    fun deleteMaterialFromJob(@PathVariable materialId: Long, @PathVariable jobId: Long) = materialService.deleteMaterialFromJob(materialId, jobId)

    @GetMapping("/get")
    @ResponseBody
    fun findMaterial(): List<Material> = materialService.findMaterial()

    @GetMapping("/get/{id}")
    @ResponseBody
    fun findMaterialById(@PathVariable id: Long) = materialService.findMaterialById(id)

    @PutMapping("/update/{materialId}")
    @ResponseBody
    fun updateMaterial(@PathVariable materialId: Long, @RequestBody updateMaterial: Material) = materialService.updateMaterial(materialId, updateMaterial)

    @DeleteMapping("/delete/{id}")
    @ResponseBody
    fun deleteMaterial(@PathVariable id: Long) = materialService.deleteMaterial(id)
}