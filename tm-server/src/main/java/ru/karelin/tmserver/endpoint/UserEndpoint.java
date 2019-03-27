package ru.karelin.tmserver.endpoint;

import javax.jws.WebParam;
import javax.jws.WebService;

@WebService
public class UserEndpoint {
    public boolean checkUser (@WebParam String login, @WebParam char[] password){
        return true;
    }
}
