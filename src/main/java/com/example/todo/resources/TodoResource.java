package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.Todo;
import com.example.todo.repositories.AllTodos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final AllTodos allTodos;

    public TodoResource(AllTodos allTodos) {
        this.allTodos = allTodos;
    }

    @GET
    @Timed
    @Path("{id}")
    public Todo getById(@PathParam("id") int id) {
        return allTodos.findById(id).orElse(null);
    }

    @POST
    @Timed
    public Todo addTodo(Todo todo) {
        allTodos.put(todo);
        return todo;
    }
}
