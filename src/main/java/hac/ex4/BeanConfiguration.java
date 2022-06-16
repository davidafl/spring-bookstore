package hac.ex4;

import hac.ex4.beans.ShoppingCart;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.SessionScope;

/**
 * The bean configuration.
 */
@Configuration
public class BeanConfiguration {

    /**
     * The shopping cart.
     * @return - a new shopping cart
     */
    @Bean
    @SessionScope
    public ShoppingCart myShoppingCart () {
        return new ShoppingCart();
    }

}
