package com.pras.bareksatest.model.remote

import android.os.Handler
import android.os.Looper
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pras.bareksatest.model.remote.response.DataResponse
import com.pras.bareksatest.utils.EspressoIdlingResource

class RemoteDataSource private constructor(private val jsonHelper: JsonHelper) {
    private val handler = Handler(Looper.getMainLooper())

    companion object {
        private const val SERVICE_LATENCY_IN_MILIS: Long = 2000

        @Volatile
        private var instance: RemoteDataSource? = null

        fun getInstance(helper: JsonHelper): RemoteDataSource =
            instance ?: synchronized(this) {
                instance ?: RemoteDataSource(helper).apply { instance = this }
            }
    }

    val dataResponse = MutableLiveData<List<DataResponse>>()

    fun getData(): LiveData<ApiResponse<List<DataResponse>>> {
        EspressoIdlingResource.increment()
        val resultDataList = MutableLiveData<ApiResponse<List<DataResponse>>>()
        handler.postDelayed(
            {
                resultDataList.value = ApiResponse.success(jsonHelper.loadData())
                Log.d("graphArrayRemote3", resultDataList.value?.body.toString())
                if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow) {
                    EspressoIdlingResource.decrement()
                }
            }, SERVICE_LATENCY_IN_MILIS
        )
        Log.d("graphArrayRemote", jsonHelper.loadData().toString())
        Log.d("graphArrayRemote2", resultDataList.value.toString())
        return resultDataList
    }

}