package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.models.entities.User;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-03T16:26:35+0600",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Override
    public User mapToUserEntity(UserRegisterDto userRegisterDto) {
        if ( userRegisterDto == null ) {
            return null;
        }

        User user = new User();

        user.setFullName( userRegisterDto.getFullName() );
        user.setEmail( userRegisterDto.getEmail() );
        user.setPassword( userRegisterDto.getPassword() );

        return user;
    }

    @Override
    public User mapToUserEntity(User user, UserRegisterDto userRegisterDto) {
        if ( userRegisterDto == null ) {
            return user;
        }

        user.setFullName( userRegisterDto.getFullName() );
        user.setEmail( userRegisterDto.getEmail() );

        return user;
    }

    @Override
    public UserResponseDto mapToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setFullName( user.getFullName() );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }

    @Override
    public List<UserResponseDto> mapToUserResponseDtos(List<User> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponseDto> list = new ArrayList<UserResponseDto>( users.size() );
        for ( User user : users ) {
            list.add( mapToUserResponseDto( user ) );
        }

        return list;
    }
}
