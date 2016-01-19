package com.example.todo.repositories;

import com.example.todo.api.Todo;
import com.example.todo.db.Database;
import com.google.inject.Inject;

import java.util.Optional;

public class AllTodos {


    private Database db;

    @Inject
    public AllTodos(Database db) {
        this.db = db;
    }

    public Optional<Todo> findById(int id) {
        return Optional.ofNullable(db.getTodoStore().get(id));
    }

    public void put(Todo todo) {
        db.getTodoStore().put(todo.getId(), todo);
    }

    public void printHashMap() {
        for (Integer key :
                db.getTodoStore().keySet()) {
            String id = key.toString();
            String todo = db.getTodoStore().get(key).toString();
            System.out.println(id + ": " + todo);
        }
    }

}
