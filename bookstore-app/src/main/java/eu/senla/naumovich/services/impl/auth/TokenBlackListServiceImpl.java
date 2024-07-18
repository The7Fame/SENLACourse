package eu.senla.naumovich.services.impl.auth;

import eu.senla.naumovich.services.service.auth.TokenBlackListService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TokenBlackListServiceImpl implements TokenBlackListService {
    List<String> tokensInBlackList = new ArrayList<>();

    @Override
    public boolean isTokenInBlackList(String token) {
        return tokensInBlackList.contains(token);
    }

    @Override
    public void addToken2BlackList(String token) {
        tokensInBlackList.add(token);
    }
}
