package jovisimons.dekeet.UserService;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

@SpringBootTest(
        properties = "de.flapdoodle.mongodb.embedded.version=5.0.5"
)
public class BaseClass {
    @MockBean
    RabbitTemplate rabbitTemplate;
}
