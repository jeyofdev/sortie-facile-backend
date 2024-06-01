package com.poec.projet_backend.domain.activity;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("activity")
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService service;

    @GetMapping("/all")
    public ResponseEntity<List<Activity>> getAll() {
        List<Activity> activities = service.getAll();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Activity> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Activity> add(@RequestBody Activity activity) {
        return new ResponseEntity<>(service.add(activity), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Activity> update(@RequestBody Activity activity, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(activity, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
