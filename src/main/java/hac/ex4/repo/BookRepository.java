package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Modifying
    @Query(value = "UPDATE Book SET quantity = quantity - :stock WHERE id = :id")
    void decrementStock(long id, int stock);

    // find first five books with the biggest discount (descending)
    @Query(value = "SELECT * FROM Book ORDER BY discount DESC LIMIT 5", nativeQuery = true)
    List<Book> findFirstFiveBooksWithBiggestDiscount();

    List<Book> findFirst5ByOrderByDiscountDesc();

    List<Book> findByBookNameContaining(String bookName);

    // find all books containing or equals to the given bookName
    //List<Book> findByTitleContainingOrTitle(String title, String title2);
}
