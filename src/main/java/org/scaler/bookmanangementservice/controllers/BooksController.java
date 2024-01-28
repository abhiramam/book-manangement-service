package org.scaler.bookmanangementservice.controllers;


import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.extern.slf4j.Slf4j;
import org.scaler.bookmanangementservice.exceptions.BookNotFoundException;
import org.scaler.bookmanangementservice.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/books")
public class BooksController {
    @Autowired
    BookService bookService;

    @GetMapping(path = "/")
    public ResponseEntity<Object> getAllBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping(path = "/{bookId}/")
    public ResponseEntity<Object> getBookById(@PathVariable Long bookId){
        try {
            if (!bookService.bookExists(bookId)){
                throw new BookNotFoundException(bookId);
            }
            return ResponseEntity.status(HttpStatus.OK).body(bookService.getBookById(bookId));
        } catch (Exception e){
            log.error("Failed to get book with book id : {}",bookId,e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping(path = "/book/")
    public ResponseEntity<Object> saveNewBook(@RequestBody String bookInfoVo){
        try {
            log.info("Received request to add new book");
            bookService.saveNewBook(bookInfoVo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Book created successfully");
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json",e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        } catch (Exception e){
            log.error("Please try again after sometime",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping(path = "/book/{bookId}/")
    public ResponseEntity<Object> deleteBookById(@PathVariable Long bookId){
        try {
            log.info("Received request to delete book with book id : {}",bookId);
            if (!bookService.bookExists(bookId)){
                throw new BookNotFoundException(bookId);
            }
            bookService.deleteById(bookId);
            return ResponseEntity.status(HttpStatus.OK).body("Successfully deleted book with book");
        } catch (Exception e){
            log.error("Please try again after sometime",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping(path = "/update/{bookId}/")
    public ResponseEntity<Object> updateBook(@PathVariable Long bookId, @RequestBody String bookInfoVo){
        try {
            log.info("Received request to update book with book id : {}",bookId);
            if (!bookService.bookExists(bookId)){
                throw new BookNotFoundException(bookId);
            }
            bookService.updateById(bookInfoVo);
            return ResponseEntity.status(HttpStatus.CREATED).body("Successfully update book");
        } catch (JsonProcessingException e) {
            log.error("Failed to parse json",e);
            return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
        } catch (Exception e){
            log.error("Please try again after sometime",e);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
