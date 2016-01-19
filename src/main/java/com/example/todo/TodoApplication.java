package com.example.todo;

import com.example.todo.conf.Module;
import com.example.todo.db.Database;
import com.example.todo.resources.TodoResource;
import com.hubspot.dropwizard.guice.GuiceBundle;
import io.dropwizard.Application;
import io.dropwizard.db.PooledDataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
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
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {
        Module module = new Module();
        module.setDatabase(new Database());

        GuiceBundle<TodoConfiguration> guiceBundle = GuiceBundle.<TodoConfiguration>newBuilder()
                .addModule(module)
                .enableAutoConfig(getClass().getPackage().getName())
                .setConfigClass(TodoConfiguration.class)
                .build();


        FlywayBundle<TodoConfiguration> flywayBundle = new FlywayBundle<TodoConfiguration>() {
            @Override
            public PooledDataSourceFactory getDataSourceFactory(TodoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public FlywayFactory getFlywayFactory(TodoConfiguration configuration) {
                return configuration.getFlywayFactory();
            }
        };

        bootstrap.addBundle(guiceBundle);
        bootstrap.addBundle(flywayBundle);
    }

    @Override
    public void run(TodoConfiguration configuration, Environment environment) throws Exception {

        environment.jersey().register(TodoResource.class);

        // Configure CORS parameters
        final FilterRegistration.Dynamic cors = environment.servlets().addFilter("CORS", CrossOriginFilter.class);
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

}
