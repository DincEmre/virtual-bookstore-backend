package com.dincdev.virtualbookstore.event;

public class BookSyncEvent {
    private final String message;
    private final int size;

    public BookSyncEvent(String message, int size) {
        this.message = message;
        this.size = size;
    }

    public String getMessage() {
        return message;
    }

    public int getSize() {
        return size;
    }
}
