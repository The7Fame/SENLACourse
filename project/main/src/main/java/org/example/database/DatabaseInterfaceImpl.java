package org.example.database;

import org.example.Autowired;
import org.example.Component;
import org.example.util.ParameterHolder;

@Component
public class DatabaseInterfaceImpl implements DatabaseInterface{
    @Autowired
    private ParameterHolder parameterHolder;

    @Override
    public String execute() {
        return parameterHolder.getSomeText();
    }
}
