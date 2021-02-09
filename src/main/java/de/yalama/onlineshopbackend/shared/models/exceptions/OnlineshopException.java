package de.yalama.onlineshopbackend.shared.models.exceptions;

public class OnlineshopException extends RuntimeException {

    public OnlineshopException() {
        super();
    }

    public OnlineshopException(String message) {
        super(message);
    }
}
