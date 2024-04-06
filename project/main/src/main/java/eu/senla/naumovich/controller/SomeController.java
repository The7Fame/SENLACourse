package eu.senla.naumovich.controller;

import eu.senla.naumovich.annotations.Autowired;
import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.service.ServiceInterface;

@Component
public class SomeController {

    private final ServiceInterface serviceInterface;
    @Autowired
    public SomeController(ServiceInterface serviceInterface){
        this.serviceInterface = serviceInterface;
    }

    public String execute(){
        return serviceInterface.execute();
    }
}
