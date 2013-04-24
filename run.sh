#!/bin/sh
export CLEARDB_DATABASE_URL=mysql://punter:Janeka1974@localhost/punter
java -Xdebug -Xrunjdwp:transport=dt_socket,server=y,suspend=n,address=5005 -jar target/dependency/webapp-runner.jar --port 5000 target/punters-den

