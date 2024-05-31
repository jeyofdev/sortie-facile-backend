package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.city.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
