package com.poec.projet_backend.domain.testimonial;

import com.poec.projet_backend.domain.city.City;
import com.poec.projet_backend.domain.city.CityDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.poec.projet_backend.util.Route.ALL;
import static com.poec.projet_backend.util.Route.BASE_URL;

@RestController
@RequestMapping(BASE_URL + "/testimonial")
@RequiredArgsConstructor
public class TestimonialController {

    @Autowired
    private TestimonialService testimonialService;

    @GetMapping(ALL)
    public ResponseEntity<List<TestimonialDTO>> getAll() {
        List<Testimonial> testimonials = testimonialService.getAll();
        List<TestimonialDTO> testimonialDTOS = testimonials.stream().map(TestimonialDTO::mapFromEntity).toList();
        return new ResponseEntity<>(testimonialDTOS, HttpStatus.OK);
    }

}
