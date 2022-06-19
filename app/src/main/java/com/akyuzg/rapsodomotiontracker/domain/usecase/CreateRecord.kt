package com.akyuzg.rapsodomotiontracker.domain.usecase

import com.akyuzg.rapsodomotiontracker.domain.model.Record
import com.akyuzg.rapsodomotiontracker.domain.repository.RecordRepository

class CreateRecord(
    private val repository: RecordRepository
) {

    suspend operator fun invoke(record: Record){
        repository.createRecord(record)
    }
}