ALTER TABLE lab_course.objectives
    DROP CONSTRAINT fk_objectives_on_course,
    ADD CONSTRAINT FK_OBJECTIVES_ON_COURSE FOREIGN KEY (course_id) REFERENCES lab_course.courses ON DELETE CASCADE;
