package jovisimons.dekeet.UserService.userUnitTest;

import jovisimons.dekeet.UserService.BaseClass;
import jovisimons.dekeet.UserService.repo.UserRepo;
import jovisimons.dekeet.UserService.service.UserService;
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

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@AutoConfigureDataMongo
@Slf4j
@ExtendWith(SpringExtension.class)
@EnableAutoConfiguration()
public class UserServiceTest extends BaseClass {
    @Autowired
    private UserRepo userRepository;

    @Autowired
    private UserService svc;
    User userGet = new User("test", "james@bond", "test", "James", "local", "default");
    User userDelete = new User("delete", "james@bond", "test", "James", "local", "default");
    User userUpdate = new User("update", "james@bond", "test", "James", "local", "default");


    @BeforeAll
    public void setup(){
        userRepository.save(userGet);
        userRepository.save(userDelete);
        userRepository.save(userUpdate);
    }

    @Test
    public void test_getById_successfull() throws Exception {
        User fetchedUser = svc.GetUserById(userGet.getUid()).orElse(null);

        assert fetchedUser != null;

        Assertions.assertEquals(userGet.getUid(), fetchedUser.getUid());
        Assertions.assertEquals(userGet.getName(), fetchedUser.getName());
        Assertions.assertEquals(userGet.getEmail(), fetchedUser.getEmail());
        Assertions.assertEquals(userGet.getRole(), fetchedUser.getRole());
    }

    @Test
    public void test_updateById_successfull() throws Exception {
        User userNewUpdate = new User("update","bond@james", "test", "Bond", "local", "host" );

        svc.UpdateUser(userNewUpdate);

        User fetchedUser = svc.GetUserById(userNewUpdate.getUid()).orElse(null);

        assert fetchedUser != null;

        Assertions.assertEquals(userNewUpdate.getUid(), fetchedUser.getUid());
        Assertions.assertEquals(userNewUpdate.getName(), fetchedUser.getName());
        Assertions.assertEquals(userNewUpdate.getEmail(), fetchedUser.getEmail());
        Assertions.assertEquals(userNewUpdate.getRole(), fetchedUser.getRole());
    }

    @Test
    public void test_deleteById_successfull() throws Exception {
         String userDeleted = "Deleted user";
         svc.DeleteUser("delete");

         User deleted = svc.GetUserById("delete").orElse(null);

        assert deleted != null;
        Assertions.assertEquals(userDeleted, deleted.getName());
        Assertions.assertEquals(userDeleted, deleted.getEmail());
        Assertions.assertEquals(userDeleted, deleted.getName());

    }

    @Test
    public void test_createUser_successfull() throws Exception {
        User userCreate = new User("create","bond@james", "test", "Bond", "local", "default" );

        svc.CreateUser(userCreate);

        User fetchedUser = svc.GetUserById(userCreate.getUid()).orElse(null);

        assert fetchedUser != null;

        Assertions.assertEquals(userCreate.getUid(), fetchedUser.getUid());
        Assertions.assertEquals(userCreate.getName(), fetchedUser.getName());
        Assertions.assertEquals(userCreate.getEmail(), fetchedUser.getEmail());
        Assertions.assertEquals(userCreate.getRole(), fetchedUser.getRole());
    }

}
