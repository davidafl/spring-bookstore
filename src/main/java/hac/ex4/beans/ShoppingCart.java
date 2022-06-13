package hac.ex4.beans;

import hac.ex4.repo.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

@Component
public class ShoppingCart implements Serializable {
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<Long,Integer> bookQuantities = new HashMap<>();


    // CTOR
    public ShoppingCart() {}

    /**
     * Add a book to the shopping cart
     * @param book the book to add
     */
    public void addBook(Book book) {
        if (bookQuantities.containsKey(book.getId())) {
            bookQuantities.put(book.getId(), bookQuantities.get(book.getId()) + 1);
        } else {
            bookQuantities.put(book.getId(), 1);
            books.add(book);
        }
    }

    public ArrayList<Book> getBooks() {
        return books;
    }

    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


    public HashMap<Long, Integer> getBookQuantities() {
        return bookQuantities;
    }

    public void setBookQuantities(HashMap<Long, Integer> bookQuantities) {
        this.bookQuantities = bookQuantities;
    }

    /**
     * Remove a book from the shopping cart
     * @param book the book to remove
     */
    public void removeBook(Book book) {
        getBooks().remove(book);
        getBookQuantities().remove(book.getId());
    }

    public void removeBook(long id) {
        for (int i = 0; i < getBooks().size(); i++) {
            if (getBooks().get(i).getId() == id) {
                getBooks().remove(i);
                getBookQuantities().remove(id);
                break;
            }
        }
    }

    public void updateBook(Book book, int quantity) {
        getBookQuantities().put(book.getId(), quantity);
    }

    public void updateBook(long id, int quantity) {
        getBookQuantities().put(id, quantity);
    }

    public int getBookQuantity(long id) {
        return getBookQuantities().get(id);
    }

    public int getTotalQuantity() {
        int total = 0;
        for (int quantity : getBookQuantities().values()) {
            total += quantity;
        }
        return total;
    }

    public double getTotalPrice() {
        double total = 0;
        for (Book book : getBooks()) {
            total += book.getPrice() * getBookQuantities().get(book.getId());
        }
        return total;
    }

    public double getTotalDiscount() {
        double total = 0;
        for (Book book : getBooks()) {
            total += (book.getPrice() * book.getDiscount()/100) * getBookQuantities().get(book.getId());
        }
        return total;
    }

    public double getTotalPriceAfterDiscount() {
        return getTotalPrice() - getTotalDiscount();
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.setBooks(shoppingCart.getBooks());
        this.setBookQuantities(shoppingCart.getBookQuantities());
    }

    public void clear() {
        getBooks().clear();
        getBookQuantities().clear();
    }

    public boolean isEmpty() {
        return getBooks().isEmpty();
    }

    public int getSize() {
        return getBooks().size();
    }

    public Book getBook(int index) {
        return getBooks().get(index);
    }


    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : getBooks()) {
            sb.append(book.getBookName()).append(" x ").append(getBookQuantities().get(book.getId())).append("\n");
        }
        return sb.toString();
    }

}
