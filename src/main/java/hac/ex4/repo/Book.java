package hac.ex4.repo;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * the admin controller.
 */
@Entity
public class Book implements Serializable {

    /**
     * the id of the book.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * the name of the book.
     */
    @NotEmpty(message="Please enter an image URL")
    private String image = "https://islandpress.org/sites/default/files/default_book_cover_2015.jpg";

    /**
     * the title of the book.
     */
    @NotEmpty(message = "Name is mandatory")
    private String bookName;

    /**
     * the quantity of the book.
     */
    @PositiveOrZero(message = "Quantity must be positive or zero")
    @NotNull(message = "Quantity is mandatory")
    private Integer quantity = 1;

    /**
     * the price of the book.
     */
    @Positive(message = "Price must be positive")
    private double price = 1;

    /**
     * the discount of the book.
     */
    @PositiveOrZero(message = "Discount must be positive or zero")
    private double discount = 0;

    /**
     * Default Constructor for the book.
     */
    public Book() {}

    /**
     * Constructor for the book.
     * @param bookName - the name of the book
     * @param quantity - the quantity of the book
     * @param price - the price of the book
     * @param discount - the discount of the book
     */
    public Book(String bookName, int quantity, double price, double discount) {
        this.setBookName(bookName);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setDiscount(discount);
    }

    /**
     * Getter for the id of the book.
     * @return - the id of the book
     */
    public long getId() { return id; }

    /**
     * Setter for the id of the book.
     * @param id - the id of the book
     */
    public void setId(long id) { this.id = id; }

    /**
     * Getter for the name of the book.
     * @return - the name of the book
     */
    public String getBookName() { return bookName; }

    /**
     * Setter for the name of the book.
     * @param bookName - the name of the book
     */
    public void setBookName(String bookName) { this.bookName = bookName; }

    /**
     * Getter for the quantity of the book.
     * @return - the quantity of the book
     */
    public Integer getQuantity() { return quantity; }

    /**
     * Setter for the quantity of the book.
     * @param quantity - the quantity of the book
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    /**
     * Getter for the price of the book.
     * @return - the price of the book
     */
    public double getPrice() { return price; }

    /**
     * Setter for the price of the book.
     * @param price - the price of the book
     */
    public void setPrice(double price) { this.price = price; }

    /**
     * Getter for the discount of the book.
     * @return - the discount of the book
     */
    public double getDiscount() { return discount; }

    /**
     * Setter for the discount of the book.
     * @param discount - the discount of the book
     */
    public void setDiscount(double discount) { this.discount = discount; }

    /**
     * Getter for the image of the book.
     * @return - the image of the book
     */
    public String getImage() { return image; }

    /**
     * Setter for the image of the book.
     * @param image - the image of the book
     */
    public void setImage(String image) { this.image = image; }

    /**
     * computes the total price of the book
     * @return - the total price of the book after discount
     */
    public double getPriceAfterDiscount() {
        return price - (price * discount / 100);
    }

    /**
     * toString method for the book.
     * @return - the string representation of the book
     */
    public String toString() {
        return "Book: " + this.getBookName() + " | Quantity: " + this.getQuantity() + " | Price: " + this.getPrice() + " | Discount: " + this.getDiscount();
    }
}
