create database java_project;
use java_project;

CREATE TABLE person (
  person_id        int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  first_name       varchar(50) NOT NULL, 
  last_name        varchar(50) NOT NULL, 
  birth_date       date NOT NULL, 
  gender           char(1) NOT NULL, 
  type_person      varchar(50) NOT NULL, 
  is_professional  bit(1), 
  elo              int, 
  level            varchar(50), 
  is_vip           bit(1)
);

CREATE TABLE result (
  player_id int NOT NULL, 
  match_id  int NOT NULL, 
  points    int, 
  PRIMARY KEY (player_id, match_id)
);

CREATE TABLE `match` (
  match_id      int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  location_id   int NOT NULL, 
  tournament_id int NOT NULL, 
  referee_id    int NOT NULL, 
  date_start    date NOT NULL, 
  duration      int, 
  is_final      bit(1) NOT NULL, 
  comment       varchar(200)
);

CREATE TABLE location (
  location_id      int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  name             varchar(50) NOT NULL, 
  nb_rows          int NOT NULL, 
  nb_seats_per_row int NOT NULL
);

CREATE TABLE reward (
  reward_id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  name      varchar(50) NOT NULL, 
  cost      double NOT NULL
);

CREATE TABLE tournament (
  tournament_id int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  name          varchar(50) NOT NULL, 
  is_official   bit(1) NOT NULL
);

CREATE TABLE reservation (
  visitor_id  int NOT NULL, 
  match_id    int NOT NULL, 
  seat_type   varchar(50) NOT NULL, 
  seat_row    char(1) NOT NULL, 
  seat_number int NOT NULL, 
  cost        double NOT NULL, 
  PRIMARY KEY (visitor_id, match_id)
);

CREATE TABLE tournament_reward (
  place         int NOT NULL, 
  tournament_id int NOT NULL, 
  reward_id     int NOT NULL, 
  PRIMARY KEY (place, tournament_id, reward_id)
);

ALTER TABLE person ADD CONSTRAINT ck_person_253688 CHECK (gender in('M', 'F', 'X'));
ALTER TABLE person ADD CONSTRAINT ck_person_134641 CHECK (type_person in('Player', 'Referee', 'Visitor'));
ALTER TABLE result ADD CONSTRAINT fk_result_329181 FOREIGN KEY (player_id) REFERENCES person (person_id);
ALTER TABLE result ADD CONSTRAINT fk_result_383554 FOREIGN KEY (match_id) REFERENCES `match` (match_id) ON DELETE CASCADE;
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_647266 FOREIGN KEY (visitor_id) REFERENCES person (person_id);
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_964687 FOREIGN KEY (match_id) REFERENCES `match` (match_id) ON DELETE CASCADE;
ALTER TABLE tournament_reward ADD CONSTRAINT fk_tournament_513242 FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id);
ALTER TABLE tournament_reward ADD CONSTRAINT fk_tournament_298729 FOREIGN KEY (reward_id) REFERENCES reward (reward_id);
ALTER TABLE `match` ADD CONSTRAINT fk_match_290535 FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE `match` ADD CONSTRAINT fk_match_722680 FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id);
ALTER TABLE `match` ADD CONSTRAINT fk_match_207413 FOREIGN KEY (referee_id) REFERENCES person (person_id);


-- person
    -- player
insert into person
values(101, 'Emilien', 'Bolle', '2002-03-29', 'M', 'Player', false, 1523, null, null);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Tissia', 'Migeot', '2003-02-21', 'F', 'Player', false, 596);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Steven', 'Sermeus', '2001-05-23', 'M', 'Player', true, 2003);

    -- referee
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Michel', 'Sardou', '1956-12-02', 'M', 'Referee', 'LigueA');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Toulp', 'Atou', '2000-01-30', 'M', 'Referee', 'LigueC');


    -- visitor
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Fabrice', 'Perpète', '1953-10-12', 'M', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Margot', 'Larivière', '1998-01-17', 'F', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Simon', 'Albicocco', '2002-09-21', 'M', 'Visitor', true);

-- tournament
insert into tournament(name, is_official)
values('Ping2022', true);
insert into tournament(name, is_official)
values('Foot2015', false);


-- location
insert into location(name, nb_rows, nb_seats_per_row)
values('table3A5', 5, 20);
insert into location(name, nb_rows, nb_seats_per_row)
values('terrainGauche', 2, 30);


-- match
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(1, 1, 104, '2022-03-12', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 1, 104, '2022-03-15', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(1, 2, 105, '2022-04-01', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(1, 2, 105, '2022-04-03', true);


-- result
insert into result(player_id, match_id, points)
values(101, 2, 23);
insert into result(player_id, match_id, points)
values(103, 2, 10);
insert into result(player_id, match_id, points)
values(102, 4, 105);
insert into result(player_id, match_id, points)
values(103, 4, 92);
insert into result(player_id, match_id)
values(101, 3);
insert into result(player_id, match_id)
values(102, 3);


-- reservation
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(106, 4, 'parterre', 'B', 23, 52.36);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(107, 1, 'gradin', 'R', 11, 14.53);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(108, 3, 'vip', 'O', 3, 0);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(106, 2, 'gradin', 'T', 1, 12.7);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(106, 1, 'parterre', 'P', 14, 10.09);


-- reward
insert into reward(name, cost)
values('Ordinateur', 2123.2);
insert into reward(name, cost)
values('Bon cadeau', 50);
insert into reward(name, cost)
values('Voyage', 700);
insert into reward(name, cost)
values('Porte clé', 2.5);


-- tournament_reward
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 1, 1);
insert into tournament_reward(place, tournament_id, reward_id)
values(2, 1, 3);
insert into tournament_reward(place, tournament_id, reward_id)
values(3, 1, 4);
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 2, 2);
insert into tournament_reward(place, tournament_id, reward_id)
values(2, 2, 4);
