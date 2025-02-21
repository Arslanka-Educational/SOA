package org.example.com.ifmo.se.route.management.configs

import org.mapstruct.MapperConfig
import org.mapstruct.MappingConstants
import org.mapstruct.extensions.spring.SpringMapperConfig

@MapperConfig(componentModel = MappingConstants.ComponentModel.SPRING)
@SpringMapperConfig
interface MapperConfiguration {}