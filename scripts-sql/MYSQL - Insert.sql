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