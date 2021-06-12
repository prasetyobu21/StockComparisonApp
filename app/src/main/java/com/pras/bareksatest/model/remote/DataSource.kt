package com.pras.bareksatest.model.remote

import androidx.lifecycle.LiveData
import com.pras.bareksatest.model.local.entity.DataEntity
import com.pras.bareksatest.vo.Resource

interface DataSource {
    fun getData(): LiveData<Resource<List<DataEntity>>>
}