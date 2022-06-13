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

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private PaymentRepository paymentRepository;

    @Transactional
    public void addUsers(ArrayList<Book> users) {
        for (Book user : users) {
            bookRepository.save(user);
        }
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public List<Book> get5DiscountBooks() {
        //return bookRepository.findFirstFiveBooksWithBiggestDiscount();
        return bookRepository.findFirst5ByOrderByDiscountDesc();

    }

    public void deleteBook(long id) {
        bookRepository.deleteById(id);
    }

    public void deleteBook(Book b) {
        bookRepository.delete(b);
    }

    public void updateBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBook(long id) {
        Book b = bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("invalid id: " + id));
        return b;
    }

    public List<Book> search(String query) {
        return bookRepository.findByBookNameContaining(query);
    }

    public List<Book> getBooks() { return bookRepository.findAll(); }

    public List<Payment> getPayments() { return paymentRepository.findAll(); }

    @Transactional
    public void updateStock(ShoppingCart cart){
        ArrayList<Book> outOfStock = new ArrayList<>();
        for(Book b : cart.getBooks()) {
            Book book = bookRepository.findById(b.getId()).orElseThrow(() -> new IllegalArgumentException("invalid id: " + b.getId()));
            try {
                book.setQuantity(book.getQuantity() - cart.getBookQuantity(b.getId()));
            } catch (Exception e) {
                outOfStock.add(b);
            }
            bookRepository.save(book);
        }
        if (outOfStock.size() > 0) {
            throw new IllegalArgumentException("The following books are out of stock: " + outOfStock);
        }
        paymentRepository.save(new Payment(cart.getTotalPriceAfterDiscount()));
    }
}
