CREATE SCHEMA lab_course;
CREATE TABLE lab_course.user_role
(
    user_id BIGINT NOT NULL,
    roles   VARCHAR(255)
);

CREATE TABLE lab_course.users
(
    id            BIGSERIAL PRIMARY KEY NOT NULL,
    full_name     VARCHAR(255),
    date_of_birth date,
    email         VARCHAR(255),
    balance       DECIMAL,
    password      VARCHAR(255),
    enabled       BOOLEAN                                 NOT NULL
    );

ALTER TABLE lab_course.user_role
    ADD CONSTRAINT fk_user_role_on_user FOREIGN KEY (user_id) REFERENCES lab_course.users (id);

CREATE TABLE lab_course.categories
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);

CREATE TABLE lab_course.tags
(
    id   BIGSERIAL PRIMARY KEY NOT NULL,
    name VARCHAR(255)
);
CREATE TABLE lab_course.courses
(
    id               BIGSERIAL PRIMARY KEY NOT NULL,
    title            VARCHAR(255),
    description      VARCHAR(255),
    price            BIGINT,
    rating           DECIMAL,
    date_time_create TIMESTAMP WITHOUT TIME ZONE,
    date_time_update TIMESTAMP WITHOUT TIME ZONE,
    category_id      BIGINT,
    tag_id           BIGINT,
    author_id        BIGINT
);

ALTER TABLE lab_course.courses
    ADD CONSTRAINT FK_COURSES_ON_AUTHOR FOREIGN KEY (author_id) REFERENCES lab_course.users (id);

ALTER TABLE lab_course.courses
    ADD CONSTRAINT FK_COURSES_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES lab_course.categories (id);

ALTER TABLE lab_course.courses
    ADD CONSTRAINT FK_COURSES_ON_TAG FOREIGN KEY (tag_id) REFERENCES lab_course.tags (id);


CREATE TABLE lab_course.sections
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    course_id BIGINT,
    title     VARCHAR(255)
   );

ALTER TABLE lab_course.sections
    ADD CONSTRAINT FK_SECTIONS_ON_COURSE FOREIGN KEY (course_id) REFERENCES lab_course.courses (id);

CREATE TABLE lab_course.objectives
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    course_id BIGINT,
    title     VARCHAR(255)
    );

ALTER TABLE lab_course.objectives
    ADD CONSTRAINT FK_OBJECTIVES_ON_COURSE FOREIGN KEY (course_id) REFERENCES lab_course.courses (id);

CREATE TABLE lab_course.modules
(
    id         BIGSERIAL PRIMARY KEY NOT NULL,
    section_id BIGINT,
    title      VARCHAR(255),
    video_link VARCHAR(255),
    duration   BIGINT
);

ALTER TABLE lab_course.modules
    ADD CONSTRAINT FK_MODULES_ON_SECTION FOREIGN KEY (section_id) REFERENCES lab_course.sections (id);

CREATE TABLE lab_course.user_course_link
(
    id        BIGSERIAL PRIMARY KEY NOT NULL,
    course_id BIGINT,
    user_id   BIGINT,
    progress  BIGINT
);

ALTER TABLE lab_course.user_course_link
    ADD CONSTRAINT FK_USER_COURSE_LINK_ON_COURSE FOREIGN KEY (course_id) REFERENCES lab_course.courses (id);

ALTER TABLE lab_course.user_course_link
    ADD CONSTRAINT FK_USER_COURSE_LINK_ON_USER FOREIGN KEY (user_id) REFERENCES lab_course.users (id);