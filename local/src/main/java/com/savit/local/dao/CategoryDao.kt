package com.savit.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.savit.local.model.DBCategory

@Dao
interface CategoryDao {

    @Query("SELECT * from category")
    suspend fun getCategories(): List<DBCategory>

    @Insert
    suspend fun insert(category: DBCategory)
}
