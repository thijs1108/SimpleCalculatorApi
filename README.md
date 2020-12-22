# Installation, build and run instructions
Instructions to get the SimpleCalculator Api up and running.

## Database
The Api expects an postgres application running on `localhost` port `5432`. If you don't have that on your machine, follow next instructions to run it in Docker;
1. Ensure you have docker properly installed
2. Go to api folder: $ `cd simplecalculatorapi/`
3. It you want to, edit `postgres-stack.yml` to change credentials, but if you do so, also edit `application.properties` in the Api.
4. Run $ `docker-compose -f postgres-stack.yml up` (`-d` if you want to run in detached mode)
5. posgres is now running on `localhost:5432`

## Api
The api handles the frontend calls and persistance to the database. 
1. Open the project in your IDE
2. Run the `main` method in `SimplecalculatorApplication`.
3. The api is now running on `localhost:8080` with context path `/api`
