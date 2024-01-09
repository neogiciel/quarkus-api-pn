echo "--------------------- mvn-script.sh--------------------"

ROOT=$(pwd)
echo "starting test-script from directory: $ROOT"

echo "--------------------- package --------------------"
mvn -f quarkus-api-pn/pom.xml clean package

#docker
echo "--------------------- cp --------------------"
#cp quarkus-api-pn/target/quarkus-api-pn-1.0.0-SNAPSHOT.jar  mvn-output
cp quarkus-api-pn  mvn-output