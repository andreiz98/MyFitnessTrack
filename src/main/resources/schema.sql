CREATE TABLE if not exists person (
id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
name VARCHAR(100),
age INTEGER,
username VARCHAR(100),
date_of_birth DATE,
sex VARCHAR(1),
weight NUMERIC(5,2),
height NUMERIC(5,2),
body_fat NUMERIC(5,2),
activity VARCHAR(50),
objective VARCHAR(50),
calories_needed NUMERIC(5,2),
protein NUMERIC(5,2),
carbs NUMERIC(5,2),
fats NUMERIC(5,2));

CREATE TABLE if not exists products (
id Serial primary key,
name VARCHAR(100),
weight NUMERIC(5,2),
price NUMERIC(5,2));

CREATE TABLE if not exists product_stats (
id Serial primary key,
calories NUMERIC(5,2),
fats NUMERIC(5,2),
carbs NUMERIC(5,2),
sugar NUMERIC(5,2),
fiber NUMERIC(5,2),
protein NUMERIC(5,2),
salt NUMERIC(5,2));

CREATE TABLE if not exists products_updated (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255),
    weight NUMERIC(5,2),
    price NUMERIC(5,2)
);