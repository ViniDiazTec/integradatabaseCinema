package com.cinema.db.integradatabase.data;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface AnaliseRepository extends JpaRepository<AnaliseEntity, Integer> {
}
