package com.ghomashtchi.memoria

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ghomashtchi.memoria.data.Repository
import com.ghomashtchi.memoria.data.remote.MedicineApi
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {
    val repository = Repository(MedicineApi)
    val medicineList = repository.medicineList
    val dailymedicine = repository.dailymedicineList
    private var _time = MutableLiveData<String>("")
    val time: LiveData<String> get() = _time
    private var _date = MutableLiveData<String>("")
    val date: LiveData<String> get() = _date
    private var _categoryList = MutableLiveData<Int>()
    val categoryList: LiveData<Int> get() = _categoryList

    init {
        loadMedicine()
    }

    fun setCategoryList(Catergory: Int) {
        _categoryList.value = Catergory
    }

    fun loadMedicine() {
        viewModelScope.launch {
            repository.loadMedicineList()
        }
    }

    fun addTooDailyMedicineList(medicine: com.ghomashtchi.memoria.data.medicineApi.Result) {
        repository.addToDailyymedicine(medicine)
    }

    fun removeFromDailyMedicine(medicine: com.ghomashtchi.memoria.data.medicineApi.Result) {
        repository.removeFromDailymedicine(medicine)
    }

    fun mementotime(time: String) {
        _time.value = time
    }

    fun mementodate(date: String) {
        _date.value = date
    }

}
