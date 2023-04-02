package jovisimons.dekeet.UserService.controller;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService svc;
    @PostMapping
    public ResponseEntity<String> GetUserDetails(@RequestBody User user){
        svc.CreateUser(user);
        return new ResponseEntity<>("Doet het!", HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<User>> GetAllUsers(){
        return new ResponseEntity<>(svc.GetAll(), HttpStatus.OK);
    }
}
