package com.example.BackendSAF.entity.Repository;

import com.example.BackendSAF.entity.PersonalDao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonalRepository extends JpaRepository<PersonalDao, Long> {
}
