# *Stock Portfolio Web App*

## Description:
REST API enabling traders to register and maintain their stock portfolio, receive
buying recommendations by various strategies and real-time information on the
stock market and their stock portfolio value

## configuration:
1. run the script files  from `\stockPortfolio\src\main\resources` on your MySQL
- put the "stocks_history_file.csv" on your MySQL "uploads" directory on
`C:\ProgramData\MySQL\MySQL Server 8.0\Uploads`
-"ProgramData" should be hidden (show hidden items to find it)
2. import the project as Maven project to your IDE 
3. right click on the project -> run as -> run on server
*verify that the port 8080 is free and the apache tomcat doesn't run as service 

the REST API server is up :)

## REST Routs and API :

### Creating a new user and inserting stockPortfolio:

URL:
`/api/addNewTrader`

Method: POST

Body content type: JSON

Expected body will contain a list of all owned stocks names as "stockName" 
and the amount as "stockAmount" like the example:
```
[
    {
        "stockName": "IDB",
        "stockAmount": 2
    }, 
    {
        "stockName": "TEVA",
        "stockAmount": 10
    }
]
```
the app returns the trader ID

**************************************************************

### Replacing the trader's stock portfolio:
URL:
`/api//trader/replace/{traderId}`

Method: PUT

Body content type: JSON

Expected body will contain a list of all owned stocks names as "stockName" 
and the amount as "stockAmount" like the example:
```
[
    {
        "stockName": "IDB",
        "stockAmount": 5
    },
    {
        "stockName": "TEVA",
        "stockAmount": 10
    }
]
```
**************************************************************

### Updating the trader's stocks:
URL:
`/api/trader/update/{traderId}`

Method: PUT

Body content type: JSON

Expected body will contain a list of already owned stocks and the respective amount 
of stocks sold/bought. 
the new amount will added to the current amount in the stock value 
sold amount should be with '-' before the amount , like the example:
```
[
   
   {
        "stockName": "IDB",
        "stockAmount": 5
    },
    {
        "stockName": "TEVA",
        "stockAmount": -7
    }
]
```
***************************************************************

### Returning the trader's total stock portfolio value:
URL:
`/api/trader/value/{traderId}`

Method: GET

The app returns the trader's total portfolio value

***************************************************************

### Returning the trader's stock portfolio:
URL:
`/api/trader/myPortfolio/{traderId}`

Method: GET

The app returns the trader's stock portfolio

***************************************************************

### Returning the stocks in the market:
URL:
`/api/stocksList`

Method: GET

The app returns the stock list which in the database

***************************************************************

### get recommendation by performance:
URL:
`/api/recommendations/performance/{traderId}`

Method: GET

The app returns an already owned stock, who raised the most in value during the
last 7 days.

***************************************************************

### get recommendation by "most stable":
URL:
`/api/recommendations/stable/{traderId}`

Method: GET

The app returns an already owned stock, with least value fluctuation during the last
7 days. For example, a stock whose max value was 4.5 and min value was 2.5 is
considered more stable than a stock whose max was 10 and min was 5.

***************************************************************
### get recommendation by "best stock":
URL:
`/api/recommendations/best/{traderId}`

Method: GET

The app returns a not already owned stock, whose current value is the highest among all
stocks.

***************************************************************

#### you can change the period of time by changing the variable "daysAgo" on the "StockPortfolioDaoImpl" class

