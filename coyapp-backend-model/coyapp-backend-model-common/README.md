# coldrye-app-backend-model-base

## Introduction

coldrye-app-backend-model-base, or CoyAppBackendModelBase, establishes a few
common concepts and abstractions for use in implementing the backend data model
of database driven (web) applications and services.


## Maven

### Maven Coordinates

```
GroupId: coyapp
ArtifactId: coyapp-backend-model-base
```

### Versioning

For our projects we use a semantic versioning scheme, e.g. 0.0.1-SNAPSHOT.


## Deployment

### Initial Deployment

TBD

```
liquibase --username=root --password=root --url="jdbc:mysql://localhost:3306/coyapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" --changeLogFile=eu/coldrye/app/backend/model/base/liquibase/deploy.xml --driver=com.mysql.jdbc.Driver --classpath=./target/coyapp-backend-model-base-0.0.1-SNAPSHOT.jar --contexts=deploy --logLevel=debug update
```

### Migrations

TBD

```
liquibase --username=root --password=root --url="jdbc:mysql://localhost:3306/coyapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" --changeLogFile=eu/coldrye/app/backend/model/base/liquibase/deploy.xml --driver=com.mysql.jdbc.Driver --classpath=./target/coyapp-backend-model-base-0.0.1-SNAPSHOT.jar --contexts=migrate --logLevel=debug update
```
