# NS3 Documentation

## General

The purpose of this document is to track the documentation surrounding the project. This includes but is not limited to working notes as well as permanent/official documentation of the service itself.

## Architecture

In order to provide scalability and high availability of the service, it makes sense to separate different concerns of the service. Two main separations rise from this point of view:

- catalog
- storage

In order to implement the mentionde above main components, additional helper/supporting components are needed:

- fic (file id calculator)

### Catalog

The purpose of this part would be to keep the metadata related to the files that are being uploaded.

### Storage

The purpose of this part would be just to store the files, without keeping track of metadata related to the file (tenant, path, even name maybe). From this point of view, the APIs/capabilities of the Storage component would be the following ones:

- putFile(String id, File f)
- OutputStream getFile(String id)
- deleteFile(String id)
- List<StoredFile> list()

### FIC - File ID Calculator

The purpose of this part would be just to calculate thee ID of a file being uploaded. From this point of view, the APIs/capabilities of the FIC component would be the following ones:

- String calculate(File f)

This part could be implemented as serverless.

## Working notes

Tasks:

- adopt fix in catalog microservice
- brief description
- API definition/documentation
- project structure
- deployment
- integration tests
- continuous testing
- multitenancy
- rate limiting

Next steps:

- start implementing storage api component

## Future Improvements

- implement fic (file ID calculator) as serverless;

## Unsorted

### Module naming concept

Modules will be grouped in the following groups:

- svc - modules containing micro service source code;
- util - modules containing util source code to be shared between other modules;
- doc - modules containing project related documentation;
- dev - modules containing information related to the dev process;

In order to distinguish module type, modules will be named based on the following naming convention:

```
org.roko.ns3.<module type>.<module name>
```

Example: _org.roko.ns3.svc.catalog.api_

Furthermore, the micro services will be materialized in the form of several maven modules. Besides the micro service itself, almost all of them will have a Java client, which in term will also be materialized in thee form of maven module. So, a given microservce, could materialize in the form of the following maven modules:

- <micro service name>.api - this component will contain the REST API that exposes the functionality provided by the micro service;
- <micro service name>.client - this component will contain the Java client that will make it possible the consume the REST API provided by the micro service;

### List of microservices

- fic - file id calculator
- storage.bucket - storage bucket
- storage - storage
- catalog - catalog
- api - API

## Dev Setup

- fix - localhost:8081
- storage.api - localhost:8082
- storage.bucket - localhost:8083
- api - localhost:8080
