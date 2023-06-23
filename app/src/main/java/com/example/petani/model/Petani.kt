package com.example.petani.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

    @Parcelize
    @Entity(tableName = "petani_table")
    data class Petani(
        @PrimaryKey(autoGenerate = true)
        val id: Int = 0,
        val title: String,
        val category: String,
        val content: String
    ) : Parcelable
