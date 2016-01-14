package com.example.todo.repositories;

import com.example.todo.api.Todo;

/**
 * Created by joe on 1/13/2016.
 */
public class AllTodos {

    public Todo findById(int id) {
        if (id == 1) {
            return new Todo(1, "First", false, 2);
        } else if (id == 2) {
            return new Todo(2, "second", false, 2);
        } else {
            return new Todo(55, "another", true, 3);
        }
    }

}
