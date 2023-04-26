package com.example.onlinecourses.models.entities;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QFileStorage is a Querydsl query type for FileStorage
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QFileStorage extends EntityPathBase<FileStorage> {

    private static final long serialVersionUID = 1518967932L;

    public static final QFileStorage fileStorage = new QFileStorage("fileStorage");

    public final StringPath contentType = createString("contentType");

    public final ArrayPath<byte[], Byte> data = createArray("data", byte[].class);

    public final StringPath id = createString("id");

    public final StringPath link = createString("link");

    public final StringPath name = createString("name");

    public final NumberPath<Long> size = createNumber("size", Long.class);

    public final DateTimePath<java.util.Date> uploadDate = createDateTime("uploadDate", java.util.Date.class);

    public QFileStorage(String variable) {
        super(FileStorage.class, forVariable(variable));
    }

    public QFileStorage(Path<? extends FileStorage> path) {
        super(path.getType(), path.getMetadata());
    }

    public QFileStorage(PathMetadata metadata) {
        super(FileStorage.class, metadata);
    }

}

