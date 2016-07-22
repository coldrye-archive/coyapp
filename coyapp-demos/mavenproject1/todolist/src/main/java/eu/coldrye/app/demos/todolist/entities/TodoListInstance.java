/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.demos.todolist.entities;

import eu.coldrye.app.backend.model.common.AbstractUnique;
import eu.coldrye.app.backend.model.common.Named;
import javax.persistence.Entity;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Entity
public class TodoListInstance
        extends AbstractUnique
        implements Named
{

    private String name;

    @Override
    public String getName()
    {
        return name;
    }

    @Override
    public void setName(final String name)
    {
        this.name = name;
    }
}
