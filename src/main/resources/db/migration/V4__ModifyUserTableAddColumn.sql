ALTER TABLE user
ADD COLUMN active BOOLEAN;

UPDATE user SET active = true