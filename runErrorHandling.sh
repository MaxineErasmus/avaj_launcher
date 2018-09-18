#!/bin/sh
javac avaj/*.java
javac avaj/aircrafts/*.java
javac avaj/weather/*.java

DGRAY='\033[1;30'
LGREEN='\033[1;32m'
LPURP='\033[1;35m'
LCYAN='\033[1;36m'
NC='\033[0m' # No Color

echo "${NC}----------------------------------------"
echo "${LPURP}ScenarioIncorrectRuntimeNeg.txt${NC}"
cat		ScenarioIncorrectRunTimeNeg.txt
java	avaj/Simulator ScenarioIncorrectRunTimeNeg.txt
echo "${NC}----------------------------------------"
echo "${LPURP}ScenarioIncorrectRuntime.txt${NC}"
cat		ScenarioIncorrectRunTime.txt
java	avaj/Simulator ScenarioIncorrectRunTime.txt
echo "${NC}----------------------------------------"
echo "${LPURP}ScenarioIncorrectAirplane.txt${NC}"
cat		ScenarioIncorrectAirplane.txt
java	avaj/Simulator ScenarioIncorrectAirplane.txt
echo "${NC}----------------------------------------"
echo "${LPURP}Testing for no argument passed i.e: java avaj/Simulator${NC}"
java	avaj/Simulator
echo "${NC}----------------------------------------"
echo "${LPURP}Testing for too many arguments passed i.e: java avaj/Simulator arg1 arg2${NC}"
java	avaj/Simulator ScenarioIncorrectAirplanes.txt ScenarioIncorrectRuntime.txt

rm avaj/*.class
rm avaj/aircrafts/*.class
rm avaj/weather/*.class
