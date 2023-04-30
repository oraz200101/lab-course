package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.models.entities.User;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface UserMapper {
    @Mapping(target = "dateOfBirth",ignore = true)
    User mapToUserEntity(UserRegisterDto userRegisterDto);
    @Mapping(target = "id",ignore = true)
    @Mapping(target = "roles",ignore = true)
    @Mapping(target = "dateOfBirth",ignore = true)
    @Mapping(target = "password",ignore = true)
    User mapToUserEntity(@MappingTarget User user,UserRegisterDto userRegisterDto);

    @Mapping(target = "tokenDto",ignore = true)
    UserResponseDto mapToUserResponseDto(User user);

    List<UserResponseDto> mapToUserResponseDtos(List<User> users);
}
