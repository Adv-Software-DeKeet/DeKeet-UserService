package jovisimons.dekeet.UserService.service;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.repo.UserRepo;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(User user) {
        rabbitTemplate.convertAndSend("x.de-keet", "q.auth", user);
    }

    public void CreateUser(User user){
        if(user.getRole()== null){
            user.setRole("default");
        }
        repo.insert(user);
        sendMessage(user);
    }
}
