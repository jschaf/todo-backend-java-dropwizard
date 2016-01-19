package com.example.todo.db;

import com.example.todo.api.Todo;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class Database {

    private final Map<Integer, Todo> todoStore = new HashMap<>();

    {
        todoStore.put(1, new Todo(1, "First", false, 2));
        todoStore.put(2, new Todo(2, "second", false, 2));
        todoStore.put(55, new Todo(55, "another", true, 3));
    }

    public Map<Integer, Todo> getTodoStore() {
        return todoStore;
    }
}
