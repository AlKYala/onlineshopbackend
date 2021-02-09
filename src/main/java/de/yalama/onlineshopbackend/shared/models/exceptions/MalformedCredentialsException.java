package de.yalama.onlineshopbackend.shared.models.exceptions;

public class MalformedCredentialsException extends OnlineshopException {

    public MalformedCredentialsException() {
        super();
    }

    public MalformedCredentialsException(String message) {
        super(message);
    }
}
