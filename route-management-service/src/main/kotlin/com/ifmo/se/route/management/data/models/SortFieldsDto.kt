package org.example.com.ifmo.se.route.management.data.models

import jakarta.xml.bind.annotation.XmlEnum
import jakarta.xml.bind.annotation.XmlEnumValue

@XmlEnum
enum class SortFieldsDto(private val value: String) {
    @XmlEnumValue("Id")
    ID("Id"),

    @XmlEnumValue("Name")
    NAME("Name"),

    @XmlEnumValue("Coordinates")
    COORDINATES("Coordinates"),

    @XmlEnumValue("CreationDate")
    CREATION_DATE("CreationDate"),

    @XmlEnumValue("From")
    FROM("From"),

    @XmlEnumValue("To")
    TO("To"),

    @XmlEnumValue("Distance")
    DISTANCE("Distance"),

    @XmlEnumValue("-Id")
    NEGATIVE_ID("-Id"),

    @XmlEnumValue("-Name")
    NEGATIVE_NAME("-Name"),

    @XmlEnumValue("-Coordinates")
    NEGATIVE_COORDINATES("-Coordinates"),

    @XmlEnumValue("-CreationDate")
    NEGATIVE_CREATION_DATE("-CreationDate"),

    @XmlEnumValue("-From")
    NEGATIVE_FROM("-From"),

    @XmlEnumValue("-To")
    NEGATIVE_TO("-To"),

    @XmlEnumValue("-Distance")
    NEGATIVE_DISTANCE("-Distance");

    fun value(): String {
        return value
    }

    companion object {
        fun fromValue(value: String?): SortFieldsDto {
            for (field in SortFieldsDto.entries) {
                if (field.value == value) {
                    return field
                }
            }
            throw IllegalArgumentException("Unknown enum value: " + value)
        }
    }
}