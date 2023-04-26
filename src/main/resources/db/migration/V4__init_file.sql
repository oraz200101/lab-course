CREATE TABLE lab_course.files
(
    id           VARCHAR(255) NOT NULL,
    name         VARCHAR(255),
    data         OID,
    content_type VARCHAR(255),
    link         VARCHAR(255),
    size         BIGINT,
    upload_date  TIMESTAMP WITHOUT TIME ZONE,
    CONSTRAINT pk_files PRIMARY KEY (id)
);
ALTER TABLE lab_course.courses ADD COLUMN file_id varchar(255);
ALTER TABLE lab_course.courses
    ADD CONSTRAINT FK_COURSES_ON_FILE FOREIGN KEY (file_id) REFERENCES lab_course.files (id);