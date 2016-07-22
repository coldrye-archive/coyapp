/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.demos.todolist.entities;

import eu.coldrye.app.backend.model.common.AbstractUnique;
import eu.coldrye.app.backend.model.common.Describable;
import javax.persistence.Column;
import javax.persistence.ManyToOne;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public class TodoInstance
        extends AbstractUnique
        implements Describable
{

    @ManyToOne(optional = false)
    @Column(name = "parent_id")
    private TodoListInstance parent;

    @Column(name = "is_done")
    private boolean isDone = false;

    private String description;

    @Override
    public String getDescription()
    {
        return description;
    }

    public TodoListInstance getParent()
    {
        return parent;
    }

    public boolean isIsDone()
    {
        return isDone;
    }

    @Override
    public void setDescription(String description)
    {
        this.description = description;
    }

    public void setIsDone(boolean isDone)
    {
        this.isDone = isDone;
    }

    public void setParent(TodoListInstance parent)
    {
        this.parent = parent;
    }
}
