package com.poec.sortie_facile_backend.domain.testimonial;

public record TestimonialDTO(Long id,
                             String user,
                             String title,
                             String message,
                             Double note
) {
    public static TestimonialDTO mapFromEntity(Testimonial testimonial) {
        return new TestimonialDTO(
                testimonial.getId(),
                testimonial.getUser(),
                testimonial.getTitle(),
                testimonial.getMessage(),
                testimonial.getNote()
        );
    }
}
