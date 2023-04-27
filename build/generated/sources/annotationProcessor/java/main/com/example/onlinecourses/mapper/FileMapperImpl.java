package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.FileDto;
import com.example.onlinecourses.models.entities.FileStorage;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-04-27T03:32:38+0600",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class FileMapperImpl implements FileMapper {

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
}
