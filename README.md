# Zipcodes REST API - Demo

Load all zipcodes from a csv file and save them into a MySQL database:

Endpoint:

> GET /api/v1/zipcodes/load

Perform queries for zipcodes based on lookup parameters

Endpoints:

Return all zipcodes which have a total population within range provided by the client.

> GET /api/v1/zipcodes?minPopulation=100000&maxPopulation=200000

Return all zipcodes which have a median age within a range provided by the client.

> GET /api/v1/zipcodes?minAge=10&maxAge=30

Return top X number of most populated zipcodes.

> GET /api/v1/zipcodes?top=3

Return all zipcodes with more females than males ordered by the difference descending.

> GET /api/v1/zipcodes?moreFemalesThanMales=1

## Web Interface

Use an interface to demo the above REST API:

> GET http://localhost:8080/

## TODO

- Write Unit Tests
- Validate Inputs

