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


