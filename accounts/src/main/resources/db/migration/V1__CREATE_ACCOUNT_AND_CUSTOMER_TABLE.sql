CREATE TABLE IF NOT EXISTS customer (
  customer_id serial PRIMARY KEY,
  customer_name varchar(100) NOT NULL,
  email varchar(100) NOT NULL,
  mobile_number varchar(20) NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS accounts (
  account_number serial PRIMARY KEY,
  customer_id int NOT NULL,
  account_type varchar(100) NOT NULL,
  branch_address varchar(200) NOT NULL,
  created_at date NOT NULL,
  created_by varchar(20) NOT NULL,
  updated_at date DEFAULT NULL,
  updated_by varchar(20) DEFAULT NULL,
  FOREIGN KEY (customer_id) REFERENCES customer(customer_id)
);