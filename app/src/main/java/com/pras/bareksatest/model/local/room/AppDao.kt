package com.pras.bareksatest.model.local.room

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pras.bareksatest.model.local.entity.DataEntity

@Dao
interface AppDao {
    @Query("SELECT * FROM dataentity")
    fun getData(): LiveData<List<DataEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertData(data: List<DataEntity>)
}