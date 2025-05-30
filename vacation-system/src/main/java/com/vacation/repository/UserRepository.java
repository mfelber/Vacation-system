package com.vacation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacation.module.User;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByIdAndTeamId(Long userId, Long teamId);

}
