package com.alumnos.adminalumnos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.alumnos.adminalumnos.model.UserEntity;


@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {
    
}
