package org.scaler.bookmanangementservice.exceptions;

public class BookNotFoundException extends RuntimeException{

    public BookNotFoundException(Long bookId){
        super("Book not found with id : "+bookId);
    }
}
