package eu.senla.naumovich.util;

import eu.senla.naumovich.annotations.Component;
import eu.senla.naumovich.annotations.Value;

@Component
public class ParameterHolder {
    @Value("${my.param.db}")
    private String someText;

    public String getSomeText(){ return someText; };
}
