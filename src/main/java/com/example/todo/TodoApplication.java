package com.example.todo;

import com.example.todo.repositories.AllTodos;
import com.example.todo.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

public class TodoApplication extends Application<TodoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception {
        final AllTodos allTodos = new AllTodos();
        final TodoResource todoResource = new TodoResource(allTodos);
        environment.jersey().register(todoResource);

        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);

        // Configure CORS parameters
        cors.setInitParameter("allowedOrigins", "*");
        cors.setInitParameter("allowedHeaders", "X-Requested-With,Content-Type,Accept,Origin");
        cors.setInitParameter("allowedMethods", "OPTIONS,GET,PUT,POST,DELETE,HEAD");

            // Add URL mapping
        cors.addMappingForUrlPatterns(EnumSet.allOf(DispatcherType.class), true, "/*");
    }

    @Override
    public String getName() {
       return "Todo";
    }

    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {

    }
}
