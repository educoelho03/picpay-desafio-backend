package tech.ecoelho.picpay.backend.service;


import org.springframework.stereotype.Service;
import tech.ecoelho.picpay.backend.client.AuthorizationClient;
import tech.ecoelho.picpay.backend.dto.TransferDto;
import tech.ecoelho.picpay.backend.exceptions.PicPayException;

@Service
public class AuthorizationService {

    private final AuthorizationClient authorizationClient;

    public AuthorizationService(AuthorizationClient authorizationClient) {
        this.authorizationClient = authorizationClient;
    }

    public boolean isAuthorized(TransferDto transferDto){
        var response = authorizationClient.isAuthorized();

        if(response.getStatusCode().isError()){
            throw new PicPayException();
        }

        return response.getBody().authorized();
    }

}
