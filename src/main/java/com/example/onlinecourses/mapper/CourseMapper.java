package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.ModuleDto;
import com.example.onlinecourses.models.dto.ObjectiveDto;
import com.example.onlinecourses.models.dto.SectionDto;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.Module;
import com.example.onlinecourses.models.entities.Objective;
import com.example.onlinecourses.models.entities.Section;
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
    Course mapToCourseEntity(CourseDto courseDto);

    @Mapping(source = "sections", target = "sectionDtos")
    @Mapping(source = "objectives", target = "objectiveDtos")
    CourseDto mapToCourseDto(Course course);
}
