package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.Entry;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/")
public class TodoResource {

    public TodoResource() {
    }

    @GET
    @Timed
    @Produces(MediaType.APPLICATION_JSON)
    public Entry getEntry() {
        return new Entry(100, "MY AWESOME TITLE", true, 99);
    }

    @POST
    @Timed
    @Consumes(MediaType.APPLICATION_JSON)
    public void putEntry(Entry entry) {

    }
}
