package jovisimons.dekeet.UserService.service;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.repo.UserRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestHeader;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired
    UserRepo repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend("x.de-keet", "userRegister", user);
    }

    public Optional<User> GetUserById(String uid){
        return repo.findById(uid);
    }

    public void CreateUser(User user){
        if(user.getRole()== null)
            user.setRole("default");
        if(user.getEmail().equals("jovisimons009@gmail.com"))
            user.setRole("admin");
        repo.save(user);
        sendMessage(user);
    }

    public void UpdateUser(User user){
        repo.save(user);
        rabbitTemplate.convertAndSend("x.de-keet", "userUpdate", user);
    }

    public void DeleteUser(String uid){
        User deletedUser = repo.findById(uid).orElse(null);
        log.info("Deleting user: ", uid);
        assert deletedUser != null;
        deletedUser.setEmail("Deleted user");
        deletedUser.setName("Deleted user");
        deletedUser.setRole("Deleted user");
        deletedUser.setAuthProvider("Deleted user");
        repo.save(deletedUser);
        log.info("User deleted: ", uid);
        String routingKey = "userDelete";
        rabbitTemplate.convertAndSend("x.de-keet", routingKey, uid);
        log.info(" send to royutingkey :  ", routingKey);
    }

    public List<User> GetAll(){
        return repo.findAll();
    }
}
