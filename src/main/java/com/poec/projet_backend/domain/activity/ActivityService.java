package com.poec.projet_backend.domain.activity;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityRepository;
import jakarta.persistence.EntityNotFoundException;

import java.util.List;

public class ActivityService {

    private ActivityRepository repository;

    public List<Activity> getAll() {
        return repository.findAll();
    }

    public Activity getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not fouund")
                );
    }

    public Activity add(Activity activity) {
        return repository.save(activity);
    }

    public Activity update(Activity activity, Long id) {
        Activity newActivity = getById(id);
        newActivity.setName(activity.getName());
        newActivity.setDate(activity.getDate());
        newActivity.setAge(activity.getAge());
        newActivity.setImgUrl(activity.getImgUrl());
        newActivity.setLink(activity.getLink());
        newActivity.setDescription(activity.getDescription());
        newActivity.setNbGuest(activity.getNbGuest());
        newActivity.setHour(activity.getHour());
        newActivity.setVisible(activity.isVisible());

        return repository.save(newActivity);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
