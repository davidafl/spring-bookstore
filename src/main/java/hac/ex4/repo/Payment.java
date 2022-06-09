package hac.ex4.repo;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;


@Entity
public class Payment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private double amount;

    // date and time
    private LocalDateTime paymentDate = LocalDateTime.now();

    // CTOR
    public Payment(double amount) {
        this.amount = amount;
    }

    public Payment() {}

    // getters and setters
    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) {
        if (amount < 0)
            throw new IllegalArgumentException("The amount must be positive!");
        this.amount = amount;
    }

    public LocalDateTime getDate() { return paymentDate; }
    public void setDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    // pretty print for date
    @Override
    public String toString() {
        return "Payment{" +
                "id=" + id +
                ", amount=" + amount +
                ", paymentDate=" + paymentDate +
                '}';
    }

    // format date to dd-mm-yyyy hh:mm:ss
    public String formatDate() {
        return paymentDate.getDayOfMonth() + "-" + paymentDate.getMonthValue() + "-" + paymentDate.getYear() + " " +
                paymentDate.getHour() + ":" + paymentDate.getMinute() + ":" + paymentDate.getSecond();
    }
}
