package com.ifmo.se.navigator.dtos

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonValue

/**
* 
* Values: Id,Name,Coordinates,CreationDate,From,To,Distance,MinusId,MinusName,MinusCoordinates,MinusCreationDate,MinusFrom,MinusTo,MinusDistance
*/
enum class SortFieldsDto(@get:JsonValue val value: kotlin.String) {

    Id("Id"),
    Name("Name"),
    Coordinates("Coordinates"),
    CreationDate("CreationDate"),
    From("From"),
    To("To"),
    Distance("Distance"),
    MinusId("-Id"),
    MinusName("-Name"),
    MinusCoordinates("-Coordinates"),
    MinusCreationDate("-CreationDate"),
    MinusFrom("-From"),
    MinusTo("-To"),
    MinusDistance("-Distance");

    companion object {
        @JvmStatic
        @JsonCreator
        fun forValue(value: kotlin.String): SortFieldsDto {
                return SortFieldsDto.entries.first{ it -> it.value == value}
        }
    }
}

