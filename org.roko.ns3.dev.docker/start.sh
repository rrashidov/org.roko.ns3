#/bin/bash

mkdir docker_context

# copy jars locally since Docker can not use outside paths as context

cp ../org.roko.ns3.svc.fic.api/target/org.roko.ns3.svc.fic.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.svc.storage.bucket.api/target/org.roko.ns3.svc.storage.bucket.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.svc.storage.api/target/org.roko.ns3.svc.storage.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.svc.catalog.api/target/org.roko.ns3.svc.catalog.api-0.0.1-SNAPSHOT.jar ./docker_context/
cp ../org.roko.ns3.svc.api/target/org.roko.ns3.svc.api-0.0.1-SNAPSHOT.jar ./docker_context/

cp Dockerfile-fic ./docker_context/
cp Dockerfile-storage-bucket ./docker_context/
cp Dockerfile-storage ./docker_context/
cp Dockerfile-catalog ./docker_context/
cp Dockerfile-api ./docker_context/

cp storage_bucket_client_config.json ./docker_context/

# run docker-compose
docker-compose up -d --build

