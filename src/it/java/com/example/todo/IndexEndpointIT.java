package com.example.todo;

import com.codahale.metrics.MetricRegistry;
import com.example.todo.api.Todo;
import com.example.todo.resources.TodoResource;
import io.dropwizard.client.JerseyClientBuilder;
import io.dropwizard.jersey.DropwizardResourceConfig;
import io.dropwizard.logging.BootstrapLogging;
import io.dropwizard.testing.junit.DropwizardAppRule;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;


public class IndexEndpointIT {

    @ClassRule
    public static final DropwizardAppRule<TodoConfiguration> RULE = new DropwizardAppRule<>(TodoApplication.class);


    private final String SITE_URL = String.format("http://localhost:%d/", RULE.getLocalPort());

    @Test
    public void getOfIndexReturns200() {
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
        Response response = client.target(SITE_URL + "todo")
                .request()
                .get();

        assertThat(response.getStatus()).isEqualTo(200);
    }


    @Test
    public void postToIndexReturnsPostedJson() {
        Client client = new JerseyClientBuilder(RULE.getEnvironment()).build("test client");
        Todo todo = new Todo(37, "Number 37", false, 2);

        Response response = client.target(SITE_URL + "todo")
                .request()
                .post(Entity.json(todo));

        assertThat(response.getStatus()).isEqualTo(200);
        assertThat(response.readEntity(Todo.class)).isEqualTo(todo);
    }
}
