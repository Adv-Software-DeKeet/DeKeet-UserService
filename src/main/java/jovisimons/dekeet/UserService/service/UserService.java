package jovisimons.dekeet.UserService.service;

import jovisimons.dekeet.UserService.model.User;
import jovisimons.dekeet.UserService.repo.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepo repo;

    public void CreateUser(User user){
        if(user.getRole().isEmpty()){
            user.setRole("default");
        }
        repo.insert(user);
    }
}
