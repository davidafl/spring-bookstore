package hac.ex4;

import hac.ex4.beans.ShoppingCart;
import hac.ex4.repo.Book;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;


@Configuration
public class BeanConfiguration {

    @Bean
    @SessionScope
    public ShoppingCart myShoppingCart () {
        return new ShoppingCart();
    }

}
