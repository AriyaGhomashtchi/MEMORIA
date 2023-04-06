package com.ghomashtchi.memoria.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ghomashtchi.memoria.data.model.UserAccount
import com.ghomashtchi.memoria.data.remote.MedicineApi

class Repository (val api: MedicineApi){

    private var _medicineList = MutableLiveData<List<com.ghomashtchi.memoria.testApi.Result>>()
    val medicineList : LiveData<List<com.ghomashtchi.memoria.testApi.Result>> get() =  _medicineList

    suspend fun loadMedicineList(){
        val import = api.retrofitService.getMedicine()
        _medicineList.value = import.results

    }

    fun loadAccounts(): MutableList<UserAccount> {
        return mutableListOf(
            UserAccount("ariya", "memoria123"),
            UserAccount("test","123")
        )
    }
}