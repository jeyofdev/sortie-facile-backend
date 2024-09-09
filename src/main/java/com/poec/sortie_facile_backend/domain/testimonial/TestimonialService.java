package com.poec.sortie_facile_backend.domain.testimonial;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestimonialService {

    @Autowired
    private TestimonialRepository testimonialRepository;

    public List<Testimonial> getAll() {
        return testimonialRepository.findAll();
    }
}
