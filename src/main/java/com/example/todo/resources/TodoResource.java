package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.TodoEntry;
import com.example.todo.repositories.AllTodos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/todo")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    private final AllTodos allTodos = new AllTodos();

    public TodoResource() {
    }

    @GET
    public Response getIndex() {
        return Response.ok().build();
    }

    @GET
    @Timed
    @Path("{id}")
    public TodoEntry getById(@PathParam("id") int id) {
        return allTodos.findById(id).orElse(null);
    }

    @POST
    @Timed
    public TodoEntry addTodo(TodoEntry todoEntry) {
        allTodos.put(todoEntry);
        System.out.println("Resource: " + allTodos);
        return todoEntry;
    }
}
