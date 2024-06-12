package com.poec.projet_backend.domain.booking;

import com.poec.projet_backend.domain.activity.Activity;
import com.poec.projet_backend.domain.activity.ActivityRepository;
import com.poec.projet_backend.domain.profile.Profile;
import com.poec.projet_backend.domain.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class BookingService {

    @Autowired
    private BookingRepository repository;
    @Autowired
    private ActivityRepository activityRepository;
    @Autowired
    private ProfileRepository profileRepository;

    public List<Booking> getAll() {
        return repository.findAll();
    }

    public Booking getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Booking add(Booking booking) {
        return repository.save(booking);
    }

    public Booking update(Booking booking, Long id) {
        Booking newBooking = getById(id);
        newBooking.setCreatedAt(booking.getCreatedAt());
        newBooking.setActivities(booking.getActivities());
        newBooking.setProfiles(booking.getProfiles());

        return repository.save(newBooking);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
