package com.example.todo;

import static io.dropwizard.testing.FixtureHelpers.*;
import static org.assertj.core.api.Assertions.assertThat;

import com.example.todo.api.Entry;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.dropwizard.jackson.Jackson;
import org.junit.Test;

public class EntryTest {

    private static final ObjectMapper MAPPER = Jackson.newObjectMapper();

    @Test
    public void serializesToJSON() throws Exception {
        final Entry entry = new Entry(1, "My Title", false, 1);

        final String actual = MAPPER.writeValueAsString(
                MAPPER.readValue(fixture("fixtures/entry.json"), Entry.class));

        assertThat(MAPPER.writeValueAsString(entry)).isEqualTo(actual);
    }

    @Test
    public void deserializesFromJSON() throws Exception {
        final Entry entry = new Entry(1, "My Title", false, 1);
        assertThat(MAPPER.readValue(fixture("fixtures/entry.json"), Entry.class))
                .isEqualTo(entry);
    }

}
