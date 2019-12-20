### Database

use MYSQL Database server
create database with any name in mysql
run db.sql int database terminal

###Application.properties file
add database properties in application.property

server.port=<port no>
driverName=com.mysql.jdbc.Driver
dbUrl=<database url>
username=user name
password=password
hbmToDdl=none
hibernateDilact=org.hibernate.dialect.MySQL5Dialect

###source code

get the source code from 

https://github.com/ashokkumarjagarwal/java_assignment

#API

###register user in bulk

Path: host:port/register
HTTP Method:POST
Content-Type: multipart/form-data
form-data; name="file"; filename="userSample.csv

response:
{
    "noOfrowParsed": 8,
    "noOfrowFailed": 6,
    "noOfUserCreated": 4,
    "errorFileUrl": "error.csv"
}

###download error file

Path: host:port//download/{file}
HTTP Method:GET








