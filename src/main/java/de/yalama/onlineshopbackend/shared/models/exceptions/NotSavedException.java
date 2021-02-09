package de.yalama.onlineshopbackend.shared.models.exceptions;

public class NotSavedException extends OnlineshopException {

    public NotSavedException() {
        super();
    }

    public NotSavedException(String message) {
        super(message);
    }
}
