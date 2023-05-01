ALTER TABLE lab_course.modules DROP CONSTRAINT fk_modules_on_section,
    ADD CONSTRAINT fk_modules_on_section FOREIGN KEY (section_id) REFERENCES lab_course.sections (id) ON DELETE CASCADE ;