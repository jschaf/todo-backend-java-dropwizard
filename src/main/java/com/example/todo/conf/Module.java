package com.example.todo.conf;

import com.example.todo.db.Database;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.ProvisionException;

public class Module extends AbstractModule {


    private Database database;

    @Override
    protected void configure() {
    }

    @Provides
    Database providesDatabase() {
        if (database == null) {
            throw new ProvisionException("The database has not yet been set.  "
                    + "You must call setDatabase in Application#run().");
        }

        return database;

    }

    public void setDatabase(Database db) {
        this.database = db;
    }
}
