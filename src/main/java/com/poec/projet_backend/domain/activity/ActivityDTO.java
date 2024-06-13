package com.poec.projet_backend.domain.activity;


public record ActivityDTO(
        Long id,
        String name,
        String date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        String hour,
        boolean isVisible
) {


    public static ActivityDTO mapFromEntity(Activity activity) {
        return new ActivityDTO(
            activity.getId(),
            activity.getName(),
            activity.getDate(),
            activity.getAge(),
            activity.getImgUrl(),
            activity.getLink(),
            activity.getDescription(),
            activity.getNbGuest(),
            activity.getHour(),
            activity.isVisible()
        );
    }

    public static Activity mapToEntity(ActivityDTO activityDTO) {
        return new Activity(
            activityDTO.name(),
            activityDTO.date(),
            activityDTO.age(),
            activityDTO.imgUrl(),
            activityDTO.link(),
            activityDTO.description(),
            activityDTO.nbGuest(),
            activityDTO.hour(),
            activityDTO.isVisible()
        );
    }
}
