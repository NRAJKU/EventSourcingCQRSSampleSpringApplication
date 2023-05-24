# Java-Microservices-CQRS-Event-Sourcing-with-Kafka
Spring Boot Microservices that comply to the CQRS &amp; Event Sourcing patterns, powered by Java and Apache Kafka

## steps to run the application

### Launch Mongo DB, Postgress from docker with this command
```docker run -p 27017:27017 --name some-mongo -d mongo:latest```
```docker run -p 5432:5432 --name some-postgres -e POSTGRES_PASSWORD=dbpassword -d postgres```

### launch dev Cluster for Kafka and Zookeeper from docker-compose file as
```docker-compose -f docker-compose-kafka.yaml up -d```

### now normally launch Spring Applications
#### account.cmd this is Event Sourcing application 
```Application will run on port 5100```
#### account.query this is our query application which is a CQRS app 
```Application will run on port 5101```
