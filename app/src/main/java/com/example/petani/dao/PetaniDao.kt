package com.example.petani.dao


import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.petani.model.Petani
import kotlinx.coroutines.flow.Flow


@Dao
interface PetaniDao {
    @Query("SELECT * FROM petani_table ORDER BY id ASC")
    fun getAllPetanis(): Flow<List<Petani>>

    @Insert
    suspend fun insertPetani(petani: Petani)

    @Delete
    suspend fun deletePetani(petani: Petani)

    @Update
    fun updatePetani(petani: Petani)
}