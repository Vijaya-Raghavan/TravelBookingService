CREATE TABLE user (id BIGINT, first_name VARCHAR(50) NOT NULL, last_name VARCHAR(50) NOT NULL, gender VARCHAR(1) NOT NULL, email VARCHAR(60) NOT NULL, phone VARCHAR(15) NOT NULL, password VARCHAR(255) NOT NULL);
ALTER TABLE user ADD CONSTRAINT user_pk PRIMARY KEY (id);
ALTER TABLE user ADD CONSTRAINT user_email_uq UNIQUE (email);
ALTER TABLE user ADD CONSTRAINT user_phone_uq UNIQUE (phone);
CREATE SEQUENCE user_id_seq START WITH 1 INCREMENT BY 1;

CREATE TABLE station (id INTEGER, name VARCHAR(50) NOT NULL, code VARCHAR(5) NOT NULL);
ALTER TABLE station ADD CONSTRAINT station_pk PRIMARY KEY (id);
ALTER TABLE station ADD CONSTRAINT station_code_uq UNIQUE (code);
CREATE SEQUENCE station_id_seq START WITH 101 INCREMENT BY 1;

CREATE TABLE train (number INTEGER, name VARCHAR(300) NOT NULL, source_station INTEGER NOT NULL, destination_station INTEGER NOT NULL, source_departure_time DATETIME NOT NULL, destination_arrival_time DATETIME NOT NULL);
ALTER TABLE train ADD CONSTRAINT train_pk PRIMARY KEY (number);
ALTER TABLE train ADD CONSTRAINT train_source_station_fk FOREIGN KEY (source_station) REFERENCES station (id);
ALTER TABLE train ADD CONSTRAINT train_destination_station_fk FOREIGN KEY (destination_station) REFERENCES station (id);
CREATE SEQUENCE train_number_seq START WITH 60001 INCREMENT BY 1;

CREATE TABLE coach_type (id INTEGER, type VARCHAR(50));
ALTER TABLE coach_type ADD CONSTRAINT coach_type_pk PRIMARY KEY (id);
CREATE SEQUENCE coach_type_id_seq START WITH 11 INCREMENT BY 1;

CREATE TABLE train_coach (id INTEGER, train_number INTEGER NOT NULL, coach_name VARCHAR(5) NOT NULL, coach_position INTEGER NOT NULL, total_seats INTEGER NOT NULL, coach_id INTEGER NOT NULL);
ALTER TABLE train_coach ADD CONSTRAINT train_coach_pk PRIMARY KEY (id);
ALTER TABLE train_coach ADD CONSTRAINT train_coach_train_number_fk FOREIGN KEY (train_number) REFERENCES train (number);
ALTER TABLE train_coach ADD CONSTRAINT train_coach_coach_id_fk FOREIGN KEY (coach_id) REFERENCES coach_type (id);
CREATE SEQUENCE train_coach_id_seq START WITH 1001 INCREMENT BY 1;

CREATE TABLE train_price (train_number INTEGER NOT NULL, coach_id INTEGER NOT NULL, price DOUBLE NOT NULL);
ALTER TABLE train_price ADD CONSTRAINT train_price_pk PRIMARY KEY (train_number, coach_id);
ALTER TABLE train_price ADD CONSTRAINT train_price_train_number_fk FOREIGN KEY (train_number) REFERENCES train (number);
ALTER TABLE train_price ADD CONSTRAINT train_price_coach_id_fk FOREIGN KEY (coach_id) REFERENCES coach_type (id);

CREATE TABLE train_halt (train_number INTEGER NOT NULL, halt_station INTEGER NOT NULL, arrival_time DATETIME, departure_time DATETIME, distance_covered DOUBLE NOT NULL);
ALTER TABLE train_halt ADD CONSTRAINT train_halt_uq UNIQUE (train_number, halt_station);
ALTER TABLE train_halt ADD CONSTRAINT train_halt_train_number_fk FOREIGN KEY (train_number) REFERENCES train (number);
ALTER TABLE train_halt ADD CONSTRAINT train_halt_halt_station_fk FOREIGN KEY (halt_station) REFERENCES station (id);

CREATE TABLE travel_booking (id BIGINT, train_number INTEGER NOT NULL, user_id BIGINT NOT NULL, source_station INTEGER NOT NULL, destination_station INTEGER NOT NULL, travel_date DATE NOT NULL, booking_date TIMESTAMP NOT NULL, booking_fare DECIMAL NOT NULL, booking_status VARCHAR(50) NOT NULL, travel_status VARCHAR(50) NOT NULL);
ALTER TABLE travel_booking ADD CONSTRAINT travel_booking_pk PRIMARY KEY (id);
ALTER TABLE travel_booking ADD CONSTRAINT travel_booking_train_number_fk FOREIGN KEY (train_number) REFERENCES train (number);
ALTER TABLE travel_booking ADD CONSTRAINT travel_booking_user_id_fk FOREIGN KEY (user_id) REFERENCES user (id);
ALTER TABLE travel_booking ADD CONSTRAINT travel_booking_source_station_fk FOREIGN KEY (source_station) REFERENCES station (id);
ALTER TABLE travel_booking ADD CONSTRAINT travel_booking_destination_station_fk FOREIGN KEY (destination_station) REFERENCES station (id);
CREATE SEQUENCE travel_booking_id_seq START WITH 500001 INCREMENT BY 1;

CREATE TABLE passenger (id INTEGER, booking_id BIGINT NOT NULL, passenger_first_name VARCHAR(50) NOT NULL, passenger_last_name VARCHAR(50) NOT NULL, passenger_age INTEGER, coach_id INTEGER NOT NULL, seat_number INTEGER NOT NULL, travel_status VARCHAR(50) NOT NULL, seat_status VARCHAR(50) NOT NULL);
ALTER TABLE passenger ADD CONSTRAINT passenger_booking_id_fk FOREIGN KEY (booking_id) REFERENCES travel_booking (id);
ALTER TABLE passenger ADD CONSTRAINT passenger_coach_id_fk FOREIGN KEY (coach_id) REFERENCES train_coach (id);
ALTER TABLE passenger ADD CONSTRAINT passenger_booking_id_coach_id_seat_number_uq UNIQUE (booking_id, coach_id, seat_number);
ALTER TABLE passenger ADD CONSTRAINT passenger_pk PRIMARY KEY (id);
CREATE SEQUENCE passenger_id_seq START WITH 1 INCREMENT BY 1;

