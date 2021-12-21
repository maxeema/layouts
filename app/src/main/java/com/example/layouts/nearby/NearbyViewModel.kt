package com.example.layouts.nearby

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.layouts.common.SingleArgViewModelFactory

class NearbyViewModel(
    private val params: Params
) : ViewModel() {

    companion object {
        private const val TAG = "NearbyViewModel"

        val FACTORY = SingleArgViewModelFactory.create(::NearbyViewModel)

        class Params(
            val data: List<NearbyGroup>,
            val selected : NearbyGroup
        )
    }

    private val _groupsLiveData = MutableLiveData<List<NearbyGroup>>()
    val groupsLiveData: LiveData<List<NearbyGroup>> get() = _groupsLiveData

    private val _selectedGroupLiveData = MutableLiveData<NearbyGroup>().apply {
        this.observeForever {
            _selectedItemLiveData.value = it.items.first()
        }
    }
    val selectedGroupLiveData: LiveData<NearbyGroup> = _selectedGroupLiveData

    private val _selectedItemLiveData = MutableLiveData<NearbyItem>()
    val selectedItemLiveData: LiveData<NearbyItem> = _selectedItemLiveData

    init {
        _groupsLiveData.value = params.data
        _selectedGroupLiveData.value = params.selected
    }

    fun onItemClick(id: String) {
        val selected = selectedGroupLiveData.value!!.items.firstOrNull { it.id == id }
        selected?.let {
            _selectedItemLiveData.value = it
        }
    }

}