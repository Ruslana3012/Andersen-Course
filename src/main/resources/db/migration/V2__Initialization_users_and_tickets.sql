INSERT INTO users (name)
VALUES ('Ruslana');

INSERT INTO users (name)
VALUES ('Oleh');

INSERT INTO tickets (ticket_type, user_id)
VALUES ('DAY', (SELECT id FROM users WHERE name='Ruslana'));

INSERT INTO tickets (ticket_type, user_id)
VALUES ('YEAR', (SELECT id FROM users WHERE name='Ruslana'));

