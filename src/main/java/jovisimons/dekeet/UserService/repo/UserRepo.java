package jovisimons.dekeet.UserService.repo;

import jovisimons.dekeet.common.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepo extends MongoRepository<User, String> {
}