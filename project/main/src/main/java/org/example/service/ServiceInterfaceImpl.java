package org.example.service;

import org.example.Autowired;
import org.example.Component;
import org.example.database.DatabaseInterface;

@Component
public class ServiceInterfaceImpl implements ServiceInterface{
    private DatabaseInterface databaseInterface;
    @Autowired
    private void setDatabaseInterface(DatabaseInterface databaseInterface){
        this.databaseInterface = databaseInterface;
    }

    @Override
    public String execute() {
        return databaseInterface.execute();
    }
}
