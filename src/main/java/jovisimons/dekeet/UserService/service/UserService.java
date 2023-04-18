package jovisimons.dekeet.UserService.service;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.repo.UserRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend("x.de-keet", "userRegister", user);
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
        repo.deleteById(uid);
        rabbitTemplate.convertAndSend("x.de-keet", "deleteUser", uid);
    }

    public List<User> GetAll(){
        return repo.findAll();
    }
}
