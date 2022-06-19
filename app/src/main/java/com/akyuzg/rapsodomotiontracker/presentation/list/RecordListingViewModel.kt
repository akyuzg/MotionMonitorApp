package com.akyuzg.rapsodomotiontracker.presentation.list

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.akyuzg.rapsodomotiontracker.domain.model.Record
import com.akyuzg.rapsodomotiontracker.domain.usecase.RecordUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RecordListingViewModel @Inject constructor(
    private val recordUseCases: RecordUseCases
): ViewModel() {

    fun allRecords(): Flow<List<Record>> = recordUseCases.getRecords()


    private val _records = mutableListOf<Record>()
    val records = MutableLiveData<List<Record>>()

    init {

    }


    val selectedRecord: MutableLiveData<Record>? = null

    fun selectRecord(record: Record){
        selectedRecord?.value = record
    }

}