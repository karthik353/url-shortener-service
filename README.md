# _URL Shortener Utility_

✨URL Shortener Utility✨ helps one generate short URLs in place of the lengthy source URLs.
When a user hits the generated shortened, the utility redirects the user to the original URL. The home page
helps the users to generate the shortened URL using a simple form providing a lucid UX. 
The grid on the home page shows the list of all the records that were generated and also the number of times the shortened links were accessed. 

## Features
- Ability to generate a short URL in place of a long source URL
- Capability to view all such records with key details like number of times the URLs have been accessed

## Tech

_URL Shortener Utility_ uses a number of technologies:

- [Angular 12] - Angular is a TypeScript-based free and open-source web application framework led by the Angular Team at Google and by a community of individuals and corporations
- [MongoDB] - MongoDB is a source-available cross-platform document-oriented database program. Classified as a NoSQL database program, MongoDB uses JSON-like documents with optional schemas
- [Spring boot] - Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications that you can "just run".

## Installation

_URL Shortener Utility_ requires [Node.js](https://nodejs.org/) v14.15+ to run.

1. Java - Spring boot application
    ```sh
    cd application
    mvn clean install
    ```

2. Docker Compose has been integrated to make the deployment process seamless. 

    [docker-compose.yaml](./docker-compose.yaml) is the file that has all the required details about the various containers - database, service and UI.
    
    It has the list of configurations for each container and the dependencies between them.

   ```sh
   # root folder
   docker-compose up -d --build # Run this command for the very first time
   # if the above command has already been run, then the following command can be used on subsequent runs
   docker-compose up -d
   # -d option helps run the container in the background
   ```

## Monitoring

Use the following commands to monitor the state of all the 3 different containers.

Refer to [docker-compose.yaml](./docker-compose.yaml) for more details.

```shell
# To list all the containers
docker ps

# To checks the logs
docker logs -f db # MongoDb logs
docker logs -f service # Java application logs
docker logs -f ui # nginx logs
```


[Angular 12]: <https://angular.io/>
[MongoDB]: <https://www.mongodb.com/>
[Spring boot]: <https://spring.io/projects/spring-boot/>
