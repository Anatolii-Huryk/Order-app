# Order-app
___
## Introduction
Is a simple application with which you can create orders. 
Also, the order is automatically deleted within 10 minutes.
______________________
## Project structure

The structure of this project consists of 3 levels:
* Data access layer (DAO).
* Application layer (service).
* Presentation layer (controllers).

___

## Technologies:
* H2
* Spring boot
* Maven
* JPARepository
* Lombok
* Mockito

## How to use application
1. H2 base is the default, but you can put any, just change the data in 
application.properties
2. You can add a user - POST(orders)
3. Find the cheapest by item- PUT(orders/find-by-item)
4. Find all - GET(orders/all).

