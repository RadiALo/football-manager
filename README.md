# ⚽ Football manager project! ⚽

An application that implements a service for football teams and players.
It contains an API developed using Spring Boot and a front-end application created using the Angular framework.

## Features
This simple service provides you to:
* Create football teams.
* Add new players to teams.
* Edit teams and players.
* Delete teams and players.
* Transfer players from one team to another.
The API provides all the necessary endpoints for this, and the front-end application provides a convenient user interface.

## Technologies
* Java 17.
* Hibernate
* PostgreSQL
* Spring Boot
* Angular

## Structure
### API Controllers

The API includes two controllers:

* Player Controller:
  * POST /players/ - addes a new player
  * GET /players/ - provides information about multiple players. Has "page" and "size" parameters for pagination.
  * GET /players/${id}/ - provides information about player with some id.
  * PUT /players/${id}/ - updates information about player with some id.
  * DELETE /players/${id}/ - deletes player with some id.
  * POST /players/${id}/transfer - transfer player with some id.
  * GET /info/ - provides information about the total number of players.
  * POST /init/ - allows you to create a whole collection of players at once.
* Team Controller:
  * POST /teams/ - creates a new team.
  * GET /teams/ - provides information about multiple teams. Has "page" and "size" parameters for pagination.
  * GET /teams/${id}/ - provides information about player with some id.
  * GET /teams/${id}/players - provides information about all players in the team.
  * PUT /teams/${id}/ - updates information about team with some id.
  * DELETE /teams/${id}/ - deletes team with some id.
  * GET /info/ - provides information about the total number of teams.
  * POST /init/ - allows you to create a whole collection of teams at once.

### Model

* Applications operate in two models: Player and Team.
* The API has a service layer and a repository for each model. The repository tier is built using Sping Data JPA.
* The front-end makes requests to the server and receives responses in the form of DTOs.
* DTOs received by the server are validated by the server.

## How to start

* Spring Back-end API:
  * Change values YOUR_DRIVER, YOURDATABASE_URL, YOUR_USERNAME, YOUR_PASSWORD on yours at the db.properties file.
  * Build and start project.
* Angular Front-end:
  * Run front-end application using `npm start` command.
