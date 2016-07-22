# coyapp-backend-model-security-base

This provides the fundamental entities for realizing a simple role based
security model.



## Maven

### Maven Coordinates

```
GroupId: coyapp
ArtifactId: coyapp-backend-model-security
```

### Versioning

For our projects we use a semantic versioning scheme, e.g. 0.0.1-SNAPSHOT.


## Deployment

### Initial Deployment

TBD

```
liquibase --username=root --password=root --url="jdbc:mysql://localhost:3306/coyapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" --changeLogFile=eu/coldrye/app/backend/model/base/liquibase/deploy.xml --driver=com.mysql.jdbc.Driver --classpath=./target/coyapp-backend-model-security-0.0.1-SNAPSHOT.jar --contexts=deploy --logLevel=debug update
```

### Migrations

TBD

```
liquibase --username=root --password=root --url="jdbc:mysql://localhost:3306/coyapp?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC" --changeLogFile=eu/coldrye/app/backend/model/base/liquibase/deploy.xml --driver=com.mysql.jdbc.Driver --classpath=./target/coyapp-backend-model-security-0.0.1-SNAPSHOT.jar --contexts=migrate --logLevel=debug update
```
