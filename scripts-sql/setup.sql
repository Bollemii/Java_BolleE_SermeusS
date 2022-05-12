-- create database java_project;
use java_project;

CREATE TABLE person (
  person_id        int NOT NULL AUTO_INCREMENT PRIMARY KEY, 
  first_name       varchar(50) NOT NULL, 
  last_name        varchar(50) NOT NULL, 
  birth_date       date NOT NULL, 
  gender           char(1) NOT NULL, 
  type_person      varchar(50) NOT NULL, 
  is_professional  bit(1), 						-- player
  elo              int, 
  level            varchar(50), 				-- referee
  is_vip           bit(1)						-- visitor
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
  date_start    datetime NOT NULL, 
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
ALTER TABLE person ADD CONSTRAINT ck_person_554343 CHECK (elo >= 0);
ALTER TABLE result ADD CONSTRAINT fk_result_329181 FOREIGN KEY (player_id) REFERENCES person (person_id);
ALTER TABLE result ADD CONSTRAINT fk_result_383554 FOREIGN KEY (match_id) REFERENCES `match` (match_id) ON DELETE CASCADE;
ALTER TABLE result ADD CONSTRAINT ck_result_687546 CHECK (points >= 0);
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_647266 FOREIGN KEY (visitor_id) REFERENCES person (person_id);
ALTER TABLE reservation ADD CONSTRAINT fk_reservation_964687 FOREIGN KEY (match_id) REFERENCES `match` (match_id) ON DELETE CASCADE;
ALTER TABLE reservation ADD CONSTRAINT ck_reservation_354543 CHECK (cost >= 0);
ALTER TABLE reservation ADD CONSTRAINT ck_reservation_355435 CHECK (seat_number >= 0);
ALTER TABLE location ADD CONSTRAINT ck_location_335434 CHECK (nb_rows >= 0);
ALTER TABLE location ADD CONSTRAINT ck_location_413465 CHECK (nb_seats_per_row >= 0);
ALTER TABLE reward ADD CONSTRAINT ck_reward_535443 CHECK (cost >= 0);
ALTER TABLE tournament_reward ADD CONSTRAINT fk_tournament_513242 FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id);
ALTER TABLE tournament_reward ADD CONSTRAINT fk_tournament_298729 FOREIGN KEY (reward_id) REFERENCES reward (reward_id);
ALTER TABLE tournament_reward ADD CONSTRAINT ck_tournament_reward_454645 CHECK (place >= 1);
ALTER TABLE `match` ADD CONSTRAINT fk_match_290535 FOREIGN KEY (location_id) REFERENCES location (location_id);
ALTER TABLE `match` ADD CONSTRAINT fk_match_722680 FOREIGN KEY (tournament_id) REFERENCES tournament (tournament_id);
ALTER TABLE `match` ADD CONSTRAINT fk_match_207413 FOREIGN KEY (referee_id) REFERENCES person (person_id);
ALTER TABLE `match` ADD CONSTRAINT ck_match_876467 CHECK (duration >= 0);


-- person
    -- player
