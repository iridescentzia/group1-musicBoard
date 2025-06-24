USE music_board_db;

CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(50) NOT NULL
);

CREATE TABLE music (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       title VARCHAR(100) NOT NULL,
                       artist VARCHAR(100) NOT NULL,
                       genre VARCHAR(50),
                       release_date DATE,
                       cover_url VARCHAR(255)
);

CREATE TABLE reviews (
                         id INT AUTO_INCREMENT PRIMARY KEY,
                         user_id INT,
                         music_id INT,
                         content TEXT,
                         rating INT,
                         created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                         FOREIGN KEY (user_id) REFERENCES users(id),
                         FOREIGN KEY (music_id) REFERENCES music(id)
);

CREATE TABLE comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          review_id INT,
                          user_id INT,
                          content TEXT,
                          created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
                          FOREIGN KEY (review_id) REFERENCES reviews(id),
                          FOREIGN KEY (user_id) REFERENCES users(id)
);


INSERT INTO users (id, username) VALUES
                                     (1, 'user1'),
                                     (2, 'user2'),
                                     (3, 'user3'),
                                     (4, 'user4'),
                                     (5, 'user5'),
                                     (6, 'user6'),
                                     (7, 'user7'),
                                     (8, 'user8');



INSERT INTO music (id, title, artist, genre, release_date, cover_url) VALUES
                                                                          (1, '그라데이션', '10cm', '발라드', '2023-08-01', 'https://image.bugsm.co.kr/album/images/500/40773/4077389.jpg'),
                                                                          (2, 'DREAMLIKE', '피엘', '알앤비', '2023-10-17', 'https://a1.mzstatic.com/r40/Music221/v4/e3/5f/14/e35f14ff-870a-227e-3adb-2bb629a107ae/888272094459_cover.jpg'),
                                                                          (3, '포옹', '선우정아', '인디', '2022-06-12', 'https://a1.mzstatic.com/r40/Music116/v4/df/0a/a6/df0aa64d-f004-16f4-0e1e-df44c7e6b9d6/cover_KM0019126_1.jpg'),
                                                                          (4, 'only', '이하이', '소울', '2021-03-05', 'https://a1.mzstatic.com/r40/Music125/v4/d9/5a/a9/d95aa943-0e9b-ae75-3157-ab372f5b831a/8809704422604_Cover.jpg'),
                                                                          (5, 'THUNDER', '세븐틴', '댄스', '2024-01-12', 'https://a1.mzstatic.com/r40/Music221/v4/6c/12/b2/6c12b206-21f3-5ef3-6137-bbe830cf7187/198704485107_Cover.jpg'),
                                                                          (6, 'HANDS UP', 'MEOVV', '힙합', '2023-07-22', 'https://a1.mzstatic.com/r40/Music211/v4/79/72/6d/79726d82-243b-8fff-52c2-302204a21d70/8800303089540_cover.jpg'),
                                                                          (7, '반딧불', '도영', '발라드', '2022-11-10', 'https://a1.mzstatic.com/r40/Music211/v4/88/42/27/8842274f-5d07-8fdb-92bb-e526f9a829f0/cover.jpg'),
                                                                          (8, 'Firefly', '엔플라잉', '락', '2023-04-04', 'https://a1.mzstatic.com/r40/Music122/v4/08/93/f8/0893f86e-d82b-7b53-6f6a-4dcc3daa30f6/cover_KM0016221_1.jpg'),
                                                                          (9, 'gameboy', '로제', '팝', '2023-09-16', 'https://i.namu.wiki/i/tXj9NdwVaKj96EzSYqWagmRK9bgvEIViiEF3H7ws1gMcinJ50FTJ9bNjmfIOd5JnjRLI1UWp2QInWwfGmCtOTA.webp'),
                                                                          (10, 'Completey', '재현', '알앤비', '2023-02-14', 'https://i.namu.wiki/i/CxvkW2Qx5_vuLZYfG0kiOxk80b_QFGUODBMryhzgfNhDNItrmbnSlZoyqlzSP41_h1vddzhcXVLQ04xnALGRJQ.webp'),
                                                                          (11, 'twilight zone', 'Ariana Grande', '팝', '2024-01-01', 'https://images.genius.com/c1c6d83dc732ffcc4fe043680cb92cbb.1000x1000x1.png'),
                                                                          (12, 'The Man', 'Taylor Swift', '팝', '2020-03-08', 'https://a1.mzstatic.com/r40/Music124/v4/16/40/c0/1640c01e-3763-3638-6ad9-984eab741580/20UMGIM11059.rgb.jpg');



