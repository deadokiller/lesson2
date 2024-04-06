package ru.vtb.javapro.homework.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.vtb.javapro.homework.entity.Limit;

import java.util.Optional;

@Repository
public interface LimitRepository extends JpaRepository<Limit, Long> {
    Optional<Limit> findFirstByUserId(Long userId);
}

