ROOT=$(pwd)
echo "starting test-script from directory: $ROOT"

#cd _ROOT/AppDemo
mvn -f quarkus-api-pn/pom.xml clean package

#docker
docker build -f quarkus-api-pn/src/main/docker/Dockerfile.jvm -t neogicel/quarkus-api-pn-jvm:1.0 .