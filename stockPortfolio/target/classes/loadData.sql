LOAD DATA  INFILE 'stocks_history_file.csv' 
INTO TABLE STOCK_HISTORY
FIELDS TERMINATED BY ',' 
LINES TERMINATED BY '\n'
(STOCK_NAME,STOCK_VALUE,@DATE_OF_VALUE)
SET DATE_OF_VALUE = STR_TO_DATE(@DATE_OF_VALUE, '%d.%m.%Y');