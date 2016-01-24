package com.example.todo;

import com.example.todo.api.TodoEntry;
import com.example.todo.repositories.AllTodos;
import com.example.todo.resources.TodoResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.junit.After;
import org.junit.Before;
import org.junit.ClassRule;
import org.junit.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class TodoResourceTest {

    private static final AllTodos allTodos = mock(AllTodos.class);

    @ClassRule
    public static final ResourceTestRule resources = ResourceTestRule.builder()
            .addResource(new TodoResource())
            .build();

    private static final TodoEntry TODO_ENTRY = new TodoEntry(23, "My Title", false, 1);

    @Before
    public void setUp() {
        when(allTodos.findById(eq(23))).thenReturn(Optional.of(TODO_ENTRY));
    }

    @After
    public void tearDown() {
        // reset the mock after each test because of @ClassRule
        reset(allTodos);
    }

    @Test
    public void testGetTodo() {
        assertThat(resources.client().target("/todo/23").request().get(TodoEntry.class))
                .isEqualTo(TODO_ENTRY);
        verify(allTodos).findById(23);
    }

}
