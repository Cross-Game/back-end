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
    (friendship_state, user_id, username, friend_user_id, friendship_start_date)
VALUES
    (2,1,'LeoSanto', 2, '2023-04-26' ),
    (2,1,'IsmaelMatheo', 3, '2023-04-26'),
    (2,1,'KakaLopz', 4, '2023-04-26'),
    (2,1,'Giahanna', 6, '2023-04-26'),
    (2,1,'hariken', 5, '2023-04-26'),

    (2,2,'matPeras', 1, '2023-04-26'),
    (2,2,'IsmaelMatheo', 3, '2023-04-26'),
    (2,2,'KakaLopz', 4, '2023-04-26'),
    (2,2,'Giahanna', 6, '2023-04-26'),
    (2,2,'hariken', 5, '2023-04-26'),

    (2,3,'matPeras', 1, '2023-04-26'),
    (2,3,'LeoSanto', 2, '2023-04-26'),
    (2,3,'KakaLopz', 4, '2023-04-26'),
    (2,3,'Giahanna', 6, '2023-04-26'),

    (2,4,'matPeras', 1, '2023-04-26'),
    (2,4,'LeoSanto', 2, '2023-04-26'),
    (2,4,'IsmaelMatheo', 3, '2023-04-26'),
    (2,4,'Giahanna', 6, '2023-04-26'),

    (2,5,'matPeras', 1, '2023-04-26'),
    (2,5,'LeoSanto', 2, '2023-04-26'),
    (2,5,'KakaLopz', 4, '2023-04-26');

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

INSERT INTO plataform
    (plataform_type)
VALUES
    ('NINTENDO'),
    ('XBOX'),
    ('PSN'),
    ('PC'),
    ('MOBILE');

INSERT INTO preference
    (preferences)
VALUES
    ('ITALIANA'), --1
    ('JAPONESA'), --2
    ('CHINESA'), --3
    ('BRASILEIRA'), --4
    ('MEXICANA'), --5
    ('INDIANA'), --6
    ('FAST_FOOD'), --7
    ('COREANA'), --8
    ('CAFES'), --9
    ('ACAO'), --10
    ('AVENTURA'), --11
    ('ANIMACAO'), --12
    ('COMEDIA'), --13
    ('CRIME'), --14
    ('DOCUMENTARIO'), --15
    ('DRAMA'), --16
    ('FANTASIA'), --17
    ('TERROR'), --18
    ('ROMANCE'), --19
    ('FICCAO_CIENTIFICA'), --20
    ('THRILLER'), --21
    ('FPS'), --22
    ('MOBA'), --23
    ('MMORPG'), --24
    ('RPG'), --25
    ('BATTLE_ROYALE'), --26
    ('RTS'), --27
    ('LUTA'), --28
    ('CARTA'), --29
    ('ROCK'), --30
    ('POP'), --31
    ('RAP'), --32
    ('ELETRONICA'), --33
    ('INDIE'), --34
    ('REGGAE'), --35
    ('SERTANEJO'), --36
    ('MPB'), --37
    ('JAZZ'), --38
    ('CLASSICA'), --39
    ('FUNK'), --40
    ('METAL'); --41
--
--INSERT INTO
--    games (game_name, game_genre)
--VALUES
--('LEAGUE OF LEGENDS', 'MOBA'),
--('TEAM FIGHT TATICS','RTS'),
--('VALORANT', 'FPS');
--
--INSERT INTO game_plataform
--    (game_id, plataform_id)
--VALUES
--    (1, 4),
--    (2, 4),
--    (2, 5),
--    (3, 4);
--
--INSERT INTO user_games
--    (game_id, user_id, is_favorite_game, user_nickname, gamer_id, skill_level, game_role)
--VALUES
--    (1, 1, false, 'junior', '#BR1', 'LOW', 'MID'),
--    (2, 1, true, 'junin', '#BR1', 'MEDIUM', 'DUELIST'),
--    (2, 2, false, 'dididie', '#pignite', 'HIGH', 'SENTINEL'),
--    (1, 3, true, 'xaropinho', '#BR1', 'MEDIUM', 'ADC'),
--    (2, 3, false, 'xaropinho', '#BR1', 'LOW', 'CONTROLLER'),
--    (1, 5, true, 'hariken', '#BR1', 'MEDIUM', 'JUNGLE'),
--    (2, 5, false, 'hariken', '#BR1', 'MEDIUM', 'SUPPORT');
--
--INSERT INTO user_preferences
--    (user_id, preference_id)
--VALUES
--    (1,4),
--    (1,11),
--    (1,23),
--    (1,31),
--    (2,4),
--    (2,7),
--    (2,20),
--    (2,27),
--    (2,36),
--    (3,1),
--    (3,20),
--    (3,11),
--    (3,25),
--    (3,24),
--    (3,30),
--    (4,4),
--    (4,7),
--    (4,12),
--    (4,23),
--    (4,31),
--    (5,2),
--    (5,18),
--    (5,22),
--    (5,41),
--    (6,2),
--    (6,18),
--    (6,22),
--    (6,41);
--
---- Inserção de dados para a tabela generic_game
--INSERT INTO generic_game (id, game_name, cover_id)
--VALUES
--    (1, 'Garena Free Fire', 236311),
--    (2, 'League of Legends', 199459),
--    (3, 'Valorant', 122969),
--    (4, 'Counter-Strike', 157207),
--    (5, 'Dota 2', 298922),
--    (6, 'FIFA 22', 157846);
--
---- Inserção de dados para a tabela game_platforms_type
--INSERT INTO game_platforms_type (game_id, platforms_type)
--VALUES
--    (1, 'MOBILE'),
--    (2, 'PC'),
--    (3, 'PC'),
--    (4, 'PC'),
--    (5, 'PC'),
--    (6, 'PC'),
--    (6, 'PLAYSTATION'),
--    (6, 'XBOX');
--
--
---- Inserção de dados para a tabela game_genre_type
--INSERT INTO game_genre_type (game_id, game_genres)
--VALUES
--    (1, 'AVENTURA'),
--    (2, 'RPG'),
--    (2, 'ESTRATEGIA'),
--    (2, 'MOBA'),
--    (3, 'TIRO'),
--    (3, 'TATICO'),
--    (4, 'TIRO'),
--    (5, 'ESTRATEGIA'),
--    (5, 'MOBA'),
--    (6, 'SIMULACAO'),
--    (6, 'ESPORTES');
--
---- Inserção de dados para a tabela game_genres_id
--INSERT INTO game_genres_id (game_id, genre_id)
--VALUES
--    (1, 31),
--    (2, 12),
--    (2, 15),
--    (2, 36),
--    (3, 5),
--    (3, 24),
--    (4, 5),
--    (5, 15),
--    (5, 36),
--    (6, 13),
--    (6, 14);
