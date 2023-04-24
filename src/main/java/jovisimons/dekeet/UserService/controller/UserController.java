package jovisimons.dekeet.UserService.controller;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/user")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService svc;

    @GetMapping
    public ResponseEntity<List<User>> GetAllUsers(){
        return new ResponseEntity<>(svc.GetAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> GetUserByID(@PathVariable String id){
        return new ResponseEntity<>(svc.GetUserById(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> GetUserDetails(@RequestBody User user){
        svc.CreateUser(user);
        return new ResponseEntity<>("Doet het!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateUser(@RequestBody User user) {
        try {
            svc.UpdateUser(user);
            return new ResponseEntity<>(user.getName()+" updated", HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@PathVariable String id) {
        try {
            svc.DeleteUser(id);
            return new ResponseEntity<>(id+" deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
