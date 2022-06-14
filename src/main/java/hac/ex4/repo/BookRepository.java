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
     * dercrease the quantity of a book in the store.
     * @param id - the id of the book
     * @param stock - the new stock
     */
    @Modifying
    @Query(value = "UPDATE Book SET quantity = quantity - :stock WHERE id = :id")
    void decrementStock(long id, int stock);

    /**
     * @return - the first five books with the biggest discount
     */
    @Query(value = "SELECT * FROM Book ORDER BY discount DESC LIMIT 5", nativeQuery = true)
    List<Book> findFirstFiveBooksWithBiggestDiscount();

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
