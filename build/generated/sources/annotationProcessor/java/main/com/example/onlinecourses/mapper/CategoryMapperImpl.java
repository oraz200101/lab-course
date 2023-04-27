package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CategoryDto;
import com.example.onlinecourses.models.dto.TagDto;
import com.example.onlinecourses.models.entities.Category;
import com.example.onlinecourses.models.entities.Tag;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T10:55:38+0600",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CategoryMapperImpl implements CategoryMapper {

    @Override
    public Category mapToCategoryEntity(CategoryDto categoryDto) {
        if ( categoryDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( categoryDto.getId() );
        category.setName( categoryDto.getName() );

        return category;
    }

    @Override
    public Category mapToCategoryEntity(CategoryDto categoryDto, Category category) {
        if ( categoryDto == null ) {
            return category;
        }

        category.setName( categoryDto.getName() );

        return category;
    }

    @Override
    public CategoryDto mapToCategoryDto(Category category) {
        if ( category == null ) {
            return null;
        }

        CategoryDto categoryDto = new CategoryDto();

        categoryDto.setId( category.getId() );
        categoryDto.setName( category.getName() );

        return categoryDto;
    }

    @Override
    public List<CategoryDto> mapToCategoryDtos(List<Category> categories) {
        if ( categories == null ) {
            return null;
        }

        List<CategoryDto> list = new ArrayList<CategoryDto>( categories.size() );
        for ( Category category : categories ) {
            list.add( mapToCategoryDto( category ) );
        }

        return list;
    }

    @Override
    public Tag mapToTagEntity(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Tag tag = new Tag();

        tag.setCategory( tagDtoToCategory( tagDto ) );
        tag.setName( tagDto.getName() );

        return tag;
    }

    @Override
    public TagDto mapToTagDto(Tag tag) {
        if ( tag == null ) {
            return null;
        }

        TagDto tagDto = new TagDto();

        tagDto.setCategoryId( tagCategoryId( tag ) );
        tagDto.setName( tag.getName() );

        return tagDto;
    }

    protected Category tagDtoToCategory(TagDto tagDto) {
        if ( tagDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( tagDto.getCategoryId() );

        return category;
    }

    private Long tagCategoryId(Tag tag) {
        if ( tag == null ) {
            return null;
        }
        Category category = tag.getCategory();
        if ( category == null ) {
            return null;
        }
        Long id = category.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }
}
