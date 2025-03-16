# FlightPlanner

## Prerequisites

To run this project, make sure you have the following installed:  

**Java 17 (or higher)** for the backend (Spring Boot)   
Verify that it is installed with:
```
java -version
```
If not install it from the [official website](https://www.oracle.com/java/technologies/downloads/)  

**Maven** (to build and run the backend)   
Verify that it is installed with:
```
mvn -v
```
If not install it from the [official website](https://maven.apache.org/download.cgi)    

**npm** (for the frontend)   
Verify that it is installed with:
```
npm -v
```
If not install it from the [official website](https://nodejs.org/)    

**PostgreSQL** (database for the backend)   
Verify that it is installed with:
```
psql --version
```
If not install it from the [official website](https://www.postgresql.org/download/)    

**Git** (for version control)   
Verify that it is installed with:
```
git --version
```
If not install it from the [official website](https://git-scm.com/downloads)  

## Cloning the project

Clone the project to your local machine by running this command:
```
git clone https://github.com/Asttar/FlightPlanner
```

## Running the backend

Run the following commands to open the psql terminal and create the database for the backend (Replace postgres with your PostgreSQL username if it differs.):
```
psql -U postgres
```

```
CREATE DATABASE flights_db;
```

Navigate to **src/main/resources/application.properties** and update the following lines with your database's username and password:  
**spring.datasource.username=\<your-db-username\>**  
**spring.datasource.password=\<your-db-password\>** 


Then in the folder where the project is cloned navigate to the backend and start it:
```
cd FlightPlanner/Backend
```
```
mvn spring-boot:run
```

## Running the frontend

In the folder where the project is cloned navigate to the frontend and start it:


```
cd FlightPlanner/frontend
```
```
npm install
```
```
npm run dev
```

The website will be running on http://localhost:8080

## Documentation

## Project Overview
FlightPlanner is a web application that allows users to search for flights, filter results based on different criteria (destination, date, time, price), and receive seat recommendations. The backend is built using **Spring Boot** and a **PostgreSQL** database, while the frontend is built with **Vue**.

## Features
- **Flight Search:** Users can view all available flights.
- **Filtering Options:** Users can filter flights based on:
  - Destination
  - Date
  - Flight Time
  - Price Range
- **Sorting Options:** Users can sort flights in ascending or descending order based on:
  - Price
  - Date
  - Alphabetically

- **Seat Recommendation System:** When choosing seats, the system suggests seats based on:
  - Window seats
  - More legroom
  - Close to exit
  - Side-by-side seating in pairs (for multiple ticket purchases)
- **Manual Seat Selection:** Users can also choose seats manually.

## Technologies Used
- **Vue** for frontend
- **Java Spring Boot** for backend
- **PostgreSQL** for database

## How It Works
1. The user lands on the homepage and sees all available flights.
2. The user can filter flights based on destination, date, flight time, and price.
3. After selecting a flight and getting redirected to the corresponding page, the system automatically recommends the seats based on the number of tickets and other filters.
4. The user can also manually choose their own seats.

### Time Taken to Complete
- The project took approximately **30 hours** to complete.  

### Challenges Faced
- Had an issue where the filters weren't properly reset when no available seats matched the selected filters, or when the number of tickets exceeded 10.  
  → **Solution:** Using ChatGPT's recommended `this.$nextTick(() => {` fixed the problem.  
- Struggled with design choices and took inspiration from real travel planners.  
- The most challenging part was integrating automatic seat recommendations with manual selection. It was difficult to ensure that the ticket count remained accurate when switching between the two, and that seat filters could still be applied to manually selected seats.  


### Assumptions Made
- I assumed that in the seat planning the seats can be recommended by filters, but can also be manually chosen by the user.
- I assumed that the taken seats could be generated on the load of the specific page, rather than hardcoding the seatplan for every flight in the database, so it is identical every time a specific flight is loaded.
- I assumed that for Side-by-side seating filter, the seats are positioned in pairs and the leftover seats are positioned alone.
- I assumed that there doesn't need to be anything following the seat choosing as in paying for the tickets or saving the choices in the database.
- I assumed that there doesn't have to be a way to add additional flights by the user (can be theoretically done by manually changing the code).


