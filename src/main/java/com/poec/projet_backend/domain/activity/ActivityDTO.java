package com.poec.projet_backend.domain.activity;


import java.util.Date;

public record ActivityDTO(
        Long id,
        String name,
        Date date,
        int age,
        String imgUrl,
        String link,
        String description,
        int nbGuest,
        boolean isVisible,
        Long regionId,
        Long departmentId,
        Long cityId,
        Long profileId
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
                activity.isVisible(),
                activity.getRegion().getId(),
                activity.getDepartment().getId(),
                activity.getCity().getId(),
                activity.getProfile().getId()
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
//                activityDTO.hour(),
                activityDTO.isVisible()
        );
    }
}
