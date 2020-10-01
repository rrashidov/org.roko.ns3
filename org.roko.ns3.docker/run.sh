#/bin/bash

# copy jars locally since Docker can not use outside paths as context

cp ../org.roko.ns3.fic.api/target/org.roko.ns3.fic.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.storage.bucket.api/target/org.roko.ns3.storage.bucket.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.storage.api/target/org.roko.ns3.storage.api-0.0.1-SNAPSHOT.jar ./docker_context/

cp Dockerfile-fic ./docker_context/
cp Dockerfile-storage-bucket ./docker_context/
cp Dockerfile-storage ./docker_context/

cp storage_bucket_client_config.json ./docker_context/

cp storage_bucket_client_config.json ./docker_context/

# run docker-compose
docker-compose up -d --build

