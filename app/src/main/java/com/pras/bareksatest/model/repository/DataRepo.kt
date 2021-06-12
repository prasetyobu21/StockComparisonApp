package com.pras.bareksatest.model.repository

import android.util.Log
import androidx.lifecycle.LiveData
import com.pras.bareksatest.model.local.LocalDataSource
import com.pras.bareksatest.model.local.entity.DataEntity
import com.pras.bareksatest.model.remote.ApiResponse
import com.pras.bareksatest.model.remote.DataSource
import com.pras.bareksatest.model.remote.RemoteDataSource
import com.pras.bareksatest.model.remote.response.DataResponse
import com.pras.bareksatest.utils.AppExecutors
import com.pras.bareksatest.utils.NetworkBoundResource
import com.pras.bareksatest.vo.Resource

class DataRepo private constructor(
    private val remoteDataSource: RemoteDataSource,
    private val localDataSource: LocalDataSource,
    private val appExecutors: AppExecutors
) : DataSource {

    companion object {
        @Volatile
        private var instance: DataRepo? = null

        fun getInstance(
            remoteDataSource: RemoteDataSource,
            localDataSource: LocalDataSource,
            appExecutors: AppExecutors
        ): DataRepo = instance ?: synchronized(this) {
            instance ?: DataRepo(
                remoteDataSource,
                localDataSource,
                appExecutors
            ).apply { instance = this }
        }
    }

    override fun getData(): LiveData<Resource<List<DataEntity>>> {
        return object :
            NetworkBoundResource<List<DataEntity>, List<DataResponse>>(appExecutors) {
            override fun loadFromDB(): LiveData<List<DataEntity>> = localDataSource.getData()

            override fun shouldFetch(data: List<DataEntity>?): Boolean =
                data == null || data.isEmpty()

            override fun createCall(): LiveData<ApiResponse<List<DataResponse>>> =
                remoteDataSource.getData()

            override fun saveCallResult(data: List<DataResponse>) {
                val dataList = ArrayList<DataEntity>()
                for (response in data) {
                    Log.d("graphArrayRepoo", "Response not null")
                    val data = DataEntity(
                        response.name,
                        response.jenis,
                        response.imbalHasil,
                        response.danaKelolaan,
                        response.minPembelian,
                        response.jangkaWaktu,
                        response.tingkatRisiko,
                        response.peluncuran,
                        response.graph
                    )
                    dataList.add(data)
                    Log.d("graphArrayRepoo", response.toString())
                }
                localDataSource.insertData(dataList)
                Log.d("graphArrayRepo", dataList.toString())
            }
        }.asLiveData()
    }

}