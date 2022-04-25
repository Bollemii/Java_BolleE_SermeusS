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