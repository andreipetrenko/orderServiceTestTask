CREATE TABLE users (
  id SERIAL PRIMARY KEY,
  login VARCHAR (50) UNIQUE NOT NULL,
  fio VARCHAR (512) NOT NULL,
  user_status VARCHAR (255) NOT NULL,
  CHECK (user_status in ('Online', 'Offline'))
);
CREATE SEQUENCE user_sq START 1;

CREATE TABLE orders (
  id SERIAL PRIMARY KEY,
  name VARCHAR (250) NOT NULL,
  description VARCHAR (1024),
  responsible_id INTEGER REFERENCES users (id),
  order_status VARCHAR (255) NOT NULL,
  CHECK (order_status in ('Открыто', 'Выполнено', 'Не выполнено'))
);
CREATE SEQUENCE order_sq START 1;