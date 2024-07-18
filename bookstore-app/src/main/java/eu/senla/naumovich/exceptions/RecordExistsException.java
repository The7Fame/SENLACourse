package eu.senla.naumovich.exceptions;

public class RecordExistsException extends RuntimeException{
    public RecordExistsException(String msg){
        super(msg);
    }
}
