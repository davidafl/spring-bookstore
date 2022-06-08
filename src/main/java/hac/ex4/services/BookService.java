package hac.ex4.services;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import hac.ex4.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    @Autowired
    private BookRepository repository;

    @Transactional
    public void addUsers(ArrayList<Book> users) {
        for (Book user : users) {
            repository.save(user);
        }
    }

    public void saveBook(Book book) {
        repository.save(book);
    }

    public void deleteBook(long id) {
        repository.deleteById(id);
    }
    public void deleteBook(Book b) {
        repository.delete(b);
    }
    public void updateBook(Book book) {
        repository.save(book);
    }
    public Book getBook(long id) {
        Book b = repository.findById(id).orElseThrow(()->new IllegalArgumentException("invalid id: " + id));
        return b;
    }

    public List<Book> getBooks() { return repository.findAll(); }

    @Transactional
    public void updateStock(ShoppingCart cart){
        for(Book b : cart.getBooks()){
            Book book = repository.findById(b.getId()).orElseThrow(()->new IllegalArgumentException("invalid id: " + b.getId()));
            repository.decrementStock(book.getId(), b.getQuantity());
        }
    }
}
