package com.example.todo;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.todo.api.TodoEntry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

public class TodoTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final TodoEntry todoEntry = new TodoEntry(1, "My Title", false, 1);

        final String actual = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/entry.json"), TodoEntry.class));

        assertThat(MAPPER.writeValueAsString(todoEntry)).isEqualTo(actual);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final TodoEntry todoEntry = new TodoEntry(1, "My Title", false, 1);
        assertThat(MAPPER.readValue(fixture("fixtures/entry.json"), TodoEntry.class))
                .isEqualTo(todoEntry);
    }

}
