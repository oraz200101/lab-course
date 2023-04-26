package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CourseDto;
import com.example.onlinecourses.models.dto.FileDto;
import com.example.onlinecourses.models.dto.ModuleDto;
import com.example.onlinecourses.models.dto.ObjectiveDto;
import com.example.onlinecourses.models.dto.SectionDto;
import com.example.onlinecourses.models.entities.Category;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.FileStorage;
import com.example.onlinecourses.models.entities.Module;
import com.example.onlinecourses.models.entities.Objective;
import com.example.onlinecourses.models.entities.Section;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-26T21:26:01+0600",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CourseMapperImpl implements CourseMapper {

    @Override
    public Module mapToModuleEntity(ModuleDto moduleDto) {
        if ( moduleDto == null ) {
            return null;
        }

        Module module = new Module();

        module.setTitle( moduleDto.getTitle() );
        module.setVideoLink( moduleDto.getVideoLink() );
        module.setDuration( moduleDto.getDuration() );

        return module;
    }

    @Override
    public List<Module> mapToModuleEntities(List<ModuleDto> moduleDtos) {
        if ( moduleDtos == null ) {
            return null;
        }

        List<Module> list = new ArrayList<Module>( moduleDtos.size() );
        for ( ModuleDto moduleDto : moduleDtos ) {
            list.add( mapToModuleEntity( moduleDto ) );
        }

        return list;
    }

    @Override
    public ModuleDto mapToModuleDto(Module module) {
        if ( module == null ) {
            return null;
        }

        ModuleDto moduleDto = new ModuleDto();

        moduleDto.setTitle( module.getTitle() );
        moduleDto.setVideoLink( module.getVideoLink() );
        moduleDto.setDuration( module.getDuration() );

        return moduleDto;
    }

    @Override
    public List<ModuleDto> mapToModuleDtos(List<Module> modules) {
        if ( modules == null ) {
            return null;
        }

        List<ModuleDto> list = new ArrayList<ModuleDto>( modules.size() );
        for ( Module module : modules ) {
            list.add( mapToModuleDto( module ) );
        }

        return list;
    }

    @Override
    public Section mapToSectionEntity(SectionDto sectionDto) {
        if ( sectionDto == null ) {
            return null;
        }

        Section section = new Section();

        section.setModules( mapToModuleEntities( sectionDto.getModuleDtos() ) );
        section.setTitle( sectionDto.getTitle() );

        return section;
    }

    @Override
    public List<Section> mapToSectionEntities(List<SectionDto> sectionDtos) {
        if ( sectionDtos == null ) {
            return null;
        }

        List<Section> list = new ArrayList<Section>( sectionDtos.size() );
        for ( SectionDto sectionDto : sectionDtos ) {
            list.add( mapToSectionEntity( sectionDto ) );
        }

        return list;
    }

    @Override
    public SectionDto mapToSectionDto(Section section) {
        if ( section == null ) {
            return null;
        }

        SectionDto sectionDto = new SectionDto();

        sectionDto.setModuleDtos( mapToModuleDtos( section.getModules() ) );
        sectionDto.setTitle( section.getTitle() );

        return sectionDto;
    }

    @Override
    public List<SectionDto> mapToSectionDtos(List<Section> sections) {
        if ( sections == null ) {
            return null;
        }

        List<SectionDto> list = new ArrayList<SectionDto>( sections.size() );
        for ( Section section : sections ) {
            list.add( mapToSectionDto( section ) );
        }

        return list;
    }

    @Override
    public Objective mapToObjectiveDto(ObjectiveDto objectiveDto) {
        if ( objectiveDto == null ) {
            return null;
        }

        Objective objective = new Objective();

        objective.setTitle( objectiveDto.getTitle() );

        return objective;
    }

    @Override
    public List<Objective> mapToObjectiveDtos(List<ObjectiveDto> objectiveDtos) {
        if ( objectiveDtos == null ) {
            return null;
        }

        List<Objective> list = new ArrayList<Objective>( objectiveDtos.size() );
        for ( ObjectiveDto objectiveDto : objectiveDtos ) {
            list.add( mapToObjectiveDto( objectiveDto ) );
        }

        return list;
    }

    @Override
    public ObjectiveDto mapToObjectiveDto(Objective objective) {
        if ( objective == null ) {
            return null;
        }

        ObjectiveDto objectiveDto = new ObjectiveDto();

        objectiveDto.setTitle( objective.getTitle() );

        return objectiveDto;
    }

    @Override
    public List<ObjectiveDto> mapToObjectiveEntities(List<Objective> objectives) {
        if ( objectives == null ) {
            return null;
        }

        List<ObjectiveDto> list = new ArrayList<ObjectiveDto>( objectives.size() );
        for ( Objective objective : objectives ) {
            list.add( mapToObjectiveDto( objective ) );
        }

        return list;
    }

    @Override
    public Course mapToCourseEntity(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setCategory( courseDtoToCategory( courseDto ) );
        course.setSections( mapToSectionEntities( courseDto.getSectionDtos() ) );
        course.setObjectives( mapToObjectiveDtos( courseDto.getObjectiveDtos() ) );
        course.setId( courseDto.getId() );
        course.setTitle( courseDto.getTitle() );
        course.setDescription( courseDto.getDescription() );
        course.setPrice( courseDto.getPrice() );
        course.setRating( courseDto.getRating() );

        return course;
    }

    @Override
    public CourseDto mapToCourseDto(Course course) {
        if ( course == null ) {
            return null;
        }

        CourseDto courseDto = new CourseDto();

        courseDto.setSectionDtos( mapToSectionDtos( course.getSections() ) );
        courseDto.setObjectiveDtos( mapToObjectiveEntities( course.getObjectives() ) );
        courseDto.setCategoryId( courseCategoryId( course ) );
        courseDto.setId( course.getId() );
        courseDto.setTitle( course.getTitle() );
        courseDto.setDescription( course.getDescription() );
        courseDto.setPrice( course.getPrice() );
        courseDto.setRating( course.getRating() );

        return courseDto;
    }

    @Override
    public FileDto mapToFileDto(FileStorage fileStorage) {
        if ( fileStorage == null ) {
            return null;
        }

        FileDto fileDto = new FileDto();

        fileDto.setId( fileStorage.getId() );
        fileDto.setName( fileStorage.getName() );
        fileDto.setContentType( fileStorage.getContentType() );
        fileDto.setSize( fileStorage.getSize() );
        fileDto.setUploadDate( fileStorage.getUploadDate() );

        return fileDto;
    }

    protected Category courseDtoToCategory(CourseDto courseDto) {
        if ( courseDto == null ) {
            return null;
        }

        Category category = new Category();

        category.setId( courseDto.getCategoryId() );

        return category;
    }

    private Long courseCategoryId(Course course) {
        if ( course == null ) {
            return null;
        }
        Category category = course.getCategory();
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
