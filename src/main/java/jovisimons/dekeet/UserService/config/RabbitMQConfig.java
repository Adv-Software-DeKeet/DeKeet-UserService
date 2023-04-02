package jovisimons.dekeet.UserService.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitMQConfig {

    @Bean
    public Declarables createKeetSchema(){
        return new Declarables(
                new FanoutExchange("x.de-keet"),
                new Queue("q.auth" ),
                new Queue("q.userUpdate" ),
                new Queue("q.userDelete" ),
                new Binding("q.auth", Binding.DestinationType.QUEUE, "x.de-keet", "auth", null),
                new Binding("q.userUpdate", Binding.DestinationType.QUEUE, "x.de-keet", "userUpdate", null),
                new Binding("q.userDelete", Binding.DestinationType.QUEUE, "x.de-keet", "userDelete", null));
    }
}
