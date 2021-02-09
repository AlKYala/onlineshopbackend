package de.yalama.onlineshopbackend.shared.models.exceptions;

public class NotDeletedException extends OnlineshopException {

    public NotDeletedException() {
        super();
    }

    public NotDeletedException(String message) {
        super(message);
    }
}
