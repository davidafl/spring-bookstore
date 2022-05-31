package hac.ex4.repo;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty(message = "Name is mandatory")
    private String bookName;


    @PositiveOrZero
    @Column(nullable = false)
    private int quantity;

    @Positive
    @Column(nullable = false)
    @Min(value = 0, message = "Price must be greater than 0")
    private double price;

    @PositiveOrZero
    @Column(nullable = false)
    @Min(value = 0, message = "Quantity must be greater than 0")
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

    public void setQuantity(int quantity) { this.quantity = quantity; }

    public double getPrice() { return price; }

    public void setPrice(double price) { this.price = price; }

    public double getDiscount() { return discount; }

    public void setDiscount(double discount) { this.discount = discount; }
}
