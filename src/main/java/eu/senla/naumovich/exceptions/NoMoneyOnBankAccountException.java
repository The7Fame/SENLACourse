package eu.senla.naumovich.exceptions;

public class NoMoneyOnBankAccountException extends RuntimeException{
    public NoMoneyOnBankAccountException(String msg) {
        super(msg);
    }
}
