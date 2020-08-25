# NS3 Documentation

## General

The purpose of this document is to track the documentation surrounding the project. This includes but is not limited to working notes as well as permanent/official documentation of the service itself.

## Architecture

In order to provide scalability and high availability of the service, it makes sense to separate different concerns of the service. Two main separations rise from this point of view:

- catalog
- storage

### Catalog

The purpose of this part would be to keep the metadata related to the files that are being uploaded.

### Storage

The purpose of this part would be just to store the files, without keeping track of metadata related to the file (tenant, path, event name maybe). From this point of view, the APIs/capabilities of the Storage component would be the following ones:

- putFile(String id, File f)
- OutputStream getFile(String id)
- deleteFile(String id)
- List<StoredFile> list()

## Working notes

Tasks:

- brief description
- API definition/documentation
- project structure
- deployment
- integration tests
- continuous testing
- multitenancy
- rate limiting

Next steps:

- start implementing storage api componeent
