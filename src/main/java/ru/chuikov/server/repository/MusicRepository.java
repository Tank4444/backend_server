package ru.chuikov.server.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import ru.chuikov.server.entity.Music;

import javax.transaction.Transactional;
import org.springframework.data.domain.Pageable;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface MusicRepository extends JpaRepository<Music,Long> {

    @Query("select b from Music b where b.id = :id")
    Optional<Music> getById(@Param("id") long id);

    @Query("select b from Music b where b.user.id = :id")
    Optional<List<Music>> getByUserIdAndPagable(@Param("id") long id, Pageable pageable);

    @Query("select b from Music b")
    Optional<List<Music>> getAll();
}
