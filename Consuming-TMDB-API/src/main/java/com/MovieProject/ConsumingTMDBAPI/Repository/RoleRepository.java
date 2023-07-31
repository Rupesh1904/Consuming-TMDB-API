package com.MovieProject.ConsumingTMDBAPI.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.MovieProject.ConsumingTMDBAPI.Entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}