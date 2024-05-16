package eu.senla.naumovich.exceptions;

public class NoMoneyOnBankAccount extends RuntimeException{
    public NoMoneyOnBankAccount(String msg) {
        super(msg);
    }
}
