package eu.senla.naumovich.exceptions;

public class OrderAlreadyPaidException extends RuntimeException{
    public OrderAlreadyPaidException(String msg){
        super(msg);
    }
}
