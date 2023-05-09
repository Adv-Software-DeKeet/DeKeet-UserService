package jovisimons.dekeet.UserService.userIntegrationTest;

import jovisimons.dekeet.UserService.BaseClass;
import jovisimons.dekeet.UserService.repo.UserRepo;
import jovisimons.dekeet.common.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class UserControllerTest extends BaseClass {
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private UserRepo userRepository;

    private final String requester = "643f039b4cfb80328538472d";
    private final String requested = "644cd2bb4476ba444e83e313";
    private final String path = "/api/user";

    private final String role = "admin";

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
    public void admin_can_update_other_user() throws Exception {
        this.mockMvc.perform(put(path+"/"+requested)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":\"updateNew\",\"name\":\"JoviSimons\",\"authProvidor\":\"local\",\"email\":\"jovisimons@gmail.com\"}")
                        .header("id",requester)
                        .header("role",role))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void admin_can_delete_other_user() throws Exception {
        this.mockMvc.perform(delete(path+"/"+"delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("id",requester)
                        .header("role",role))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void create_new_user() throws Exception {
        this.mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"JoviSimons\",\"authProvidor\":\"local\",\"email\":\"jovisimons@gmail.com\"}")
                        .header("id",requester)
                        .header("role","default"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void update_user() throws Exception {
        this.mockMvc.perform(put(path+"/"+"update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":\"update\",\"name\":\"JoviSimons\",\"authProvidor\":\"local\",\"email\":\"jovisimons@gmail.com\"}")
                        .header("id","update")
                        .header("role","default"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void delete_user() throws Exception {
        this.mockMvc.perform(delete(path+"/"+"delete")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("id","delete")
                        .header("role","default"))
                .andDo(print())
                .andExpect(status().isOk());
    }
}
