#! /bin/bash
javac *.java
javac -cp .:./mysql-connector-java-5.1.35-bin.jar MainSimpleTest.java
java -cp .:./mysql-connector-java-5.1.35-bin.jar MainSimpleTest
rm *.class
