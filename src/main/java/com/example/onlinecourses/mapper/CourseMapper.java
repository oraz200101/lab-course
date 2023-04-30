package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.*;
import com.example.onlinecourses.models.entities.*;
import com.example.onlinecourses.models.entities.Module;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface CourseMapper {
    @Mapping(source = "sectionId",target = "section.id")
    Module mapToModuleEntity(ModuleDto moduleDto);

    List<Module> mapToModuleEntities(List<ModuleDto> moduleDtos);
    @Mapping(source = "section.id",target = "sectionId")
    ModuleDto mapToModuleDto(Module module);

    List<ModuleDto> mapToModuleDtos(List<Module> modules);


    @Mapping(source = "moduleDtos", target = "modules")
    @Mapping(source = "courseId",target = "course.id")
    Section mapToSectionEntity(SectionDto sectionDto);

    List<Section> mapToSectionEntities(List<SectionDto> sectionDtos);

    @Mapping(source = "modules", target = "moduleDtos")
    @Mapping(source = "course.id",target = "courseId")
    SectionDto mapToSectionDto(Section section);

    List<SectionDto> mapToSectionDtos(List<Section> sections);


    Objective mapToObjectiveDto(ObjectiveDto objectiveDto);

    List<Objective> mapToObjectiveDtos(List<ObjectiveDto> objectiveDtos);

    ObjectiveDto mapToObjectiveDto(Objective objective);

    List<ObjectiveDto> mapToObjectiveEntities(List<Objective> objectives);


    @Mapping(source = "sectionDtos", target = "sections")
    @Mapping(source = "objectiveDtos", target = "objectives")
    @Mapping(source = "categoryId", target = "category.id")
    Course mapToCourseEntity(CourseDto courseDto);

    @Mapping(source = "sections", target = "sectionDtos")
    @Mapping(source = "objectives", target = "objectiveDtos")
    @Mapping(source = "category.id", target = "categoryId")
    @Mapping(expression = "java(course.getSections()!= null ? course.getSections().size():0)",target = "lecturesQuantity")
    CourseDto mapToCourseDto(Course course);
    @Mapping(source = "author.fullName",target = "author")
    @Mapping(expression = "java(course.getSections()!= null ? course.getSections().size():0)",target = "lecturesQuantity")
    CourseResponseDto mapToCourseResponseDto(Course course);
    List<CourseResponseDto> mapToCourseResponseDtos(List<Course> courses);
    FileDto mapToFileDto(FileStorage fileStorage);
}
