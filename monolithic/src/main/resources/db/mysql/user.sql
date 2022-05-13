CREATE DATABASE IF NOT EXISTS book_store;

ALTER DATABASE book_store
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON book_store.* TO 'bookstore@%' IDENTIFIED BY 'bookstore';
