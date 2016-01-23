package com.example.todo.repositories;

import com.example.todo.models.tables.pojos.Todo;
import com.example.todo.models.tables.records.TodoRecord;
import org.jooq.DSLContext;

import javax.ws.rs.core.Context;
import java.util.Optional;

import static com.example.todo.models.Tables.TODO;

public class AllTodos {


    private DSLContext database;

    public AllTodos(@Context DSLContext database) {
        this.database = database;
    }

    public Optional<Todo> findById(int id) {
        Todo todo = database.select().from(TODO).where(TODO.ID.eq(id)).fetchOneInto(Todo.class);
        return Optional.ofNullable(todo);
    }

    public void put(Todo todo) {
        TodoRecord todoRecord = database.newRecord(TODO, todo);
        todoRecord.store();
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
