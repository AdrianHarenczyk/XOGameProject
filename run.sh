!#/bin/bash
mvn clean install
reset
cd target
zmienna=`ls | grep .jar`
java -jar $zmienna
