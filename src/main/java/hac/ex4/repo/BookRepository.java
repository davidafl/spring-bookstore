package hac.ex4.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface BookRepository extends JpaRepository<Book, Long> {
    @Query(value = "UPDATE Book SET quantity = quantity - :stock WHERE id = :id")
    void decrementStock(long id, int stock);
}
