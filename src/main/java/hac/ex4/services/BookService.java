package hac.ex4.services;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.repo.BookRepository;
import hac.ex4.repo.Payment;
import hac.ex4.repo.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * The book service.
 */
@Service
public class BookService {

    /**
     * The book repository.
     */
    @Autowired
    private BookRepository bookRepository;

    /**
     * The payment repository.
     */
    @Autowired
    private PaymentRepository paymentRepository;

    /**
     * save a book to the database.
     *
     * @param book - the book to save
     */
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    /**
     * get the 5 most discounted books.
     *
     * @return - the 5 most discounted books
     */
    public List<Book> get5DiscountBooks() {
        return bookRepository.findFirst5ByOrderByDiscountDesc();

    }

    /**
     * delete a book from the database by its name.
     *
     * @param b - the book to delete
     */
    public void deleteBook(Book b) {
        bookRepository.delete(b);
    }

    /**
     * get a book by its id.
     *
     * @param id - the id of the book to get
     * @return the book
     */
    public Book getBook(long id) {
        Book b = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return b;
    }

    /**
     * search for a book by its name.
     *
     * @param query - the name of the book to search for
     * @return a list of matching results
     */
    public List<Book> search(String query) {
        return bookRepository.findByBookNameContaining(query);
    }

    /**
     * get all books from the database.
     *
     * @return - all books
     */
    public List<Book> getBooks() {
        return bookRepository.findAll();
    }

    /**
     * get all payments from the database.
     *
     * @return - all payments
     */
    public List<Payment> getPayments() {
        return paymentRepository.findAll();
    }

    /**
     * update the stock after a cart has been checked out.
     *
     * @param cart - the cart
     */
    @Transactional
    public void updateStock(ShoppingCart cart) {
        ArrayList<Book> outOfStock = new ArrayList<>();
        for (Book b : cart.getBooks()) {
            Book book = bookRepository.findById(b.getId()).orElseThrow(() -> new IllegalArgumentException("invalid id: " + b.getId()));
            book.setQuantity(book.getQuantity() - cart.getBookQuantity(b.getId()));
        }
        paymentRepository.save(new Payment(cart.getTotalPriceAfterDiscount()));
    }
}
