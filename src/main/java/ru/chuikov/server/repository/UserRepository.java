package ru.chuikov.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;
import ru.chuikov.server.entity.User;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User,Long> {
    @Query("select b from User b where b.id = :id")
    Optional<User> findById(@Param("id") long id);

    @Query("select b from User b where b.email = :email")
    Optional<User> findByEmail(@Param("email")String email);

    @Query("select b from User b where b.email = :email")
    Optional<User> findDetailsByEmail(@Param("email")String email);

}
