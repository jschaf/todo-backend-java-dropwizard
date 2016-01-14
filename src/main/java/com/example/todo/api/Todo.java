package com.example.todo.api;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Objects;

public class Todo {

    private int id;

    private String title;

    private boolean completed;

    private int order;

    public Todo() {
    }

    public Todo(int id, String title, boolean completed, int order) {
        this.id = id;
        this.title = title;
        this.completed = completed;
        this.order = order;
    }

    @JsonProperty
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @JsonProperty
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @JsonProperty
    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @JsonProperty
    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
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

        if (!(o instanceof Todo)) {
            return false;
        }

        Todo other = (Todo) o;
        return id == other.id
                && Objects.equals(title, other.title)
                && completed == other.completed
                && order == other.order;


    }


    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", completed=" + completed +
                ", order=" + order +
                '}';
    }
}
