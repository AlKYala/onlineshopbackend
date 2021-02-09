package de.yalama.onlineshopbackend.shared.models.exceptions;

public class NotFoundException extends OnlineshopException {

    public NotFoundException() {
        super();
    }

    public NotFoundException(String message) {
        super(message);
    }
}
