package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QCourse is a Querydsl query type for Course
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QCourse extends EntityPathBase<Course> {

    private static final long serialVersionUID = -1879111970L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QCourse course = new QCourse("course");

    public final com.example.onlinecourses.models.entities.base.QBaseEntity _super = new com.example.onlinecourses.models.entities.base.QBaseEntity(this);

    public final QUser author;

    public final QCategory category;

    public final DateTimePath<java.time.LocalDateTime> dateTimeCreate = createDateTime("dateTimeCreate", java.time.LocalDateTime.class);

    public final DateTimePath<java.time.LocalDateTime> dateTimeUpdate = createDateTime("dateTimeUpdate", java.time.LocalDateTime.class);

    public final StringPath description = createString("description");

    public final QFileStorage fileStorage;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final ListPath<Objective, QObjective> objectives = this.<Objective, QObjective>createList("objectives", Objective.class, QObjective.class, PathInits.DIRECT2);

    public final NumberPath<Long> price = createNumber("price", Long.class);

    public final NumberPath<java.math.BigDecimal> rating = createNumber("rating", java.math.BigDecimal.class);

    public final ListPath<Section, QSection> sections = this.<Section, QSection>createList("sections", Section.class, QSection.class, PathInits.DIRECT2);

    public final StringPath title = createString("title");

    public QCourse(String variable) {
        this(Course.class, forVariable(variable), INITS);
    }

    public QCourse(Path<? extends Course> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QCourse(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QCourse(PathMetadata metadata, PathInits inits) {
        this(Course.class, metadata, inits);
    }

    public QCourse(Class<? extends Course> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.author = inits.isInitialized("author") ? new QUser(forProperty("author")) : null;
        this.category = inits.isInitialized("category") ? new QCategory(forProperty("category")) : null;
        this.fileStorage = inits.isInitialized("fileStorage") ? new QFileStorage(forProperty("fileStorage")) : null;
    }

}

