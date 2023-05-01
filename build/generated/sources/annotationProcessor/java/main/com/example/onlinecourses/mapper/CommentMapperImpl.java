package com.example.onlinecourses.mapper;

import com.example.onlinecourses.models.dto.CommentDto;
import com.example.onlinecourses.models.dto.UserResponseDto;
import com.example.onlinecourses.models.entities.Comment;
import com.example.onlinecourses.models.entities.Course;
import com.example.onlinecourses.models.entities.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-05-01T23:01:30+0600",
    comments = "version: 1.5.4.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.1.jar, environment: Java 17.0.7 (Amazon.com Inc.)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public Comment mapToEntity(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Comment comment = new Comment();

        comment.setCourse( commentDtoToCourse( commentDto ) );
        comment.setText( commentDto.getText() );
        comment.setCreatedBy( userResponseDtoToUser( commentDto.getCreatedBy() ) );
        comment.setRating( commentDto.getRating() );
        comment.setDateTimeCreate( commentDto.getDateTimeCreate() );

        return comment;
    }

    @Override
    public CommentDto mapToDto(Comment comment) {
        if ( comment == null ) {
            return null;
        }

        CommentDto commentDto = new CommentDto();

        commentDto.setCourseId( commentCourseId( comment ) );
        commentDto.setCreatedBy( userToUserResponseDto( comment.getCreatedBy() ) );
        commentDto.setText( comment.getText() );
        commentDto.setRating( comment.getRating() );
        commentDto.setDateTimeCreate( comment.getDateTimeCreate() );

        return commentDto;
    }

    protected Course commentDtoToCourse(CommentDto commentDto) {
        if ( commentDto == null ) {
            return null;
        }

        Course course = new Course();

        course.setId( commentDto.getCourseId() );

        return course;
    }

    protected User userResponseDtoToUser(UserResponseDto userResponseDto) {
        if ( userResponseDto == null ) {
            return null;
        }

        User user = new User();

        user.setId( userResponseDto.getId() );
        user.setFullName( userResponseDto.getFullName() );
        user.setEmail( userResponseDto.getEmail() );

        return user;
    }

    private Long commentCourseId(Comment comment) {
        if ( comment == null ) {
            return null;
        }
        Course course = comment.getCourse();
        if ( course == null ) {
            return null;
        }
        Long id = course.getId();
        if ( id == null ) {
            return null;
        }
        return id;
    }

    protected UserResponseDto userToUserResponseDto(User user) {
        if ( user == null ) {
            return null;
        }

        UserResponseDto userResponseDto = new UserResponseDto();

        userResponseDto.setId( user.getId() );
        userResponseDto.setFullName( user.getFullName() );
        userResponseDto.setEmail( user.getEmail() );

        return userResponseDto;
    }
}
