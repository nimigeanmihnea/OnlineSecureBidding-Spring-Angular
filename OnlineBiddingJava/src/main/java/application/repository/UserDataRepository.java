package application.repository;

import application.entity.User;
import application.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDataRepository extends JpaRepository<UserData, Long> {

    UserData findByPersonalId(String personalId);
    UserData findByUser(User user);
}
