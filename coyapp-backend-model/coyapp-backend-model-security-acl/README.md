# coyapp-backend-model-security-acl

This provides the entities required for realizing an access control list (ACL)
based security model.

## Concepts

### Security Schemas

Security schemas have well known UUIDs, that are known to both the backend
services and frontend services, e.g. WikiPage.SCHEMA is a constant string
representation of the registered schema's UUId.

Security schemas are registered with the security backend including a unique
name, e.g. "Wiki Page" and an optional description which will be made available
to the user in for example the form of a tooltip.

Security schemas define the permissions that are available in a given context.

### Permissions

Permissions have well known UUIDs, that are known to both the backend services
and frontend services, e.g. WikiPage.Permissions.CREATE_PAGE is a constant
string representation of the registered permission's UUId.

Permissions are registered with the security backend including a unique name,
e.g. "Create Wiki Page" and an optional description which will be made available
to the user in for example the form of a tooltip.

### ACL

TBD associated with a domain object by its UUID.

### Domain Object Ownership

TBD Individual accounts can be made owners of a given domain object, including
also the System user account. Owners will automatically implement the owner role,
which in turn will override existing other role assignments and the permissions
that are either granted or denied for these specific roles.
The owner role will always be granted all of the available permissions defined
by a specific security schema.

### Permission Grants / Denials

TBD individual roles can be granted / denied individual permissions on a given
domain object.
