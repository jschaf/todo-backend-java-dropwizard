package com.example.todo.repositories;

import com.example.todo.api.Todo;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class AllTodos {

    private Map<Integer, Todo> todoStore = new HashMap<>();

    public AllTodos() {
        todoStore.put(1, new Todo(1, "First", false, 2));
        todoStore.put(2, new Todo(2, "second", false, 2));
        todoStore.put(55, new Todo(55, "another", true, 3));
    }

    public Optional<Todo> findById(int id) {
        return Optional.ofNullable(todoStore.get(id));
    }

    public void put(Todo todo) {
        todoStore.put(todo.getId(), todo);
    }

}
