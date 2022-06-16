package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * The book repository.
 */
public interface BookRepository extends JpaRepository<Book, Long> {

    /**
     * @return - the first five books with the biggest discount
     */
    List<Book> findFirst5ByOrderByDiscountDesc();

    /**
     * find a book containing or equal to the given name.
     * @param bookName - the name of the book
     * @return - a list of search result
     */
    List<Book> findByBookNameContaining(String bookName);
}
