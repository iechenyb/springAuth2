package com.cyb.auth2.service;

import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientRegistrationException;

/**
 * Created by user on 2016/3/24.
 */
public class ClientDetailsService  implements org.springframework.security.oauth2.provider.ClientDetailsService{
    public ClientDetails loadClientByClientId(String s) throws ClientRegistrationException {
        return null;
    }
}
