package edu.eci.cvds.project.repository;
import edu.eci.cvds.project.model.Laboratory;
import edu.eci.cvds.project.model.Reservation;
import edu.eci.cvds.project.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface UserMongoRepository extends UserRepository,MongoRepository<User, String> {


    @Override
    default boolean existsById(String id) {
        User user = findUserById(id);
        return user != null;
    }


    private String generateId() {
        StringBuilder id = new StringBuilder();
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        do {
            for (int i = 0; i < 8; i++) {
                id.append(characters.charAt((int) Math.floor(Math.random() * characters.length())));
            }
        } while (existsById(id.toString()));
        return id.toString();
    }


    @Override
    default User saveUser(User user) {
        if (user.getId() == null) {
            user.setId(generateId());
        }
        save(user);
        return user;
    }


    @Override
    default User findUserById(String id) {
        return findById(id).orElse(null);
    }


    @Override
    default List<User> findAllUsers() {
        return findAll();
    }


    @Override
    default void deleteUserById(String id) {
        if (!existsById(id)) {
            throw new RuntimeException("User not found");
        }
        deleteById(id);
    }


    @Override
    default User updateUser(User user) {
        if (!existsById(user.getId())) {
            throw new RuntimeException("User not found");
        }
        save(user);
        return user;
    }

}
