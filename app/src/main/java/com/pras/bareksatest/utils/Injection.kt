package com.pras.bareksatest.utils

import android.content.Context
import com.pras.bareksatest.model.local.LocalDataSource
import com.pras.bareksatest.model.local.room.AppDatabase
import com.pras.bareksatest.model.remote.JsonHelper
import com.pras.bareksatest.model.remote.RemoteDataSource
import com.pras.bareksatest.model.repository.DataRepo

object Injection {
    fun provideDataRepository(context: Context): DataRepo {
        val database = AppDatabase.getInstance(context)
        val remoteDataSource = RemoteDataSource.getInstance(JsonHelper(context))
        val localDataSource = LocalDataSource.getInstance(database.appDao())
        val appExecutors = AppExecutors()

        return DataRepo.getInstance(remoteDataSource, localDataSource, appExecutors)
    }
}