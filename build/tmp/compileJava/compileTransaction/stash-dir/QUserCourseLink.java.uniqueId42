package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserCourseLink is a Querydsl query type for UserCourseLink
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserCourseLink extends EntityPathBase<UserCourseLink> {

    private static final long serialVersionUID = 1052965027L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserCourseLink userCourseLink = new QUserCourseLink("userCourseLink");

    public final com.example.onlinecourses.models.entities.base.QBaseEntity _super = new com.example.onlinecourses.models.entities.base.QBaseEntity(this);

    public final QCourse course;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final NumberPath<Long> progress = createNumber("progress", Long.class);

    public final QUser user;

    public QUserCourseLink(String variable) {
        this(UserCourseLink.class, forVariable(variable), INITS);
    }

    public QUserCourseLink(Path<? extends UserCourseLink> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserCourseLink(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserCourseLink(PathMetadata metadata, PathInits inits) {
        this(UserCourseLink.class, metadata, inits);
    }

    public QUserCourseLink(Class<? extends UserCourseLink> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course"), inits.get("course")) : null;
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
    }

}

