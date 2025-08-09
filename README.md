# README

# Load maven project

The IDE will be all messed up until it recognizes this as a mvn project.
This seems buggy and nondeterministic and wastes a lot of time.

If you think the pom looks right, but package names aren't being loaded properly
and/or discovered appropriately within the IDE, then try to reload maven:

```
Ctrl + Shift + O
```

or close and reopen the project (IntelliJ window) entirely

which should trigger the bottom right popup to "load maven project"

# Run Local

`mvn clean install`

`mvn spring-boot:run`

# DynamoDB Local

https://github.com/awslabs/amazon-dynamodb-local-samples/tree/main

`docker run -d -p 8000:8000 amazon/dynamodb-local`

appears to start a container managed via Docker Desktop, neat



# Docker compose

`docker-compose up --build`