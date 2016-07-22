/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package eu.coldrye.app.demos.todolist.resources;

import eu.coldrye.app.backend.model.common.AbstractEntity;
import eu.coldrye.app.demos.todolist.entities.TodoInstance;
import eu.coldrye.app.demos.todolist.entities.TodoListInstance;
import eu.coldrye.app.demos.todolist.spi.TodoInstanceDAO;
import eu.coldrye.app.demos.todolist.spi.TodoListInstanceDAO;

/**
 *
 * @author Carsten Klein <trancesilken@gmail.com>
 */
@Path("/todo")
public class TodoResource
{

    @Post
    @Path("/delete")
    void delete(String id)
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        TodoInstance td = tddao.findOne(id);
        tddao.delete(td);
    }

    @Post
    @Path("/update")
    void update(String id, String description)
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        TodoInstance td = tddao.findOne(id);
        td.setDescription(description);
        tddao.persist(td);
    }

    @Post
    @Path("/update")
    void update(String id, boolean isDone)
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        TodoInstance td = tddao.findOne(id);
        td.setIsDone(isDone);
        tddao.persist(td);
    }

    @Post
    @Path("/create")
    void create(String todoListId, String description)
    {
        TodoListInstanceDAO tdldao = new TodoListInstanceDAO();
        tdldao.setEntityManager(entityManager);
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        TodoListInstance tdl = tdldao.findOne(todoListId);
        TodoInstance td = tddao.createNewInstance(
                AbstractEntity.OwnerRoleType.USER);
        td.setParent(tdl);
        tddao.persist(td);
    }

    @Post
    @Path("/count")
    void count()
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        tddao.count(tddao.createNewCountQuery());
    }

    @Post
    @Path("/count")
    void count(String todoListId)
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        tddao.count(tddao.createNewCountQuery());
    }

    @Post
    @Path("/list")
    void list(String todoListId)
    {
        TodoInstanceDAO tddao = new TodoInstanceDAO();
        tddao.setEntityManager(entityManager);

        tddao.find(tddao.createNewFindQuery());
    }
}
