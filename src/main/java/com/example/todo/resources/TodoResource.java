package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.Todo;
import com.example.todo.repositories.AllTodos;
import com.google.inject.Inject;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final AllTodos allTodos;

    @Inject
    public TodoResource(AllTodos allTodos) {
        this.allTodos = allTodos;
    }

    @GET
    public Response getIndex() {
        return Response.ok().build();
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
        System.out.println("Resource: " + allTodos);
        return todo;
    }
}
