#!/bin/sh
BASEDIR=$(cd "$(dirname "$0")"; pwd -P)

if [ -z "$JAVA_HOME" ]; then
  JAVA_EXE=java
else
  JAVA_EXE="$JAVA_HOME/bin/java"
fi

exec "$JAVA_EXE" -jar "$BASEDIR/.mvn/wrapper/maven-wrapper.jar" "$@"
