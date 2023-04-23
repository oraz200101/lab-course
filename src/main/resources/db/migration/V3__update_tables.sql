ALTER TABLE lab_course.tags ADD COLUMN category_id BIGINT,
         ADD CONSTRAINT FK_TAGS_ON_CATEGORY FOREIGN KEY (category_id) REFERENCES lab_course.categories (id);
ALTER TABLE lab_course.courses DROP CONSTRAINT FK_COURSES_ON_TAG, DROP COLUMN tag_id;