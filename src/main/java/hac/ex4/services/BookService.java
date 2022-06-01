package hac.ex4.services;

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
    public Optional<Book> getBook(long id) { return repository.findById(id); }

    public List<Book> getBooks() { return repository.findAll(); }
}
