package com.vacation.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacation.module.User;

public interface UserRepository extends JpaRepository<User, Long> {

  boolean existsByIdAndTeamId(Long userId, Long teamId);

  List<User> findAllByTeamId(Long teamId);

}
