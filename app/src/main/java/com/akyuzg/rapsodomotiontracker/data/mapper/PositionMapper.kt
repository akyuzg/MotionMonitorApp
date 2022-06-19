package com.akyuzg.rapsodomotiontracker.data.mapper

import com.akyuzg.rapsodomotiontracker.domain.model.Position
import com.akyuzg.rapsodomotiontracker.data.local.dto.Coordinate
import javax.inject.Inject

class PositionMapper @Inject constructor(): Mapper<Coordinate, Position> {
    override fun toModel(value: Coordinate): Position {
        return Position(value.x, value.y, value.z)
    }

    override fun fromModel(value: Position): Coordinate {
        return Coordinate(value.x, value.y, value.z)
    }
}