# coyapp-backend-model-subsystem

This provides a subsystem entity for applications that have multiple different
subsystems or rather instances thereof. Subsystems can be used to associate
individual content with their providing subsystem, for example a wiki page that
is part of a given wiki instance and so on.

Subsystems can be defined hierarchically, so for example a wiki subsystem would
be part of a subsystem that for example resembles a project management
application and so on.


## Subsystem Types

There exist a number of defined subsystem types, these are

- SYSTEM
This resembles the system itself.

- SITE
This resembles the site on which all of the applications are run. Normally,
a single site runs just one application at a time. Sites that run multiple
applications are considered to be portal sites. Sites can also be realized in a
multi site scenario, i.e. a single deployment provides access to multiple sites
that all run different applications.

- APP
This resembles an application, for example a project management application. An
application is basically also a component.

- COMPONENT
This resembles an individual component of the application, for example a project
or repository or wiki or issue tracker thereof. A component is basically also
a plugin, yet it is trusted in that it comes from a trusted source. As such,
components do not have the restrictions that are applied to plugins.

- PLUGIN
This resembles an installed plugin that enhances the functionality of for example
an application or a component thereof. Plugins are considered to be untrusted
and are thus limited in their access to security related features.


## Security Aspects

Please note that this entity is not meant for implementing any security related
aspects of the application. For this, use the coyapp-backend-model-security-base
and related other packages.

In terms of security, and when using coyapp-backend-model-security-base,
subsystems directly map to security realms, which means that they can be defined
for example as subsystems that allow the application or the users of that
application, to create new user accounts, define groups, roles and so on.


### Trust

All subsystems are automatically trusted and are permitted access to all
security related aspects of the application via its designated service paths.

When it comes to plugins, these stem from different, and possibly untrusted
sources. So these need to be limited in their access to the system's
underlying security data model. For example, a plugin must never be able to
create new user accounts with associated credentials that can be used as a back
door into the system.
