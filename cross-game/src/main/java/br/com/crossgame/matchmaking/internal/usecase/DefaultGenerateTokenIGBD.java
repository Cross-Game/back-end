package br.com.crossgame.matchmaking.internal.usecase;

import br.com.crossgame.matchmaking.api.model.TokenResponse;
import br.com.crossgame.matchmaking.api.usecase.GenerateTokenIGDB;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

@Service
public class DefaultGenerateTokenIGBD implements GenerateTokenIGDB {

    private String clientId;

    private String clientSecret;

    private String grantType;

    private String url;

    public DefaultGenerateTokenIGBD(@Value("${custom.api.client-id}")String clientId,
                                    @Value("${custom.api.client-secret}")String clientSecret,
                                    @Value("${custom.api.grant-type}")String grantType,
                                    @Value("${custom.api.url-token}")String url) {
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.grantType = grantType;
        this.url = url;
    }

    @Override
    public TokenResponse execute() {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<>(params(), new HttpHeaders());
        ResponseEntity<TokenResponse> tokenResponse =
                restTemplate.postForEntity(this.url, requestEntity, TokenResponse.class);
        if (tokenResponse.getStatusCode().isError()){
            throw new ResponseStatusException(HttpStatus.valueOf(tokenResponse.getStatusCodeValue()));
        }
        return tokenResponse.getBody();
    }

    private MultiValueMap<String, String> params(){
        MultiValueMap<String, String> requestParams= new LinkedMultiValueMap<>();
        requestParams.add("client_id", this.clientId);
        requestParams.add("client_secret", this.clientSecret);
        requestParams.add("grant_type", this.grantType);
        return requestParams;
    }
}
