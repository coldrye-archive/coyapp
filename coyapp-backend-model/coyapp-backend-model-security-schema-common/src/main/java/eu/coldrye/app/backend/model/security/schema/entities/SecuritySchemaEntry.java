/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.backend.model.security.schema.entities;

import eu.coldrye.app.backend.model.base.AbstractUnique;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Entity
@Table(name = "coy_sec_security_schema_entry")
@DiscriminatorColumn(name = "entry_type")
public class SecuritySchemaEntry
        extends AbstractUnique
{

    @ManyToOne(optional = false)
    @Column(name = "schema_id", nullable = false, updatable = false)
    private SecuritySchema schema;

    @Enumerated(EnumType.STRING)
    @Column(name = "installation_type", nullable = false, updatable = false)
    private InstallationType installationType;

    public static enum InstallationType
    {
        /**
         * The entry, when deployed, will be associated with the realm of the
         * defining subsystem.
         */
        SINGLETON,
        /**
         * The entry, when deployed, will be associated with the realm of an
         * instance of the defining subsystem.
         */
        INSTANCE
    }
}
