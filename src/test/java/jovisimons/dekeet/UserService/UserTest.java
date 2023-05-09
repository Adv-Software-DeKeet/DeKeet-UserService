package jovisimons.dekeet.UserService;

import jovisimons.dekeet.UserService.repo.UserRepo;
import jovisimons.dekeet.common.model.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.autoconfigure.data.mongo.AutoConfigureDataMongo;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Objects;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureDataMongo
@Slf4j
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration()
public class UserTest extends BaseClass{
    @Autowired
    private UserRepo userRepository;

    @BeforeAll
    public void setup(){
        User user = new User("test", "james@bond", "test", "James", "local", "default");
        userRepository.save(user);
    }

    @Test
    public void test_getById_successfull() throws Exception {
        log.info(String.valueOf(userRepository.count()));
        Assertions.assertEquals("James", Objects.requireNonNull(userRepository.findById("test").orElse(null)).getName());
    }
}
