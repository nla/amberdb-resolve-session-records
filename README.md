# amberdb-resolve-session-records
Processing session records in amberdb to persistent data layer

To build:
~~~~
mvn package
~~~~

To run:
~~~~
java -jar resolve-session-records-1.jar <amberdb-url> <user> <password> <session-key> <store-path> <commit-user> <commit-info>
~~~~