insert into person
values(101, 'Emilien', 'Bolle', '2002-03-29', 'M', 'Player', false, 1523, null, null);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Melvin', 'LaGrosseMerde', '2020-02-21', 'X', 'Player', false, 0);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Steven', 'Sermeus', '2001-05-23', 'M', 'Player', true, 2003);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Archibald', 'Bernard', '1961-08-22', 'M', 'Player', true, 4990);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Quentin', 'Jacques', '1962-03-22', 'M', 'Player', false, 8052);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Florian', 'Van Damme', '1990-08-06', 'M', 'Player', false, 128);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Eline', 'Aerts', '1975-12-28', 'F', 'Player', true, 1262);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Eloïse', 'Vervoort', '1994-02-22', 'F', 'Player', true, 7592);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Giulia', 'De Coninck', '2002-09-21', 'X', 'Player', false, 52);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Yana', 'Henry', '1963-03-05', 'F', 'Player', false, 4346);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Léa', 'Bogaerts', '1985-04-10', 'F', 'Player', true, 3742);


    -- referee
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Michel', 'Sardou', '1956-12-02', 'M', 'Referee', 'LigueA');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Toulp', 'Atou', '2000-01-30', 'M', 'Referee', 'LigueC');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Lisa', 'Wouters', '1977-11-30', 'F', 'Referee', 'Sous-ligue');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Linde', 'Remy', '1965-10-12', 'F', 'Referee', 'Régional');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Mehdi', 'Bodart', '1979-09-03', 'M', 'Referee', 'LigueE');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Eloïse', 'Roland', '1996-03-29', 'X', 'Referee', 'LigueA');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Océane', 'De Meyer', '1966-05-09', 'F', 'Referee', 'Sous-ligue');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Rayan', 'Amrani', '1973-05-17', 'M', 'Referee', 'LigueB');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Inès', 'Poncelet', '1973-04-11', 'X', 'Referee', 'Régional');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Jaspert', 'Vervoort', '1974-03-17', 'X', 'Referee', 'LigueZ');


    -- visitor
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Fabrice', 'Perpète', '1953-10-12', 'M', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Margot', 'Larivière', '1998-01-17', 'F', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Alexis', 'De Winter', '2002-06-12', 'M', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Zoé', 'Romedenne', '2003-05-22', 'F', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Robin', 'Léonard', '2002-01-01', 'X', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Siebe', 'Luyten', '2003-08-26', 'M', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Clémence', 'Van Hoof', '1968-11-10', 'F', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Lukas', 'Evrard', '2001-01-10', 'M', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Maxim', 'Jacobs', '1962-11-19', 'X', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Laura', 'Smet', '1985-08-20', 'F', 'Visitor', true);


-- tournament
insert into tournament(name, is_official)
values('Ping2022', true);
insert into tournament(name, is_official)
values('Foot2015', false);
insert into tournament(name, is_official)
values('Raquette2006', true);
insert into tournament(name, is_official)
values('Echec1998', false);
insert into tournament(name, is_official)
values('Hackaton2022', true);


-- location
insert into location(name, nb_rows, nb_seats_per_row)
values('Table3A5', 5, 20);
insert into location(name, nb_rows, nb_seats_per_row)
values('TerrainGauche', 2, 30);
insert into location(name, nb_rows, nb_seats_per_row)
values('CôtéDroit', 9, 7);
insert into location(name, nb_rows, nb_seats_per_row)
values('Salle18965', 13, 10);
insert into location(name, nb_rows, nb_seats_per_row)
values('PetitSalle', 2, 5);
insert into location(name, nb_rows, nb_seats_per_row)
values('SalleRouge', 9, 3);
insert into location(name, nb_rows, nb_seats_per_row)
values('SalleBleue', 5, 5);
insert into location(name, nb_rows, nb_seats_per_row)
values('TableRonde', 9, 9);
insert into location(name, nb_rows, nb_seats_per_row)
values('TableH2F', 7, 3);
insert into location(name, nb_rows, nb_seats_per_row)
values('SalleConcert', 4, 6);


-- match
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(1, 1, 112, '2022-03-12 15:03:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 1, 121, '2022-03-15 09:59:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(3, 2, 115, '2022-04-01 23:11:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(4, 2, 120, '2022-04-03 12:02:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(5, 3, 117, '2036-12-27 01:13:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(6, 3, 112, '1927-12-27 12:36:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 4, 119, '1944-03-30 17:57:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(3, 4, 121, '1968-03-26 17:14:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(8, 5, 116, '2019-06-04 22:26:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(9, 5, 117, '1992-04-03 18:48:00', true);


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
insert into reward(name, cost)
values('Bon cadeau', 200);
insert into reward(name, cost)
values('Restaurant', 85);
insert into reward(name, cost)
values('Chocolats', 20);
insert into reward(name, cost)
values('Bières', 35);
insert into reward(name, cost)
values('Ami', 1);
insert into reward(name, cost)
values('Maison', 100000);


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
