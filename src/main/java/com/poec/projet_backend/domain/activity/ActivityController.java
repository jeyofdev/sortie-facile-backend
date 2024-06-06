package com.poec.projet_backend.domain.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService service;


    @GetMapping("/all")
    public ResponseEntity<List<ActivityDTO>> getAll() {
        List<Activity> activities = service.getAll();
        List<ActivityDTO> activityDTOS = activities.stream().map(ActivityDTO::mapFromEntity).toList();
        return new ResponseEntity<>(activityDTOS, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ActivityDTO getById(@PathVariable Long id) {
        Activity newActivity = service.getById(id);
        ActivityDTO activityDTO = ActivityDTO.mapFromEntity(newActivity);
        return activityDTO;
    }

    @PostMapping("/add")
    public ActivityDTO add(@RequestBody Activity activity) {
        Activity newActivity = service.add(activity);
        ActivityDTO activityDTO = ActivityDTO.mapFromEntity(newActivity);
        return activityDTO;
    }

    @PutMapping("update/{id}")
    public ActivityDTO update(@RequestBody Activity activity, @PathVariable Long id) {
        Activity newActivity = service.update(activity, id);
        ActivityDTO activityDTO = ActivityDTO.mapFromEntity(newActivity);
        return activityDTO;

    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
