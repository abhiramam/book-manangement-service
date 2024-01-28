package org.scaler.bookmanangementservice.repositories;

import org.scaler.bookmanangementservice.models.BooksInfoVO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BooksRepository extends JpaRepository<BooksInfoVO,Long> {
}
