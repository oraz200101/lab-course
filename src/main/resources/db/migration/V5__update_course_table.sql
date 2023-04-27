ALTER TABLE lab_course.courses
    ADD COLUMN totalHours BIGINT,
    ADD COLUMN language   varchar(255),
    ADD COLUMN subscription BOOLEAN;