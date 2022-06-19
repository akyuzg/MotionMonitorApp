package com.akyuzg.rapsodomotiontracker.domain.usecase

data class RecordUseCases(
    val getRecords: GetRecords,
    val getPositions: GetPositions,
    val createRecord: CreateRecord,
    val insertPosition: InsertPosition
)
