package com.example.todo;

import com.bendb.dropwizard.jooq.JooqBundle;
import com.bendb.dropwizard.jooq.JooqFactory;
import com.example.todo.resources.TodoResource;
import io.dropwizard.Application;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayBundle;
import io.dropwizard.flyway.FlywayFactory;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.eclipse.jetty.servlets.CrossOriginFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.EnumSet;

class TodoApplication extends Application<TodoConfiguration> {

    public static void main(String[] args) throws Exception {
        new TodoApplication().run(args);
    }

    @Override
    public void initialize(Bootstrap<TodoConfiguration> bootstrap) {

        // https://github.com/dropwizard/dropwizard-flyway
        FlywayBundle<TodoConfiguration> flywayBundle = new FlywayBundle<TodoConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TodoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public FlywayFactory getFlywayFactory(TodoConfiguration configuration) {
                return configuration.getFlywayFactory();
            }
        };

        // https://github.com/benjamin-bader/droptools/tree/master/dropwizard-jooq
        JooqBundle<TodoConfiguration> jooqBundle = new JooqBundle<TodoConfiguration>() {
            @Override
            public DataSourceFactory getDataSourceFactory(TodoConfiguration configuration) {
                return configuration.getDataSourceFactory();
            }

            @Override
            public JooqFactory getJooqFactory(TodoConfiguration configuration) {
                return configuration.getJooqFactory();
            }
        };

        bootstrap.addBundle(flywayBundle);
        bootstrap.addBundle(jooqBundle);
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
       return "TodoApplication";
    }

}
