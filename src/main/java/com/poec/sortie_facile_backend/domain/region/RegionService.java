package com.poec.sortie_facile_backend.domain.region;

import com.poec.sortie_facile_backend.core.abstracts.AbstractDomainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionService  extends AbstractDomainService<Region> {

    private final RegionRepository regionRepository;

    @Autowired
    public RegionService(RegionRepository regionRepository) {
        super(regionRepository, "region");
        this.regionRepository = regionRepository;
    }

    @Override
    public Region updateById(Region region, Long regionId) {
        Region newRegion = findById(regionId);
        System.out.println(newRegion.getName());
        newRegion.setName(region.getName());
        newRegion.setDepartments(region.getDepartments());
        newRegion.setActivities(region.getActivities());
        newRegion.setProfiles(region.getProfiles());

        return regionRepository.save(newRegion);
    }

    public void delete(Long id) {
        regionRepository.deleteById(id);
    }
}
