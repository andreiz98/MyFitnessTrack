TRUNCATE TABLE person RESTART IDENTITY;

INSERT INTO person
  (name, lastname, username, date_of_birth, sex, weight, height,body_fat)
VALUES
  ('user', 'ln','urn','2018-01-01', 'M', 50 , 180, null),
  ('user1','ln1','urn1','2018-01-01', 'F', 75.5, 165, null),
  ('user2','ln2','urn2','2018-01-01', 'm', 55.65, 178.6,21),
  ('user3','ln3','urn3','2018-01-01', 'm', 55.65, 178.6,21.76);

--TRUNCATE TABLE products RESTART IDENTITY CASCADE;
--TRUNCATE TABLE product_info RESTART IDENTITY CASCADE;