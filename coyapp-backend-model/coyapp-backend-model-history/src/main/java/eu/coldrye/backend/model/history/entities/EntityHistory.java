/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.backend.model.history.entities;

import eu.coldrye.app.backend.model.common.AbstractUnique;
import eu.coldrye.app.backend.model.user.entities.UserInstance;
import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 * @since 0.0.1
 */
@Entity
@Table(name = "coy_entity_history")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "entity_type", length = 200)
public abstract class EntityHistory
        extends AbstractUnique
{

    private LocalDateTime dateTime;

    @Column(name = "action_class", nullable = false, updatable = false)
    @Size(max = 1000)
    private String actionClass;

    private UserInstance user;

    private String oldState;
    private String newState;
}
