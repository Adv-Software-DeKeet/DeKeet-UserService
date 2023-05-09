package jovisimons.dekeet.UserService;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
public class AuthTest extends BaseClass {

    @Autowired
    private MockMvc mockMvc;

    private final String requester = "643f039b4cfb80328538472d";
    private final String requested = "644cd2bb4476ba444e83e313";
    private final String path = "/api/user";

    @Test
    public void update_user_with_wrong_uid_should_return_unauthorized() throws Exception {
        this.mockMvc.perform(put(path+"/"+requested)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"uid\":\"Qx3kpBu9GbZxUwTbwYIAfpGVbqJ2sssssssssssss\",\"name\":\"JoviSimons\",\"authProvidor\":\"local\",\"email\":\"jovisimons@gmail.com\"}")
                        .header("id",requester)
                        .header("role","default"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void delete_user_with_wrong_uid_should_return_unauthorized() throws Exception {
        this.mockMvc.perform(delete(path+"/"+requested)
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("id",requester)
                        .header("role","default"))
                .andDo(print())
                .andExpect(status().isUnauthorized());
    }
}
