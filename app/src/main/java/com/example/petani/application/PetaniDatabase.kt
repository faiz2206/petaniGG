package com.example.petani.application


import android.content.Context
import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.petani.dao.PetaniDao
import com.example.petani.model.Petani


@Database(entities = [Petani::class], version = 1, exportSchema = false)
abstract class PetaniDatabase: RoomDatabase() {
    abstract fun petaniDao(): PetaniDao

    companion object {
        private var INSTANCE:PetaniDatabase? = null

        fun getDatabase(contex: Context): PetaniDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = androidx.room.Room.databaseBuilder(
                    contex.applicationContext,
                    PetaniDatabase::class.java,
                    "petani_database"
                )
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}