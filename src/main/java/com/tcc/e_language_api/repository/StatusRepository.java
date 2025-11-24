package com.tcc.e_language_api.repository;

import com.tcc.e_language_api.entity.Status;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StatusRepository extends JpaRepository<Status, Integer> {
}
