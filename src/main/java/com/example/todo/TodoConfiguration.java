package com.example.todo;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;
import io.dropwizard.flyway.FlywayFactory;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

public class TodoConfiguration extends Configuration {

    @Valid
    @NotNull
    private DataSourceFactory database = new DataSourceFactory();

    @JsonProperty("database")
    public DataSourceFactory getDataSourceFactory() {
        return database;
    }

    @JsonProperty("database")
    public void setDataSourceFactory(DataSourceFactory dataSourceFactory) {
        this.database = dataSourceFactory;
    }

    @Valid
    @NotNull
    @JsonProperty("flyway")
    private FlywayFactory flywayFactory = new FlywayFactory();

    public FlywayFactory getFlywayFactory() {
        return flywayFactory;
    }
}