INSERT INTO reviews (id, user_id, music_id, content, rating, created_at) VALUES
                                                                             (1, 2, 4, '감성적인 멜로디가 인상적이에요. 👍', 4, '2025-05-01 08:25:48'),
                                                                             (2, 3, 3, '목소리가 따뜻해서 위로가 됐어요.', 5, '2025-04-28 08:25:48'),
                                                                             (3, 4, 5, '리듬이 경쾌하고 중독성 있어요.', 3, '2025-04-24 08:25:48'),
                                                                             (4, 5, 1, '10cm 특유의 감성이 그대로네요!', 4, '2025-04-21 08:25:48'),
                                                                             (5, 6, 6, '힙합 감성 제대로입니다.', 5, '2025-04-19 08:25:48'),
                                                                             (6, 7, 2, '도입부가 너무 좋아요!', 4, '2025-04-18 08:25:48'),
                                                                             (7, 8, 7, '감성 돋는 밤에 듣기 좋아요.', 5, '2025-04-17 08:25:48'),
                                                                             (8, 1, 8, '밴드 사운드가 매력적이네요.', 4, '2025-04-15 08:25:48'),
                                                                             (9, 2, 9, '로제 목소리 너무 좋아요!', 5, '2025-04-12 08:25:48'),
                                                                             (10, 3, 10, '재현의 감성 표현력이 짱이에요.', 4, '2025-04-10 08:25:48'),
                                                                             (11, 4, 11, '트와일라잇존 느낌 제대로네요.', 4, '2025-04-09 08:25:48'),
                                                                             (12, 5, 12, '테일러의 메시지가 느껴져요.', 5, '2025-04-08 08:25:48'),
                                                                             (13, 6, 1, '그라데이션 곡 너무 좋다.', 4, '2025-04-06 08:25:48'),
                                                                             (14, 7, 2, 'DREAMLIKE 정말 dreamy 해요.', 3, '2025-04-04 08:25:48'),
                                                                             (15, 8, 3, '포옹 듣고 울컥했어요.', 5, '2025-04-03 08:25:48'),
                                                                             (16, 1, 4, 'only는 정말 소울풀하네요.', 4, '2025-03-30 08:25:48'),
                                                                             (17, 2, 5, '세븐틴 곡은 늘 신나요!', 4, '2025-03-29 08:25:48'),
                                                                             (18, 3, 6, 'HANDS UP은 파워풀해요.', 5, '2025-03-27 08:25:48'),
                                                                             (19, 4, 7, '반딧불 진짜 힐링곡이에요.', 5, '2025-03-25 08:25:48'),
                                                                             (20, 5, 8, '엔플라잉 진짜 실력파네요.', 4, '2025-03-23 08:25:48');



INSERT INTO comments (id, review_id, user_id, content, created_at) VALUES
                                                                       (1, 18, 1, '저도 그렇게 느꼈어요! #1', '2025-04-21 08:25:48'),
                                                                       (2, 17, 2, '저도 그렇게 느꼈어요! #2', '2025-05-07 08:25:48'),
                                                                       (3, 17, 4, '저도 그렇게 느꼈어요! #3', '2025-04-03 08:25:48'),
                                                                       (4, 1, 5, '저도 그렇게 느꼈어요! #4', '2025-05-03 08:25:48'),
                                                                       (5, 6, 1, '저도 그렇게 느꼈어요! #5', '2025-03-22 08:25:48'),
                                                                       (6, 11, 2, '저도 그렇게 느꼈어요! #6', '2025-04-12 08:25:48'),
                                                                       (7, 10, 6, '저도 그렇게 느꼈어요! #7', '2025-04-08 08:25:48'),
                                                                       (8, 3, 7, '저도 그렇게 느꼈어요! #8', '2025-04-17 08:25:48'),
                                                                       (9, 4, 3, '저도 그렇게 느꼈어요! #9', '2025-04-04 08:25:48'),
                                                                       (10, 2, 4, '저도 그렇게 느꼈어요! #10', '2025-04-02 08:25:48'),
                                                                       (11, 13, 8, '저도 그렇게 느꼈어요! #11', '2025-04-01 08:25:48'),
                                                                       (12, 5, 2, '저도 그렇게 느꼈어요! #12', '2025-03-31 08:25:48'),
                                                                       (13, 16, 4, '저도 그렇게 느꼈어요! #13', '2025-03-30 08:25:48'),
                                                                       (14, 15, 1, '저도 그렇게 느꼈어요! #14', '2025-03-29 08:25:48'),
                                                                       (15, 8, 3, '저도 그렇게 느꼈어요! #15', '2025-03-28 08:25:48'),
                                                                       (16, 6, 4, '저도 그렇게 느꼈어요! #16', '2025-03-27 08:25:48'),
                                                                       (17, 18, 5, '저도 그렇게 느꼈어요! #17', '2025-03-26 08:25:48'),
                                                                       (18, 20, 7, '저도 그렇게 느꼈어요! #18', '2025-03-25 08:25:48'),
                                                                       (19, 19, 6, '저도 그렇게 느꼈어요! #19', '2025-03-24 08:25:48'),
                                                                       (20, 7, 8, '저도 그렇게 느꼈어요! #20', '2025-03-23 08:25:48'),
                                                                       (21, 1, 6, '저도 그렇게 느꼈어요! #21', '2025-03-22 08:25:48'),
                                                                       (22, 2, 7, '저도 그렇게 느꼈어요! #22', '2025-03-21 08:25:48'),
                                                                       (23, 3, 8, '저도 그렇게 느꼈어요! #23', '2025-03-20 08:25:48'),
                                                                       (24, 4, 2, '저도 그렇게 느꼈어요! #24', '2025-03-19 08:25:48'),
                                                                       (25, 5, 3, '저도 그렇게 느꼈어요! #25', '2025-03-18 08:25:48');