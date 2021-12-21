package com.example.layouts.diseases

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.layouts.common.SingleArgViewModelFactory

class DiseasesViewModel(
    private val params: Params
) : ViewModel() {

    companion object {
        private const val TAG = "DiseasesViewModel"

        val FACTORY = SingleArgViewModelFactory.create(::DiseasesViewModel)

        class Params(
            val data: List<DiseaseItem>
        )
    }

    private val _itemsLiveData = MutableLiveData<List<DiseaseItem>>()
    val itemsLiveData: LiveData<List<DiseaseItem>> get() = _itemsLiveData

    private val _selectedLiveData = MutableLiveData<DiseaseItem>()
    val selectedLiveData: LiveData<DiseaseItem> = _selectedLiveData

    init {
        _itemsLiveData.value = params.data
    }

    fun onItemClick(id: String) {
        val selected = params.data.firstOrNull { it.id == id }
        selected?.let {
            _selectedLiveData.value = it
        }
    }

}