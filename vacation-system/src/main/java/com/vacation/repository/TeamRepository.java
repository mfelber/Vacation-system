package com.vacation.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.vacation.module.Team;

public interface TeamRepository extends JpaRepository<Team, Long> {

}
