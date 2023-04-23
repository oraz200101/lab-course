package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.UserRegisterDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.models.entities.User;
import org.mapstruct.Builder;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, builder = @Builder(disableBuilder = true))
public interface UserMapper {

    User mapToUserEntity(UserRegisterDto userRegisterDto);

    UserResponseDto mapToUserResponseDto(User user);

    List<UserResponseDto> mapToUserResponseDtos(List<User> users);
}
