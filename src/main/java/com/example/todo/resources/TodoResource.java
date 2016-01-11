package com.example.todo.resources;

import com.codahale.metrics.annotation.Timed;
import com.example.todo.api.Entry;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import java.util.Optional;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
public class TodoResource {

    public TodoResource() {
    }

    @GET
    @Timed
    public Entry getEntry() {
        return new Entry(100, "MY AWESOME TITLE", true, 99);
    }
}
