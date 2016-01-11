package com.example.todo;

import com.example.todo.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class TodoApplication extends Application<TodoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception {
        final TodoResource todoResource = new TodoResource();

        environment.jersey().register(todoResource);

    }

    @Override
    public String getName() {
       return "Todo";
    }

    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {

    }
}
