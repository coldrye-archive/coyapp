/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.demos.todolist.resources;

import eu.coldrye.app.backend.model.common.AbstractEntity;
import eu.coldrye.app.demos.todolist.entities.TodoListInstance;
import eu.coldrye.app.demos.todolist.spi.TodoListInstanceDAO;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
public class TodoListResource
{

    void create(String name)
    {
        TodoListInstanceDAO tdldao = new TodoListInstanceDAO();
        tdldao.setEntityManager(entityManager);

        TodoListInstance tdl = tdldao.createNewInstance(
                AbstractEntity.OwnerRoleType.USER);
        tdl.setName(name);
        tdldao.persist(tdl);
    }

    void delete(String id)
    {
        TodoListInstanceDAO tdldao = new TodoListInstanceDAO();
        tdldao.setEntityManager(entityManager);

        TodoListInstance tdl = tdldao.findOne(id);
        tdldao.delete(tdl);
    }

    void update(String id, String name)
    {
        TodoListInstanceDAO tdldao = new TodoListInstanceDAO();
        tdldao.setEntityManager(entityManager);

        TodoListInstance tdl = tdldao.findOne(id);
        tdl.setName(name);
        tdldao.persist(tdl);
    }

    void count()
    {
        TodoListInstanceDAO tdldao = new TodoListInstanceDAO();
        tdldao.setEntityManager(entityManager);

        tdldao.count(tdldao.createNewCountQuery());
    }

    void list()
    {
    }
}
