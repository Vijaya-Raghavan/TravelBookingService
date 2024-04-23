INSERT INTO user (id, first_name, last_name, gender, email, phone, password) VALUES (user_id_seq.nextval, 'vijayaraghavan', 'n', 'M', 'vijayaraghavan1805@gmail.com', '+919940643165', 'cb64b85910ffb1cac69e59d2a349a98deba06cd4bd88be1dc7f1cb3704030d21bb42befd9b79f845582b664134193424');

INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'LONDON', 'LON');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'ASHFORD', 'ASH');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'FOLKSTONE', 'FOL');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'CALAIS', 'CAL');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'PARIS', 'PAR');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'ORLEANS', 'ORL');
INSERT INTO station (id, name, code) VALUES (station_id_seq.nextval, 'FRANCE', 'FRA');

INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'PARIS EXPRESS', 101, 105, TO_DATE('06:30:00', 'HH:MI:SS'), TO_DATE('09:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'PARIS EXPRESS', 101, 105, TO_DATE('09:30:00', 'HH:MI:SS'), TO_DATE('12:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'PARIS SUPERFAST', 101, 105, TO_DATE('12:30:00', 'HH:MI:SS'), TO_DATE('15:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'PARIS SUPERFAST', 101, 105, TO_DATE('15:30:00', 'HH:MI:SS'), TO_DATE('18:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'FRANCE SUPERFAST', 101, 107, TO_DATE('07:00:00', 'HH:MI:SS'), TO_DATE('11:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'FRANCE EXPRESS', 101, 107, TO_DATE('11:00:00', 'HH:MI:SS'), TO_DATE('15:00:00', 'HH:MI:SS'));
INSERT INTO train (number, name, source_station, destination_station, source_departure_time, destination_arrival_time) VALUES (train_number_seq.nextval, 'FRANCE SUPERFAST', 101, 107, TO_DATE('15:00:00', 'HH:MI:SS'), TO_DATE('19:00:00', 'HH:MI:SS'));

INSERT INTO coach_type (id, type) VALUES (coach_type_id_seq.nextval, 'GENERAL');
INSERT INTO coach_type (id, type) VALUES (coach_type_id_seq.nextval, 'AIR_CONDITIONED');

INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60001, 'G1', 1, 30, 11);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60001, 'A1', 2, 20, 12);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60002, 'A1', 1, 20, 12);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60003, 'G1', 1, 30, 11);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60003, 'A1', 2, 20, 12);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60004, 'A1', 1, 20, 12);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60005, 'G1', 1, 30, 11);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60005, 'A1', 2, 20, 12);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60006, 'G1', 1, 30, 11);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60007, 'G1', 1, 30, 11);
INSERT INTO train_coach (id, train_number, coach_name, coach_position, total_seats, coach_id) VALUES (train_coach_id_seq.nextval, 60007, 'A1', 2, 20, 12);

INSERT INTO train_price (train_number, coach_id, price) VALUES (60001, 11, 3.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60001, 12, 5.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60002, 12, 5.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60003, 11, 3.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60003, 12, 5.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60004, 12, 5.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60005, 11, 3.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60005, 12, 5.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60006, 11, 3.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60007, 11, 3.00);
INSERT INTO train_price (train_number, coach_id, price) VALUES (60007, 12, 5.00);

INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60001, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60001, 102, 100.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60001, 103, 50.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60001, 104, 50.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60001, 105, 85.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60002, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60002, 103, 150.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60002, 105, 135.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60003, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60003, 105, 285.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60004, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60004, 105, 285.00);

INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60005, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60005, 105, 285.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60005, 107, 75.00);

INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 102, 100.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 103, 50.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 104, 50.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 105, 85.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 106, 25.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60006, 107, 50.00);

INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60007, 101, 0.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60007, 105, 285.00);
INSERT INTO train_halt (train_number, halt_station, distance_covered) VALUES (60007, 107, 75.00);
