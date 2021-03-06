package de.yalama.onlineshopbackend.Security.controller;

import de.yalama.onlineshopbackend.Security.model.AuthenticationRequest;
import de.yalama.onlineshopbackend.Security.service.LoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import static de.yalama.onlineshopbackend.Security.SecurityConstants.SIGN_UP_ENDPOINT;

@RestController
@RequiredArgsConstructor
public class LoginController {

    @Autowired
    private LoginService loginService;

    /**
     * checks if request matches with submitted data
     * if pass, checks if the email-password combination is ok
     * sends ok if data verified
     */
    @PostMapping(SIGN_UP_ENDPOINT)
    @CrossOrigin(origins = "http://localhost:4200")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
        return this.loginService.createAuthenticationToken(authenticationRequest);
    }
}
