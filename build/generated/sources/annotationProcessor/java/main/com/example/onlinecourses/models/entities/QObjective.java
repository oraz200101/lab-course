package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QObjective is a Querydsl query type for Objective
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QObjective extends EntityPathBase<Objective> {

    private static final long serialVersionUID = 1318680470L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QObjective objective = new QObjective("objective");

    public final com.example.onlinecourses.models.entities.base.QBaseEntity _super = new com.example.onlinecourses.models.entities.base.QBaseEntity(this);

    public final QCourse course;

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final StringPath title = createString("title");

    public QObjective(String variable) {
        this(Objective.class, forVariable(variable), INITS);
    }

    public QObjective(Path<? extends Objective> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QObjective(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QObjective(PathMetadata metadata, PathInits inits) {
        this(Objective.class, metadata, inits);
    }

    public QObjective(Class<? extends Objective> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.course = inits.isInitialized("course") ? new QCourse(forProperty("course"), inits.get("course")) : null;
    }

}

