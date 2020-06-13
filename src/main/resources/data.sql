DELETE FROM voting.vote;
DELETE FROM voting.topic;

INSERT INTO voting.topic (id, created_at, description, session_end) VALUES
(1, '2020-06-12 18:00:00', 'Decidir', '2020-06-12 20:00:00');

INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 66, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 234, 'NO');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 341, 'NO');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 385, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 491, 'NO');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 627, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 750, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 758, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 886, 'NO');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 925, 'YES');
INSERT INTO voting.vote (topic_id, user_id, vote) VALUES (1, 1000, 'YES');
