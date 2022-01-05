create table QuestionType
(
    questionTypeId int         not null
        primary key,
    which_type     varchar(45) not null
);

create table Role
(
    id   int auto_increment,
    type varchar(45) null,
    constraint id_UNIQUE
        unique (id)
);

alter table Role
    add primary key (id);

create table Users
(
    user_id  int auto_increment,
    username varchar(50) not null,
    password varchar(50) not null,
    email_id varchar(50) not null,
    role_id  int         not null,
    constraint user_id_UNIQUE
        unique (user_id),
    constraint username_UNIQUE
        unique (username)
);

alter table Users
    add primary key (user_id);

create table Announcements
(
    id                int auto_increment,
    instructor_id     int          not null,
    announcement_text varchar(250) not null,
    created_at        bigint       not null,
    constraint id_UNIQUE
        unique (id),
    constraint inst_id
        foreign key (instructor_id) references Users (user_id)
);

create index instructor_id_idx
    on Announcements (instructor_id);

alter table Announcements
    add primary key (id);

create table Classroom
(
    id            int auto_increment,
    instructor_id int not null,
    constraint id_UNIQUE
        unique (id),
    constraint instructor_id
        foreign key (instructor_id) references Users (user_id)
);

create index instructor_id_idx
    on Classroom (instructor_id);

alter table Classroom
    add primary key (id);

create table Exams
(
    exam_id     varchar(100) not null,
    title       varchar(100) not null,
    score       int          not null,
    creator_id  int          not null,
    starting_at bigint       not null,
    ending_at   bigint       not null,
    created_at  bigint       not null,
    constraint exam_id_UNIQUE
        unique (exam_id),
    constraint creator_id
        foreign key (creator_id) references Users (user_id)
            on delete cascade
);

create index creator_id_idx
    on Exams (creator_id);

alter table Exams
    add primary key (exam_id);

create table Who_can_see
(
    id       int auto_increment,
    who_sees varchar(50) not null,
    constraint id_UNIQUE
        unique (id),
    constraint who_sees_UNIQUE
        unique (who_sees)
);

alter table Who_can_see
    add primary key (id);

create table Questions
(
    question_id     int auto_increment,
    question_type   int          not null,
    creator_exam_id int          not null,
    question_text   varchar(250) not null,
    points          int          not null,
    exam_id         varchar(100) not null,
    is_active       int          not null,
    who_can_see     int          not null,
    constraint question_id_UNIQUE
        unique (question_id),
    constraint creator_exam_id
        foreign key (creator_exam_id) references Users (user_id),
    constraint exam_id
        foreign key (exam_id) references Exams (exam_id)
            on delete cascade,
    constraint who_can_see
        foreign key (who_can_see) references Who_can_see (id)
);

create index who_can_see_idx
    on Questions (who_can_see);

alter table Questions
    add primary key (question_id);

create table Answer_key
(
    id             int auto_increment,
    question_id    int not null,
    correct_answer int not null,
    constraint id_UNIQUE
        unique (id),
    constraint question_id
        foreign key (question_id) references Questions (question_id)
            on delete cascade
);

create index question_id_idx
    on Answer_key (question_id);

alter table Answer_key
    add primary key (id);

create table User_answer
(
    id          int auto_increment,
    user_id     int    not null,
    question_id int    not null,
    user_answer int    null,
    points      int    null,
    is_correct  int    null,
    answered_at bigint not null,
    constraint id_UNIQUE
        unique (id),
    constraint user_answer_ibfk_1
        foreign key (question_id) references Questions (question_id)
            on delete cascade,
    constraint user_answer_ibfk_2
        foreign key (user_id) references Users (user_id)
);

create index question_id
    on User_answer (question_id);

create index user_id
    on User_answer (user_id);

alter table User_answer
    add primary key (id);

create table classroom_student
(
    id           int auto_increment
        primary key,
    classroom_id int not null,
    student_id   int not null,
    constraint classroom_id
        foreign key (classroom_id) references Classroom (id),
    constraint student_id
        foreign key (student_id) references Users (user_id)
);

create table notifications
(
    id              int auto_increment,
    announcement_id int           not null,
    seen            int default 0 not null comment '0 not seen
1 seen',
    student_id      int           not null,
    constraint notifications_id_uindex
        unique (id),
    constraint announcement_id
        foreign key (announcement_id) references Announcements (id)
);

alter table notifications
    add primary key (id);

create table question_options
(
    id           int auto_increment,
    option_value varchar(45) not null,
    question_id  int         null,
    constraint id_UNIQUE
        unique (id)
);

alter table question_options
    add primary key (id);

create table user_answer_text
(
    id          int auto_increment
        primary key,
    question_id int          not null,
    user_answer varchar(250) null,
    user_id     int          not null,
    constraint q_id
        foreign key (question_id) references Questions (question_id)
            on delete cascade,
    constraint user_id
        foreign key (user_id) references Users (user_id)
);


