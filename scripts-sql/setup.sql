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
values('Melvin', 'Parache', '2020-02-21', 'X', 'Player', false, 0);
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
values('Elo??se', 'Vervoort', '1994-02-22', 'F', 'Player', true, 7592);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Giulia', 'De Coninck', '2002-09-21', 'X', 'Player', false, 52);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('Yana', 'Henry', '1963-03-05', 'F', 'Player', false, 4346);
insert into person(first_name, last_name, birth_date, gender, type_person, is_professional, elo)
values('L??a', 'Bogaerts', '1985-04-10', 'F', 'Player', true, 3742);


    -- referee
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Michel', 'Sardou', '1956-12-02', 'M', 'Referee', 'LigueA');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Toulp', 'Atou', '2000-01-30', 'M', 'Referee', 'LigueC');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Lisa', 'Wouters', '1977-11-30', 'F', 'Referee', 'Sous-ligue');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Linde', 'Remy', '1965-10-12', 'F', 'Referee', 'R??gional');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Mehdi', 'Bodart', '1979-09-03', 'M', 'Referee', 'LigueE');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Elo??se', 'Roland', '1996-03-29', 'X', 'Referee', 'LigueA');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Oc??ane', 'De Meyer', '1966-05-09', 'F', 'Referee', 'Sous-ligue');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Rayan', 'Amrani', '1973-05-17', 'M', 'Referee', 'LigueB');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('In??s', 'Poncelet', '1973-04-11', 'X', 'Referee', 'R??gional');
insert into person(first_name, last_name, birth_date, gender, type_person, level)
values('Jaspert', 'Vervoort', '1974-03-17', 'X', 'Referee', 'LigueZ');


    -- visitor
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Fabrice', 'Perp??te', '1953-10-12', 'M', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Margot', 'Larivi??re', '1998-01-17', 'F', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Alexis', 'De Winter', '2002-06-12', 'M', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Zo??', 'Romedenne', '2003-05-22', 'F', 'Visitor', true);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Robin', 'L??onard', '2002-01-01', 'X', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Siebe', 'Luyten', '2003-08-26', 'M', 'Visitor', false);
insert into person(first_name, last_name, birth_date, gender, type_person, is_vip)
values('Cl??mence', 'Van Hoof', '1968-11-10', 'F', 'Visitor', false);
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
values('??chec1998', false);
insert into tournament(name, is_official)
values('Hackathon2022', true);


