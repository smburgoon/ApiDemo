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

# Docker

> NOTE: when an app runs as a docker containerized app, its 'localhost' is internal to that app. 
> Therefore any apps running in docker need to talk to each other over the internal docker network, not localhost
> this is why my maven project can connect to the exposed 8000 port of the dockerized dynamoDB-local, but the
> dockerized app of the same project needs to connect over 'host.docker.internal:8000'

`mvn clean package`
`docker build -t petapp:local .`
`docker run -d --name dynamodb-local -p 8000:8000 amazon/dynamodb-local`

```
docker run -p 8080:8080 \
  --name petapp \
  --env DYNAMODB_ENDPOINT=http://host.docker.internal:8000 \
  petapp:local

```

# Docker compose

`docker-compose up --build`

https://docs.aws.amazon.com/amazondynamodb/latest/developerguide/DynamoDBLocal.DownloadingAndRunning.html


https://github.com/aws-samples/aws-sam-java-rest