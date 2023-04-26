package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.FileDto;
import com.example.onlinecourses.models.entities.FileStorage;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface FileMapper {
    FileDto mapToFileDto(FileStorage fileStorage);
}
