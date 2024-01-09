ROOT=$(pwd)
echo "starting test-script from directory: $ROOT"

#cd _ROOT/AppDemo
mvn -f quarkus-api-pn/pom.xml clean package