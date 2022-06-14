package hac.ex4.beans;

import hac.ex4.repo.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.SessionScope;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * The shopping cart.
 */
@Component
public class ShoppingCart implements Serializable {
    private ArrayList<Book> books = new ArrayList<>();
    private HashMap<Long,Integer> bookQuantities = new HashMap<>();

    /**
     * constructor for the shopping cart.
     */
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

    /**
     * getter for the books in the shopping cart
     * @return
     */
    public ArrayList<Book> getBooks() {
        return books;
    }

    /**
     * setter for the books in the shopping cart
     * @param books
     */
    public void setBooks(ArrayList<Book> books) {
        this.books = books;
    }


    public HashMap<Long, Integer> getBookQuantities() {
        return bookQuantities;
    }

    /**
     * setter for the book quantities in the shopping cart
     * @param bookQuantities
     */
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


    /**
     * remove a book from the shopping cart
     * @param id - the id of the book to remove
     */
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

    /**
     * getter for the quantity of a book in the shopping cart
     * @param id - the id of the book
     * @return
     */
    public int getBookQuantity(long id) {
        return getBookQuantities().get(id);
    }

    /**
     * getter fot the total quantity of a book in the shopping cart
     * @return
     */
    public int getTotalQuantity() {
        int total = 0;
        for (int quantity : getBookQuantities().values()) {
            total += quantity;
        }
        return total;
    }

    /**
     * getter for the total price of all books in the shopping cart
     * @return
     */
    public double getTotalPrice() {
        double total = 0;
        for (Book book : getBooks()) {
            total += book.getPrice() * getBookQuantities().get(book.getId());
        }
        return total;
    }

    /**
     * getter for the total discount of all books in the shopping cart
     * @return
     */
    public double getTotalDiscount() {
        double total = 0;
        for (Book book : getBooks()) {
            total += (book.getPrice() * book.getDiscount()/100) * getBookQuantities().get(book.getId());
        }
        return total;
    }

    /**
     * getter for the total price of all books in the shopping cart after discount
     * @return
     */
    public double getTotalPriceAfterDiscount() {
        return getTotalPrice() - getTotalDiscount();
    }

    public void setShoppingCart(ShoppingCart shoppingCart) {
        this.setBooks(shoppingCart.getBooks());
        this.setBookQuantities(shoppingCart.getBookQuantities());
    }

    /**
     * clears the shopping cart
     */
    public void clear() {
        getBooks().clear();
        getBookQuantities().clear();
    }

    public boolean isEmpty() {
        return getBooks().isEmpty();
    }

    /**
     * returns the number of books in the shopping cart
     * @return
     */
    public int getSize() {
        return getBooks().size();
    }

    public Book getBook(int index) {
        return getBooks().get(index);
    }


    /**
     * toString method for the shopping cart
     * @return
     */
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Book book : getBooks()) {
            sb.append(book.getBookName()).append(" x ").append(getBookQuantities().get(book.getId())).append("\n");
        }
        return sb.toString();
    }

}
