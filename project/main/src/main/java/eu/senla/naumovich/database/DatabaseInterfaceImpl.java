package eu.senla.naumovich.database;

import eu.senla.naumovich.annotations.Autowired;
import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.util.ParameterHolder;

@Component
public class DatabaseInterfaceImpl implements DatabaseInterface{
    @Autowired
    private ParameterHolder parameterHolder;

    @Override
    public String execute() {
        return parameterHolder.getSomeText();
    }
}
