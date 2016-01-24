package com.example.todo.repositories;

import com.example.todo.api.TodoEntry;
import com.example.todo.models.tables.records.TodoRecord;
import org.jooq.DSLContext;

import javax.inject.Inject;
import java.util.Optional;

import static com.example.todo.models.Tables.TODO;

public class AllTodos {


    @Inject
    private DSLContext database;

    public AllTodos() {
    }

    public Optional<TodoEntry> findById(int id) {
        TodoEntry todoEntry = database.select().from(TODO).where(TODO.ID.eq(id)).fetchOneInto(TodoEntry.class);
        return Optional.ofNullable(todoEntry);
    }

    public void put(TodoEntry todoEntry) {
        TodoRecord todoRecord = database.newRecord(TODO, todoEntry);
        todoRecord.store();
    }
}
