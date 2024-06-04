package com.poec.projet_backend.domain.region;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RegionService {

    @Autowired
    private RegionRepository repository;

    public List<Region> getAll() {
        return repository.findAll();
    }

    public Region getById(Long id) {
        return repository.findById(id)
                .orElseThrow(
                        () -> new EntityNotFoundException(id + " not found")
                );
    }

    public Region add(Region region) {
        return repository.save(region);
    }

    public Region update(Region region, Long id) {
        Region newRegion = getById(id);
        newRegion.setName(region.getName());

        return repository.save(newRegion);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }
}
