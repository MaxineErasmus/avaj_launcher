#!/bin/sh
javac avaj/*.java
javac avaj/aircrafts/*.java
javac avaj/weather/*.java

DGRAY='\033[1;30'
LGREEN='\033[1;32m'
PURP='\033[;35m'
LCYAN='\033[1;36m'
NC='\033[0m' # No Color

INPUT='Scenario.txt'

echo "------------------------"
echo "${PURP}ScenarioSmall.txt${NC}"
cat 	${INPUT}
echo "\n"
java	avaj/Simulator ${INPUT} 
echo "------------------------"
echo "${PURP}Simulation.txt${NC}"
cat		Simulation.txt

rm avaj/*.class
rm avaj/aircrafts/*.class
rm avaj/weather/*.class
