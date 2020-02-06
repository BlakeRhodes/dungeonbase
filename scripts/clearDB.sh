docker container stop mongobase
docker container rm mongobase
docker run -d -p 27017:27017 --name mongobase mongo:latest