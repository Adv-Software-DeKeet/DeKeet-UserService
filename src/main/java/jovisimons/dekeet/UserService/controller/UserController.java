package jovisimons.dekeet.UserService.controller;

import jovisimons.dekeet.common.model.User;
import jovisimons.dekeet.UserService.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Slf4j
@RequestMapping("/api/user")
@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    UserService svc;

    @GetMapping("/test")
    public String test() {
        return "hallo";
    }

    @GetMapping
    public ResponseEntity<List<User>> GetAllUsers(@RequestHeader("id") String uid, @RequestHeader("role") String role){
        log.info("ID: "+uid);
        log.info("role: "+role);
        return new ResponseEntity<>(svc.GetAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> GetUserByID(@RequestHeader("id") String uid, @RequestHeader("role") String role, @PathVariable String id){
        if(Objects.equals(uid, id) || Objects.equals(role, "admin"))
            return new ResponseEntity<>(svc.GetUserById(id), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
    }

    @PostMapping
    public ResponseEntity<String> GetUserDetails(@RequestBody User user){
        svc.CreateUser(user);
        return new ResponseEntity<>("Doet het!", HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> UpdateUser(@RequestHeader("id") String uid, @RequestHeader("role") String role, @RequestBody User user) {
        try {
            if(Objects.equals(uid, user.getUid()) || Objects.equals(role, "admin"))
                svc.UpdateUser(user);
            else
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(user.getName()+" updated", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> DeleteUser(@RequestHeader("id") String uid, @RequestHeader("role") String role, @PathVariable String id) {
        try {
            if(Objects.equals(uid, id) || Objects.equals(role, "admin"))
                svc.DeleteUser(id);
            else
                return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
            return new ResponseEntity<>(id+" deleted", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
