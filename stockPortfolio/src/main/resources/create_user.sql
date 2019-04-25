CREATE USER 'stockPortfolio'@'localhost' IDENTIFIED BY 'stockPortfolio';

GRANT ALL PRIVILEGES ON * . * TO 'stockPortfolio'@'localhost';

#
ALTER USER 'stockPortfolio'@'localhost' IDENTIFIED WITH mysql_native_password BY 'stockPortfolio';