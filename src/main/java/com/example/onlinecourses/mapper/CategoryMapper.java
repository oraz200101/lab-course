package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.TagDto;
import com.example.onlinecourses.models.entities.Category;
import com.example.onlinecourses.models.entities.Tag;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface CategoryMapper {
    Category mapToCategoryEntity(CategoryDto categoryDto);

    @Mapping(target = "id", ignore = true)
    Category mapToCategoryEntity(CategoryDto categoryDto, @MappingTarget Category category);

    CategoryDto mapToCategoryDto(Category category);

    List<CategoryDto> mapToCategoryDtos(List<Category> categories);
    @Mapping(source = "categoryId",target = "category.id")
    Tag mapToTagEntity(TagDto tagDto);

    @Mapping(source = "category.id",target = "categoryId")
    TagDto mapToTagDto(Tag tag);
}
