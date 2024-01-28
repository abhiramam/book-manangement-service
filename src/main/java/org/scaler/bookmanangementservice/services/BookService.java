package org.scaler.bookmanangementservice.services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.scaler.bookmanangementservice.models.BooksInfoVO;
import org.scaler.bookmanangementservice.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class BookService {
    @Autowired
    BooksRepository booksRepository;

    public boolean bookExists(Long bookId){
      return booksRepository.existsById(bookId);
    }

    public List<BooksInfoVO> getAllBooks(){
        return booksRepository.findAll();
    }

    public Optional<BooksInfoVO> getBookById(Long bookId){
        return booksRepository.findById(bookId);
    }

    @Transactional
    public void deleteById(Long bookId){
        if (bookExists(bookId)){
            booksRepository.deleteById(bookId);
            log.info("Successfully deleted book with book id : {}",bookId);
        }
    }

    public void updateById(String bookInfoVo) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        BooksInfoVO booksInfoVO = objectMapper.readValue(bookInfoVo,BooksInfoVO.class);
        if (bookExists(booksInfoVO.getBookId())){
            booksRepository.save(booksInfoVO);
            log.info("Successfully updated book");
        }
    }

    public void saveNewBook(String bookInfoVo) throws JsonProcessingException{
        ObjectMapper objectMapper = new ObjectMapper();
        BooksInfoVO booksInfoVO = objectMapper.readValue(bookInfoVo,BooksInfoVO.class);
        booksRepository.save(booksInfoVO);
        log.info("Successfully added a new book");
    }
}