-- location
insert into location(name, nb_rows, nb_seats_per_row)
values('Table3A5', 5, 20);
insert into location(name, nb_rows, nb_seats_per_row)
values('TerrainGauche', 2, 30);
insert into location(name, nb_rows, nb_seats_per_row)
values('C??t??Droit', 9, 7);
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
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(1, 1, 112, '2022-03-12 15:03:00', 36, false, 'Match de qualification');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 1, 121, '2022-03-15 09:59:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(3, 2, 115, '2022-04-01 23:11:00', 75, false, 'Match de haut niveau');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(4, 2, 120, '2022-04-03 12:02:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(5, 3, 117, '2036-12-27 01:13:00', 126, true, 'Finale du tournoi de la Raquette en 2006');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(6, 3, 112, '1927-10-27 12:36:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 4, 119, '1944-03-30 17:57:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(3, 4, 121, '1968-03-26 17:14:00', 97, true, 'Finale du tournoi d\'??chec en 1998');
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(8, 5, 116, '2019-06-04 22:26:00', 64, true, 'Finale du tournoi Hackathon en 2022');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(9, 5, 117, '1992-04-03 18:48:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(1, 3, 112, '1972-04-08 11:12:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(3, 2, 115, '2019-06-20 12:11:00', 301, false, 'Match beaucoup trop long');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(5, 4, 117, '1903-08-02 08:56:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(9, 1, 114, '1965-05-27 10:22:00', true);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(6, 5, 120, '1991-03-19 23:32:00', 69, false, 'Match retour');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(2, 4, 113, '1996-12-06 11:50:00', false);
insert into `match`(location_id, tournament_id, referee_id, date_start, duration, is_final, comment)
values(7, 2, 119, '1931-01-08 06:37:00', 56, false, 'L\'arbitre ?? ??t?? malade');
insert into `match`(location_id, tournament_id, referee_id, date_start, is_final)
values(4, 1, 116, '2016-10-03 16:25:00', true);


-- result
insert into result(player_id, match_id, points)
values(101, 2, 23);
insert into result(player_id, match_id, points)
values(103, 2, 10);
insert into result(player_id, match_id, points)
values(102, 4, 105);
insert into result(player_id, match_id, points)
values(103, 4, 92);
insert into result(player_id, match_id, points)
values(111, 7, 35);
insert into result(player_id, match_id, points)
values(105, 7, 20);
insert into result(player_id, match_id, points)
values(107, 6, 0);
insert into result(player_id, match_id, points)
values(102, 6, 6);
insert into result(player_id, match_id, points)
values(109, 5, 19);
insert into result(player_id, match_id, points)
values(101, 5, 23);
insert into result(player_id, match_id)
values(104, 3);
insert into result(player_id, match_id)
values(111, 3);
insert into result(player_id, match_id)
values(105, 1);
insert into result(player_id, match_id)
values(106, 1);
insert into result(player_id, match_id)
values(110, 9);
insert into result(player_id, match_id)
values(108, 9);
insert into result(player_id, match_id)
values(105, 8);
insert into result(player_id, match_id)
values(106, 8);
insert into result(player_id, match_id)
values(110, 10);
insert into result(player_id, match_id)
values(101, 10);


-- reservation
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(122, 4, 'parterre', 'B', 9, 52.36);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(129, 1, 'gradin', 'D', 11, 14.53);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(131, 3, 'invit??', 'G', 3, 0);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(125, 2, 'gradin', 'E', 1, 12.7);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(123, 1, 'parterre', 'C', 14, 10.09);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(126, 7, 'gradin', 'A', 14, 5.36);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(127, 9, 'vip', 'F', 5, 100.95);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(130, 10, 'parterre', 'A', 2, 2);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(124, 16, 'vip', 'B', 17, 135.19);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(122, 8, 'parterre', 'H', 5, 2.01);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(128, 15, 'gradin', 'F', 2, 92.65);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(129, 15, 'gradin', 'C', 1, 90.72);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(124, 12, 'vip', 'D', 4, 127.12);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(122, 15, 'prestige', 'B', 3, 175.37);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(123, 12, 'prestige', 'G', 6, 167.53);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(131, 1, 'vip', 'E', 20, 113.34);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(131, 16, 'parterre', 'A', 27, 6.33);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(130, 4, 'gradin', 'L', 7, 33.29);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(127, 13, 'prestige', 'B', 4, 189.52);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(125, 10, 'gradin', 'D', 1, 86.49);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(127, 12, 'parterre', 'C', 7, 11.71);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(126, 6, 'gradin', 'E', 2, 88.33);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(131, 6, 'vip', 'I', 1, 119.79);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(125, 16, 'parterre', 'A', 17, 21.5);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(123, 7, 'gradin', 'A', 12, 30.96);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(124, 1, 'prestige', 'C', 20, 175.81);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(123, 5, 'gradin', 'B', 4, 40.48);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(128, 8, 'vip', 'H', 7, 124.04);


-- reward
insert into reward(name, cost)
values('Ordinateur', 2123.2);
insert into reward(name, cost)
values('Bon cadeau', 50);
insert into reward(name, cost)
values('Voyage', 700);
insert into reward(name, cost)
values('Porte cl??', 2.5);
insert into reward(name, cost)
values('Bon cadeau', 200);
insert into reward(name, cost)
values('Restaurant', 85);
insert into reward(name, cost)
values('Chocolats', 20);
insert into reward(name, cost)
values('Bi??res', 35);
insert into reward(name, cost)
values('Ami', 1);
insert into reward(name, cost)
values('Maison', 100000);


-- tournament_reward
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 1, 5);
insert into tournament_reward(place, tournament_id, reward_id)
values(2, 1, 3);
insert into tournament_reward(place, tournament_id, reward_id)
values(3, 2, 4);
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 2, 9);
insert into tournament_reward(place, tournament_id, reward_id)
values(2, 3, 2);
insert into tournament_reward(place, tournament_id, reward_id)
values(3, 3, 1);
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 4, 10);
insert into tournament_reward(place, tournament_id, reward_id)
values(2, 4, 8);
insert into tournament_reward(place, tournament_id, reward_id)
values(3, 5, 6);
insert into tournament_reward(place, tournament_id, reward_id)
values(1, 5, 7);
