package ru.chuikov.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chuikov.server.entity.Role;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface RoleRepository extends JpaRepository<Role,Long> {

    @Query("select b from Role b where b.name = :name")
    public Role findRoleByName(@Param("name")String name);

}
