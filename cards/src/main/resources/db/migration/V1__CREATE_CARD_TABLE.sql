CREATE TABLE IF NOT EXISTS card (
  card_id serial PRIMARY KEY,
  mobile_number varchar(100) NOT NULL,
  card_number varchar(100) NOT NULL,
  card_type varchar(100) NOT NULL,
  total_limit int NOT NULL,
  available_amount int NOT NULL,
  amount_used int NOT NULL
);