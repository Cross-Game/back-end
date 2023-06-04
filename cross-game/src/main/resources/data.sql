INSERT INTO users
    (email, password, role, username, is_online)
VALUES
    ('mateus.silva@gmail.com', '$2a$12$73QByp1f.BTxe2Mku5TrCediY6zjw9BxpEryBhPDrCC15AHWk2r/m','ADMIN','matPeras', false),
    ('leornado.santos@gmail.com', '$2a$12$73QByp1f.BTxe2Mku5TrCediY6zjw9BxpEryBhPDrCC15AHWk2r/m','ADMIN','LeoSanto', false),
    ('mateus.ismael@gmail.com', '$2a$12$73QByp1f.BTxe2Mku5TrCediY6zjw9BxpEryBhPDrCC15AHWk2r/m','ADMIN','IsmaelMatheo',false),
    ('katia.lopez@gmail.com', '$2a$12$73QByp1f.BTxe2Mku5TrCediY6zjw9BxpEryBhPDrCC15AHWk2r/m','ADMIN','KakaLopz',false),
    ('lucash@gmail.com', '$2a$12$n1nV3cr1FlpkLFDuLTdYf.zwxLpWIdAHQvl/Kfi8E0DxOfjuUlvFm','ADMIN','hariken',false),
    ('giovanna.silva@gmail.com', '$2a$12$73QByp1f.BTxe2Mku5TrCediY6zjw9BxpEryBhPDrCC15AHWk2r/m','ADMIN','Giahanna',false);

INSERT INTO friends
    (friendship_state, user_id, username, friendship_start_date)
VALUES
    (2,1,'LeoSanto','2023-04-26' ),
    (2,1,'IsmaelMatheo', '2023-04-26'),
    (2,1,'KakaLopz','2023-04-26'),
    (2,1,'Giahanna','2023-04-26'),
    (2,1,'hariken','2023-04-26'),

    (2,2,'matPeras','2023-04-26'),
    (2,2,'IsmaelMatheo','2023-04-26'),
    (2,2,'KakaLopz','2023-04-26'),
    (2,2,'Giahanna','2023-04-26'),
    (2,2,'hariken','2023-04-26'),

    (2,3,'matPeras','2023-04-26'),
    (2,3,'LeoSanto','2023-04-26'),
    (2,3,'KakaLopz','2023-04-26'),
    (2,3,'Giahanna','2023-04-26'),

    (2,4,'matPeras','2023-04-26'),
    (2,4,'LeoSanto','2023-04-26'),
    (2,4,'IsmaelMatheo','2023-04-26'),
    (2,4,'Giahanna','2023-04-26'),

    (2,5,'matPeras','2023-04-26'),
    (2,5,'LeoSanto','2023-04-26'),
    (2,5,'KakaLopz','2023-04-26'),
    (2,5,'LeoSanto','2023-04-26');

INSERT INTO feedbacks
    (behavior, skill,feedback_given_date,feedback_text, user_given_feedback, user_id)
VALUES
    (5,4,'2023-04-26','Ele é um bom jogador','LeoSanto',1),
    (2,5,'2023-04-26','Gostei de jogar com ele','IsmaelMatheo',1),
    (4,2,'2023-04-26','Cara gente boa','KakaLopz',1),
    (3,4,'2023-04-26','Pro Player','Giahanna',1),

    (1,4,'2023-04-26','Muito revoltadinho, merece ban','matPeras',2),
    (3,4,'2023-04-26','Esse menino xinga muito','IsmaelMatheo',2),
    (2,4,'2023-04-26','Fala muito palavrão','KakaLopz',2),
    (4,3,'2023-04-26','Gostei da nossa jornada','Giahanna',2),

    (1,4,'2023-04-26','Muito revoltadinho, merece ban','matPeras',5),
    (3,4,'2023-04-26','Esse menino xinga muito','IsmaelMatheo',5),
    (2,4,'2023-04-26','Fala muito palavrão','KakaLopz',5),
    (4,3,'2023-04-26','Gostei da nossa jornada','Giahanna',5);

INSERT INTO plataform (plataform_type)
VALUES
('NINTENDO'),
('XBOX'),
('PSN'),
('PC'),
('MOBILE');

INSERT INTO preference
(food, movie_genre, series_genre, game_genre, user_id)
VALUES
('BRASILEIRA', 'ANIMATION', 'FANTASY', 'RTS', 1),
('BRASILEIRA', 'FANTASY', 'FANTASY', 'RPG', 2),
('JAPONESA', 'ACTION', 'COMEDY', 'MOBA', 3),
('MEXICANA', 'HORROR', 'HORROR', 'MMORPG', 5);

INSERT INTO games (game_name, game_genre)
VALUES
('LEAGUE OF LEGENDS', 'MOBA'),
('VALORANT', 'FPS');

INSERT INTO game_plataform (game_id, plataform_id)
VALUES
(1, 4),
(2, 4);

INSERT INTO user_games
(game_id, user_id, is_favorite_game, user_nickname, gamer_id, skill_level, game_role)
VALUES
(1, 1, false, 'junior', '#BR1', 'LOW', 'MID'),
(2, 1, true, 'junin', '#BR1', 'MEDIUM', 'DUELIST'),
(2, 2, false, 'dididie', '#pignite', 'HIGH', 'SENTINEL'),
(1, 3, true, 'xaropinho', '#BR1', 'MEDIUM', 'ADC'),
(2, 3, false, 'xaropinho', '#BR1', 'LOW', 'CONTROLLER'),
(1, 5, true, 'hariken', '#BR1', 'MEDIUM', 'JUNGLE'),
(2, 5, false, 'hariken', '#BR1', 'MEDIUM', 'SUPPORT');