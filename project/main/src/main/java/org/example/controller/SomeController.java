package org.example.controller;

import org.example.Autowired;
import org.example.Component;
import org.example.service.ServiceInterface;

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
