package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.entities.Category;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    Category mapToCategoryEntity(CategoryDto categoryDto);

    CategoryDto mapToCategoryDto(Category category);
}
