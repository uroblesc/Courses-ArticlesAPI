USE courses_articles;

CREATE TABLE courses (
    id BINARY(16) NOT NULL,
    title VARCHAR(255) NOT NULL,
    description VARCHAR(2048),
    created_at DATE,
    updated_at DATE,
    PRIMARY KEY (id)
);

CREATE TABLE articles (
    id BINARY(16) NOT NULL,
    course_id BINARY(16) NOT NULL,
    title VARCHAR(255) NOT NULL,
    content VARCHAR(2048),
    created_at DATE NOT NULL,
    updated_at DATE,
    PRIMARY KEY (id),
    FOREIGN KEY (course_id) REFERENCES courses(id)
);
