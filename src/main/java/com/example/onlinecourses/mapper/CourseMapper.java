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

    Module mapToModuleEntity(ModuleDto moduleDto);

    List<Module> mapToModuleEntities(List<ModuleDto> moduleDtos);

    ModuleDto mapToModuleDto(Module module);

    List<ModuleDto> mapToModuleDtos(List<Module> modules);


    @Mapping(source = "moduleDtos", target = "modules")
    Section mapToSectionEntity(SectionDto sectionDto);

    List<Section> mapToSectionEntities(List<SectionDto> sectionDtos);

    @Mapping(source = "modules", target = "moduleDtos")
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
    CourseDto mapToCourseDto(Course course);

    FileDto mapToFileDto(FileStorage fileStorage);
}
