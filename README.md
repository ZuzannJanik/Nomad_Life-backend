# Nomad_Life-backend

Backend for the Nomad-life application - REST API

Link for frontend:
https://github.com/ZuzannJanik/Nomad_Life_Frontend

# Instructions for starting the application

- Run the mysql database locally according to the parameters in the application.properties of the backend project.
- Run the backend applications on port 8080 (be sure to do so first, otherwise the application will not work).
- Run frontend applications on port 5000.

# External APIs

The application uses two external APIs.

Rest Country, which is intended to download the flags of individual countries and serve as a decorative element of the application.
https://rapidapi.com/alejandro.perez3469/api/rest-countries10

And Google Search, which is intended to be used to search for drugs
https://rapidapi.com/neoscrap-net/api/google-search72

# Technologies:
- Java 17
- Lombok project
- Spring Boot 3.1.2
- Vaadin 24.1 (for frontend views)

# What is Nomad-Life?

The application is intended for people who travel frequently.
The current application focuses on the area of ​​health. 
Includes: 
- Vaccination tracker. This allows you to check whether individual vaccinations are up to date, plan vaccinations between trips and control the necessary foreign vaccinations for travelers.
- The medicine list allows you to check whether the first aid kit contains the necessary medicines. It allows you to control their expiration dates and the supply of prescription drugs.

# Further possible development of the application

- Using Security to register and login as a user, admin and observer(for exaple for company or family).
- Sending pre-trip notifications with vaccination, first aid kit and travel status.
- Adding a travel management page from which tasks from all modules will be possible.
- Transferring the application to a microservice architecture.

Adding further modules:

- Finance (cost of living control, currency conversion, budgets, tax settlements depending on financial residency).
- Managing your belongings (list of necessary things in the new apartment, list of purchased things because being a nomad means the need to sell and send your belongings)
- Moving planner (cascade or agile plan of things to do before moving out, after moving out, e.g. -2 months before departure, -1 week before departure, on the day of departure, 1 day after arrival).

# Known issues:

- External API - Rest Country isn't connected in the frontend layer (however, api work in the backend - this can be checked using Postman and/or controller tests).
