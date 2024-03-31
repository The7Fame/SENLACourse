package eu.senla.naumovich.service;

import eu.senla.naumovich.annotations.Autowired;
import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.database.DatabaseInterface;

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
