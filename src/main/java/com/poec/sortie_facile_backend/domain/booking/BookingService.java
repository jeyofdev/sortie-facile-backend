package com.poec.sortie_facile_backend.domain.booking;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import com.poec.sortie_facile_backend.domain.activity.Activity;
import com.poec.sortie_facile_backend.domain.activity.ActivityRepository;
import com.poec.sortie_facile_backend.domain.profile.Profile;
import com.poec.sortie_facile_backend.domain.profile.ProfileRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class BookingService extends AbstractDomainService<Booking> {

    private final BookingRepository bookingRepository;
    private final ActivityRepository activityRepository;
    private final ProfileRepository profileRepository;

    public BookingService(BookingRepository bookingRepository, ActivityRepository activityRepository, ProfileRepository profileRepository) {
        super(bookingRepository, "booking");

        this.bookingRepository = bookingRepository;
        this.activityRepository = activityRepository;
        this.profileRepository = profileRepository;
    }

    public Booking add(Long activityId, Long profileId) {
        Activity newActivity = activityRepository.findById(activityId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Activity with id " + activityId + " not found")
                );
        Profile newProfile = profileRepository.findById(profileId)
                .orElseThrow(
                        () -> new EntityNotFoundException("Profile with id" + profileId + " not found")
                );

        Booking booking = new Booking();
        booking.setCreatedAt(new Date().toString());
        booking.setActivity(newActivity);
        booking.setProfile(newProfile);

        return bookingRepository.save(booking);
    }

    @Override
    public Booking updateById(Booking booking, Long bookingId) {
        return findById(bookingId);
    }
}
