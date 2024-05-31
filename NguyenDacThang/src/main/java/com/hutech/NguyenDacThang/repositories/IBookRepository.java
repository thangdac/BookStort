package com.hutech.NguyenDacThang.repositories;

import com.hutech.NguyenDacThang.entities.Book;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends
        PagingAndSortingRepository<Book, Long>, JpaRepository<Book, Long> {

        List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);

        default List<Book> findAllBooks(Integer pageNo,
                                        Integer pageSize,
                                        String sortBy) {
            return findAll(PageRequest.of(pageNo,
                                            pageSize,
                                            Sort.by(sortBy)))

                    .getContent();
    }
}