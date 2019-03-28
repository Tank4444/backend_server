package ru.chuikov.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.chuikov.server.entity.OauthClientDetails;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface OauthClientRepository extends JpaRepository<OauthClientDetails,Long> {

}
