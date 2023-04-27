package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUser is a Querydsl query type for User
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUser extends EntityPathBase<User> {

    private static final long serialVersionUID = -1337727154L;

    public static final QUser user = new QUser("user");

    public final com.example.onlinecourses.models.entities.base.QBaseEntity _super = new com.example.onlinecourses.models.entities.base.QBaseEntity(this);

    public final NumberPath<java.math.BigDecimal> balance = createNumber("balance", java.math.BigDecimal.class);

    public final StringPath dateOfBirth = createString("dateOfBirth");

    public final StringPath email = createString("email");

    public final BooleanPath enabled = createBoolean("enabled");

    public final StringPath fullName = createString("fullName");

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath password = createString("password");

    public final SetPath<com.example.onlinecourses.models.enums.Role, EnumPath<com.example.onlinecourses.models.enums.Role>> roles = this.<com.example.onlinecourses.models.enums.Role, EnumPath<com.example.onlinecourses.models.enums.Role>>createSet("roles", com.example.onlinecourses.models.enums.Role.class, EnumPath.class, PathInits.DIRECT2);

    public final ListPath<UserCourseLink, QUserCourseLink> userCourseLinks = this.<UserCourseLink, QUserCourseLink>createList("userCourseLinks", UserCourseLink.class, QUserCourseLink.class, PathInits.DIRECT2);

    public QUser(String variable) {
        super(User.class, forVariable(variable));
    }

    public QUser(Path<? extends User> path) {
        super(path.getType(), path.getMetadata());
    }

    public QUser(PathMetadata metadata) {
        super(User.class, metadata);
    }

}

