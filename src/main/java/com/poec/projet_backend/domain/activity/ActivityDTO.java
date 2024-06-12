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
        boolean isVisible,
        Long cityId,
        Long departmentId,
        Long regionId,
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
            activity.getHour(),
            activity.isVisible(),
            activity.getCity().getId(),
            activity.getDepartment().getId(),
            activity.getRegion().getId(),
            activity.getProfile().getId()
        );
    }
}
