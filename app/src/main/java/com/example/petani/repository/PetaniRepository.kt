package com.example.petani.repository


import com.example.petani.dao.PetaniDao
import com.example.petani.model.Petani
import kotlinx.coroutines.flow.Flow;


class PetaniRepository(private val PetaniDao: PetaniDao) {
    val allPetanis: Flow<List<Petani>> = PetaniDao.getAllPetani()

    suspend fun insert(petani: Petani) {
        PetaniDao.insertPetani(petani)
    }

    suspend fun delete(petani: Petani) {
        PetaniDao.deletePetani(petani)
    }

    suspend fun update(petani: Petani) {
        PetaniDao.updatePetani(petani)
    }
}
