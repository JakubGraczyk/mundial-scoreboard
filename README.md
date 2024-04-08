# Mundial Scoreboard

## Requirements

For building and the library you need:

- [JDK 21](https://jdk.java.net/21/)
- [Maven 3](https://maven.apache.org)

## Running tests
```shell
mvn test
```

## Building the JAR:
```shell
mvn clean install
```

## Features:
- Starting new matches with 0 - 0 score.
- It supports the playing of matches between each of the participants of the Qatar 2022 World Cup
- It supports updating match results
- It supports ending the match and removing from the scoreboard
- Returns summary of ongoing matches in order depending on the number of goals scored and starting time (by the most recently started match)