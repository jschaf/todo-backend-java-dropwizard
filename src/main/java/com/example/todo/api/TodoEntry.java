package com.example.todo.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public final class TodoEntry {

    private final int id;

    private final String title;

    private final boolean completed;

    private final int order;

    @JsonCreator
    public TodoEntry(@JsonProperty("id") int id,
                     @JsonProperty("title") String title,
                     @JsonProperty("completed") boolean completed,
                     @JsonProperty("order") int order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public boolean isCompleted() {
        return completed;
    }

    public int getOrder() {
        return order;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, completed, order);
    }

    @Override
    public boolean equals(Object o) {
        if (this ==  o) {
            return true;
        }

        if (!(o instanceof TodoEntry)) {
            return false;
        }

        TodoEntry other = (TodoEntry) o;
        return id == other.id
                && Objects.equals(title, other.title)
                && completed == other.completed
                && order == other.order;
    }


    @Override
    public String toString() {
        return "TodoEntry{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                '}';
    }
}
