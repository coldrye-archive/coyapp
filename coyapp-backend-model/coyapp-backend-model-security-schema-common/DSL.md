# Security Schema DSL

TODO dsl for notating security schemas



Example


import App;
import Security;


subsystem Entity
{
    permission CreatePermission
        implies EditPermission
    {

    }

    permission EditPermission
        implies ViewPermission
    {
        ...
    }

    permission DeletePermission
        implies EditPermission
    {
        ...
    }

    permission ViewPermission
    {
        ...
    }
}

subsystem NamedEntity extends Entity
{
    permission RenamePermission
    {
    }
}

subsystem Node extends NamedEntity
{
    permission MovePermission
    {
    }
}


// application
subsystem WikiApp
{
    name "Wiki";
    uuid "<UUID>";
    capabilities ...;
    description "...";

    role WikiUserRole
        extends App.UserRole
    {
        name "Wiki User";
        uuid "<UUID>";
        description "...";
    }

    role WikiEditorRole
        extends WikiUserRole
    {
        name "Wiki Editor";
        uuid "<UUID>";
        description "...";
    }

    role WikiAdminRole
        extends WikiEditorRole
    {
        name "Wiki Admin";
        uuid "<UUID>";
        description "...";
    }

    instance group WikiUsersGroup
        implements WikiUserRole
    {
        description "...";
    }

    instance group WikiEditorsGroup
        implements WikiEditorRole
    {
        description "...";
    }

    instance group WikiAdminsGroup
        implements WikiAdminRole
    {
        description "...";
    }

    grant Security.ViewRolesPermission, Security.ViewGroupsPermission,
          Security.Acl.ViewAclPermission, ...
          to WikiEditorRole;

    // grant wiki admin the permission to edit all wiki related acls including that of the wiki
    grant Security.*, Security.Acl.*
          to WikiAdminRole;
}


// component
subsystem WikiPage extends Node
{
    parent WikiApp;

    name "Wiki Page";
    uuid "<UUID>";
    capabilities ...;
    description "...";

    ...

    grant ViewPermission to WikiUserRole;
    grant CreatePermission, DeletePermission to WikiEditorRole;
    grant Security.Acl.EditAclPermission to WikiEditorRole;

    grant auto * to System;
    grant auto * to OwnerRole;
    grant auto * to Site.SiteOperatorRole;
}


// component
subsystem WikiSpace extends Node
{
}
