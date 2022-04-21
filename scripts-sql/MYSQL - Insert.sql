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
values(106, 5, 'parterre', 'B', 23, 52.36);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(107, 4, 'gradin', 'R', 11, 14.53);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(108, 6, 'vip', 'O', 3, 0);
insert into reservation(visitor_id, match_id, seat_type, seat_row, seat_number, cost)
values(106, 7, 'gradin', 'T', 1, 12.7);
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