package com.pras.bareksatest.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.pras.bareksatest.model.local.entity.DataEntity
import com.pras.bareksatest.model.repository.DataRepo
import com.pras.bareksatest.vo.Resource

class GetDataViewModel(private val repo: DataRepo): ViewModel() {
    fun getData(): LiveData<Resource<List<DataEntity>>> = repo.getData()
}