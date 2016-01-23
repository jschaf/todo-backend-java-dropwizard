package com.example.todo.repositories;

import static com.example.todo.models.Tables.*;

import com.example.todo.api.TodoEntry;
import com.example.todo.db.Database;
import com.example.todo.models.tables.pojos.Todo;
import com.google.inject.Inject;
import org.jooq.DSLContext;

import javax.ws.rs.core.Context;
import java.util.Optional;

public class AllTodos {


    private DSLContext database;

    @Inject
    public AllTodos(@Context DSLContext database) {
        this.database = database;
    }

    public Optional<TodoEntry> findById(int id) {
        database.select().from(TODO).where(TODO.ID.eq(id)).fetchOneInto(Todo.class);
        return Optional.ofNullable(null);
    }

    public void put(TodoEntry todoEntry) {


       // db.getTodoStore().put(todoEntry.getId(), todoEntry);
    }

    public void printHashMap() {
//        for (Integer key :
//                db.getTodoStore().keySet()) {
//            String id = key.toString();
//            String todo = db.getTodoStore().get(key).toString();
//            System.out.println(id + ": " + todo);
//        }
    }

}
