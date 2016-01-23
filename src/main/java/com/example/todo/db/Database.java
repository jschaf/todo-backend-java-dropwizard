package com.example.todo.db;

import com.example.todo.api.TodoEntry;
import com.google.inject.Singleton;

import java.util.HashMap;
import java.util.Map;

@Singleton
public class Database {

    private final Map<Integer, TodoEntry> todoStore = new HashMap<>();

    {
        todoStore.put(1, new TodoEntry(1, "First", false, 2));
        todoStore.put(2, new TodoEntry(2, "second", false, 2));
        todoStore.put(55, new TodoEntry(55, "another", true, 3));
    }

    public Map<Integer, TodoEntry> getTodoStore() {
        return todoStore;
    }
}
