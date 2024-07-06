package eu.senla.naumovich.services.service.auth;

public interface TokenBlackListService {
    public boolean isTokenInBlackList(String token);
    public void addToken2BlackList(String token);
}
