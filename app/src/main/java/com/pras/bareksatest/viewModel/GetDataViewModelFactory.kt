package com.pras.bareksatest.viewModel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.pras.bareksatest.model.repository.DataRepo
import com.pras.bareksatest.utils.Injection

class GetDataViewModelFactory private constructor(private val mDataRepo: DataRepo) :
    ViewModelProvider.NewInstanceFactory() {
    companion object {
        @Volatile
        private var instance: GetDataViewModelFactory? = null

        fun getInstance(context: Context): GetDataViewModelFactory =
            instance?: synchronized(this){
                instance?: GetDataViewModelFactory(Injection.provideDataRepository(context)).apply {
                    instance = this
                }
            }
    }

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        when{
            modelClass.isAssignableFrom(GetDataViewModel::class.java) -> {
                return GetDataViewModel(mDataRepo) as T
            }
            else -> throw Throwable("Unknown ViewModel class " + modelClass.name)
        }
    }
}