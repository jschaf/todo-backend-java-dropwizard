package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.Todo;
import com.example.todo.repositories.AllTodos;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/")
public class TodoResource {

    private final AllTodos allTodos;

    public TodoResource(AllTodos allTodos) {
        this.allTodos = allTodos;
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/{id}")
    public Todo getEntry(@PathParam("id") int id) {
        return allTodos.findById(id);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public void putEntry(Todo todo) {

    }
}
