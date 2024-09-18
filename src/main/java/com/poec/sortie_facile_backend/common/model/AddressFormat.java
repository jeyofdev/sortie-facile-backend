package com.poec.sortie_facile_backend.common.model;

import com.poec.sortie_facile_backend.domain.city.dto.CityDTO;
import com.poec.sortie_facile_backend.domain.department.dto.DepartmentDTO;
import com.poec.sortie_facile_backend.domain.region.dto.RegionDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AddressFormat {
    private String streetNumber;
    private String street;
    private String zipCode;
    private RegionDTO region;
    private DepartmentDTO department;
    private CityDTO city;
    private String fullAddress;

    public AddressFormat(
            String streetNumber,
            String street,
            String zipCode,
            RegionDTO region,
            DepartmentDTO department,
            CityDTO city) {
        this.streetNumber = streetNumber;
        this.street = street;
        this.zipCode = zipCode;
        this.region = region;
        this.department = department;
        this.city = city;

        setFullAddress();
    }

    public void setFullAddress() {
        StringBuilder fullAddressBuilder = new StringBuilder();

        if (streetNumber != null && street != null) {
            fullAddressBuilder.append(streetNumber).append(" ").append(street);

            if (zipCode != null && city != null) {
                fullAddressBuilder.append(", ").append(zipCode).append(" ").append(city.name());

                if (department != null) {
                    fullAddressBuilder.append(" (").append(department.name()).append(")");
                }
            }
        }

        this.fullAddress = fullAddressBuilder.toString();
    }
}
