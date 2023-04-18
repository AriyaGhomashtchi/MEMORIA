package com.ghomashtchi.memoria.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ghomashtchi.memoria.data.medicineApi.Result
import com.ghomashtchi.memoria.data.model.UserAccount
import com.ghomashtchi.memoria.data.remote.MedicineApi

class Repository(val api: MedicineApi) {

    private var _medicineList = MutableLiveData<List<Result>>()
    val medicineList: LiveData<List<Result>> get() = _medicineList
    private var _dailyMedicineList =
        MutableLiveData<MutableList<com.ghomashtchi.memoria.data.medicineApi.Result>>(
            mutableListOf()
        )
    val dailymedicineList: LiveData<MutableList<com.ghomashtchi.memoria.data.medicineApi.Result>>
        get() =
            _dailyMedicineList

    suspend fun loadMedicineList() {
        val import = api.retrofitService.getMedicine()
        _medicineList.value = import.results

    }

    fun loadAccounts(): MutableList<UserAccount> {
        return mutableListOf(
            UserAccount("ariya", "memoria123"),
            UserAccount("test", "123")
        )
    }

    fun addToDailyymedicine(medicine: com.ghomashtchi.memoria.data.medicineApi.Result) {
        _dailyMedicineList.value?.add(medicine)
    }

    fun removeFromDailymedicine(medicine: com.ghomashtchi.memoria.data.medicineApi.Result) {
        _dailyMedicineList.value?.remove(medicine)
    }

}