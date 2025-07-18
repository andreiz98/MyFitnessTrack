TRUNCATE TABLE person RESTART IDENTITY CASCADE;

INSERT INTO person
  (name, username, date_of_birth, sex, weight, height, objective, activity)
VALUES
  ('user','urn','2000-01-01', 'M', 50 , 180,'cutting','very active'),
  ('user1','urn1','1998-01-01', 'F', 75.5, 165,'bulking','moderate'),
  ('user2','urn2','2011-01-01', 'm', 55.65, 178.6,'maintenance','light'),
  ('user3','urn3','2020-01-01', 'm', 55.65, 178.6,'maintenance','active');

--TRUNCATE TABLE products RESTART IDENTITY CASCADE;
--TRUNCATE TABLE product_info RESTART IDENTITY CASCADE;