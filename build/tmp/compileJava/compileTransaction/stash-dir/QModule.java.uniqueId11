package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModule is a Querydsl query type for Module
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModule extends EntityPathBase<Module> {

    private static final long serialVersionUID = -1593324241L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModule module = new QModule("module");

    public final com.example.onlinecourses.models.entities.base.QBaseEntity _super = new com.example.onlinecourses.models.entities.base.QBaseEntity(this);

    public final NumberPath<Long> duration = createNumber("duration", Long.class);

    //inherited
    public final NumberPath<Long> id = _super.id;

    public final QSection section;

    public final StringPath title = createString("title");

    public final StringPath videoLink = createString("videoLink");

    public QModule(String variable) {
        this(Module.class, forVariable(variable), INITS);
    }

    public QModule(Path<? extends Module> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModule(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModule(PathMetadata metadata, PathInits inits) {
        this(Module.class, metadata, inits);
    }

    public QModule(Class<? extends Module> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.section = inits.isInitialized("section") ? new QSection(forProperty("section"), inits.get("section")) : null;
    }

}

