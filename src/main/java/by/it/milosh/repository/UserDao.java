package by.it.milosh.repository;

import by.it.milosh.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserDao extends JpaRepository<User, Long> {

    @Query("FROM User WHERE username=:username")
    User findByUsername(@Param("username") String username);

}
