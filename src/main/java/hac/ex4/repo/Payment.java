package hac.ex4.repo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * The payment.
 */
@Entity
public class Payment implements Serializable {

    /**
     * The id of the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    /**
     * The amount of the payment.
     */
    private double amount;

    /**
     * The date of the payment.
     */
    private LocalDateTime paymentDate = LocalDateTime.now();

    /**
     * default constructor for the payment.
     */
    public Payment() {}

    /**
     * constructor for the payment.
     * @param amount - the amount of the payment
     */
    public Payment(double amount) {
        this.amount = amount;
    }

    /**
     * Getter for the id of the payment.
     * @return - the id of the payment
     */
    public long getId() { return id; }

    /**
     * Setter for the id of the payment.
     * @param id - the id of the payment
     */
    public void setId(long id) { this.id = id; }

    /**
     * Getter for the amount of the payment.
     * @return - the amount of the payment
     */
    public double getAmount() { return amount; }

    /**
     * Setter for the amount of the payment.
     * @param amount - the amount of the payment
     */
    public void setAmount(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("The amount must be positive!");
        this.amount = amount;
    }

    /**
     * Getter for the date of the payment.
     * @return - the date of the payment
     */
    public LocalDateTime getDate() { return paymentDate; }

    /**
     * Setter for the date of the payment.
     * @param paymentDate -
     */
    public void setDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    /**
     * toString method for the payment.
     * @return - the string representation of the payment
     */
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }

    /**
     * format date to dd-mm-yyyy hh:mm:ss
     * @return - the string representation of the date
     */
    public String formatDate() {
        return paymentDate.getDayOfMonth() + "-" + paymentDate.getMonthValue() + "-" + paymentDate.getYear() + " " +
                paymentDate.getHour() + ":" + paymentDate.getMinute() + ":" + paymentDate.getSecond();
    }
}
