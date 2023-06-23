package com.example.petani.application

import android.app.Application
import com.example.petani.repository.PetaniRepository

class PetaniApp: Application() {
    val database by lazy { PetaniDatabase.getDatabase(this) }
    val repository by lazy { PetaniRepository(database.petaniDao()) }
}