package com.example.petani

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.petani.model.Petani
import com.example.petani.repository.PetaniRepository
import kotlinx.coroutines.launch

class PetaniViewModel(private val repository: PetaniRepository): ViewModel() {
    val allPetanis: LiveData<List<Petani>> = repository.allPetanis.asLiveData()

    fun insert(petani: Petani) = viewModelScope.launch {
        repository.insert(petani)
    }

    fun delete(petani: Petani) = viewModelScope.launch {
        repository.delete(petani)
    }

    fun update(petani: Petani) = viewModelScope.launch {
        repository.update(petani)
    }
}

class PetaniViewModelFactory(private val repository: PetaniRepository): ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PetaniViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return PetaniViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
