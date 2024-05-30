package com.poec.projet_backend.domain.region;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("region")
@RequiredArgsConstructor
public class RegionController {
    @Autowired
    private RegionService service;

    @GetMapping("/all")
    public ResponseEntity<List<Region>> getAll() {
        List<Region> regions = service.getAll();
        return new ResponseEntity<>(regions, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Region> getById(@PathVariable Long id) {
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Region> add(@RequestBody Region region) {
        return new ResponseEntity<>(service.add(region), HttpStatus.CREATED);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<Region> update(@RequestBody Region region, @PathVariable Long id) {
        return new ResponseEntity<>(service.update(region, id), HttpStatus.OK);
    }

    @DeleteMapping("delete/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }
}
