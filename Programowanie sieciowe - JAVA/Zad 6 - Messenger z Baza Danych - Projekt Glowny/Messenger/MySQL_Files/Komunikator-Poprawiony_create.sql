-- Created by Vertabelo (http://vertabelo.com)
-- Last modification date: 2016-01-07 22:40:22.721




-- tables
-- Table Banned
CREATE TABLE Banned (
    id int  NOT NULL,
    user_id int  NOT NULL,
    start timestamp  NOT NULL,
    expire timestamp  NOT NULL,
    info varchar(50)  NOT NULL,
    CONSTRAINT Banned_pk PRIMARY KEY (id)
);

-- Table City
CREATE TABLE City (
    c_id int  NOT NULL,
    name varchar(25)  NOT NULL,
    region_id int  NOT NULL,
    Region_r_id int  NOT NULL,
    CONSTRAINT City_pk PRIMARY KEY (c_id)
);

-- Table Country
CREATE TABLE Country (
    cntr_id int  NOT NULL,
    name varchar(25)  NOT NULL,
    CONSTRAINT Country_pk PRIMARY KEY (cntr_id)
);

-- Table Followers
CREATE TABLE Followers (
    user_id int  NOT NULL,
    follower_id int  NOT NULL
);

-- Table `Ignore`
CREATE TABLE `Ignore` (
    user_id int  NOT NULL,
    ignored_id int  NOT NULL
);

-- Table Looking_For
CREATE TABLE Looking_For (
    lf_id int  NOT NULL,
    value varchar(15)  NOT NULL,
    CONSTRAINT Looking_For_pk PRIMARY KEY (lf_id)
);

-- Table Mood
CREATE TABLE Mood (
    m_id int  NOT NULL,
    name varchar(20)  NOT NULL,
    CONSTRAINT Mood_pk PRIMARY KEY (m_id)
);

-- Table Msg
CREATE TABLE Msg (
    m_id int  NOT NULL,
    from_id int  NOT NULL,
    to_id int  NOT NULL,
    date timestamp  NOT NULL,
    msg varchar(200)  NOT NULL,
    CONSTRAINT Msg_pk PRIMARY KEY (m_id)
);

-- Table Region
CREATE TABLE Region (
    r_id int  NOT NULL,
    name varchar(25)  NOT NULL,
    country_id int  NOT NULL,
    Country_cntr_id int  NOT NULL,
    CONSTRAINT Region_pk PRIMARY KEY (r_id)
);

-- Table Statistics
CREATE TABLE Statistics (
    user_id int  NOT NULL,
    last_activity timestamp  NOT NULL,
    msgs_count int  NOT NULL,
    friends_count int  NOT NULL,
    hours_online int  NOT NULL,
    CONSTRAINT Statistics_pk PRIMARY KEY (user_id)
);

-- Table Status
CREATE TABLE Status (
    s_id int  NOT NULL,
    value varchar(15)  NOT NULL,
    CONSTRAINT Status_pk PRIMARY KEY (s_id)
);

-- Table Tags
CREATE TABLE Tags (
    id int  NOT NULL,
    name varchar(10)  NOT NULL,
    CONSTRAINT Tags_pk PRIMARY KEY (id)
);

-- Table User
CREATE TABLE User (
    id int  NOT NULL  AUTO_INCREMENT,
    name varchar(10)  NOT NULL,
    surname varchar(10)  NOT NULL,
    age int  NOT NULL,
    city_id int  NOT NULL,
    website varchar(50)  NOT NULL,
    mood_id int  NOT NULL,
    status_id int  NULL,
    about varchar(100)  NOT NULL,
    looking_for_id int  NOT NULL,
    likes int  NOT NULL,
    doesnt_like int  NOT NULL,
    login varchar(15)  NULL,
    password varchar(12)  NULL,
    email varchar(25)  NULL,
    City_c_id int  NOT NULL,
    Mood_m_id int  NOT NULL,
    Looking_For_lf_id int  NOT NULL,
    Status_s_id int  NOT NULL,
    Statistics_user_id int  NOT NULL,
    CONSTRAINT User_pk PRIMARY KEY (id)
);

-- Table User_Tags
CREATE TABLE User_Tags (
    user_id int  NOT NULL,
    tag_id int  NOT NULL
);

-- Table VIP
CREATE TABLE VIP (
    id int  NOT NULL,
    usier_id int  NOT NULL,
    start timestamp  NOT NULL,
    expire timestamp  NOT NULL,
    CONSTRAINT VIP_pk PRIMARY KEY (id)
);





-- foreign keys
-- Reference:  City_Region (table: City)

ALTER TABLE City ADD CONSTRAINT City_Region FOREIGN KEY City_Region (Region_r_id)
    REFERENCES Region (r_id);
-- Reference:  Region_Country (table: Region)

ALTER TABLE Region ADD CONSTRAINT Region_Country FOREIGN KEY Region_Country (Country_cntr_id)
    REFERENCES Country (cntr_id);
-- Reference:  User_City (table: User)

ALTER TABLE User ADD CONSTRAINT User_City FOREIGN KEY User_City (City_c_id)
    REFERENCES City (c_id);
-- Reference:  User_Looking_For (table: User)

ALTER TABLE User ADD CONSTRAINT User_Looking_For FOREIGN KEY User_Looking_For (Looking_For_lf_id)
    REFERENCES Looking_For (lf_id);
-- Reference:  User_Mood (table: User)

ALTER TABLE User ADD CONSTRAINT User_Mood FOREIGN KEY User_Mood (Mood_m_id)
    REFERENCES Mood (m_id);
-- Reference:  User_Statistics (table: User)

ALTER TABLE User ADD CONSTRAINT User_Statistics FOREIGN KEY User_Statistics (Statistics_user_id)
    REFERENCES Statistics (user_id);
-- Reference:  User_Status (table: User)

ALTER TABLE User ADD CONSTRAINT User_Status FOREIGN KEY User_Status (Status_s_id)
    REFERENCES Status (s_id);



-- End of file.

