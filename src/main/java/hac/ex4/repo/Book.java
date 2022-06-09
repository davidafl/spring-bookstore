package hac.ex4.repo;

import org.hibernate.validator.constraints.URL;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;


@Entity
public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private String image = "images/default-image.jpg";


    @NotEmpty(message = "Name is mandatory")
    private String bookName;

    @PositiveOrZero(message = "Price must be positive or zero")
    @NotNull(message = "Quantity is mandatory")
    private int quantity;

    @Positive
    @Min(value = 0, message = "Price must be greater than 0")
    @Column(nullable = false)
    private double price;

    @Positive
    @Min(value = 0, message = "Quantity must be greater than 0")
    @Column(nullable = false)
    private double discount;

    public Book() {}

    public Book(String bookName, int quantity, double price, double discount) {
        this.setBookName(bookName);
        this.setQuantity(quantity);
        this.setPrice(price);
        this.setDiscount(discount);
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }
    public String getBookName() { return bookName; }
    public void setBookName(String bookName) { this.bookName = bookName; }
    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) {
        if (quantity < 0) {
            throw new IllegalArgumentException("The book " + bookName + " is out of stock!");
        }
        this.quantity = quantity;
    }
    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
    public double getDiscount() { return discount; }
    public void setDiscount(double discount) { this.discount = discount; }
    public String getImage() { return image; }
    public void setImage(String image) { this.image = image; }

    public String toString() {
        return "Book: " + this.getBookName() + " | Quantity: " + this.getQuantity() + " | Price: " + this.getPrice() + " | Discount: " + this.getDiscount();
    }
}
