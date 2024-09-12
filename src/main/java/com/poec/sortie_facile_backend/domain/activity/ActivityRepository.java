package com.poec.sortie_facile_backend.domain.activity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
//    Activity findById(Optional<Long> aLong);
/*@Query("SELECT COUNT(b) FROM Booking b WHERE b.activity.id = :activityId")
int countBookingsByActivityId(@Param("activityId") Long activityId);*/


}
