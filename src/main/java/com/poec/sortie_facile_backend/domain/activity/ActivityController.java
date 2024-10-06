package com.poec.sortie_facile_backend.domain.activity;

import com.poec.sortie_facile_backend.common.model.DataCountResponse;
import com.poec.sortie_facile_backend.domain.activity.dto.ActivityDTO;
import com.poec.sortie_facile_backend.domain.activity.dto.SaveActivityDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.poec.sortie_facile_backend.core.constants.RouteConstants.*;

@RestController
@RequestMapping(BASE_URL + ACTIVITY)
@RequiredArgsConstructor
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ActivityMapper activityMapper;

    @GetMapping(ALL)
    public ResponseEntity<List<ActivityDTO>> getAll() {
        List<Activity> activityList = activityService.findAll();
        List<ActivityDTO> activityDTOS = activityList.stream().map(activity -> activityMapper.mapFromEntity(activity, false, false)).toList();

        return new ResponseEntity<>(activityDTOS, HttpStatus.OK);
    }

    @GetMapping(ID)
    public ResponseEntity<ActivityDTO> getById(@PathVariable("id") Long activityId) {
        Activity activity = activityService.findById(activityId);
        ActivityDTO activityDTO = activityMapper.mapFromEntity(activity, false, false);

        return new ResponseEntity<>(activityDTO, HttpStatus.OK);
    }

    @PostMapping(ADD + REGION + "/{regionId}" + DEPARTMENT + "/{departmentId}" + CITY + "/{cityId}" + PROFILE + "/{profileId}")
    public ResponseEntity<ActivityDTO> add(
            @Valid @RequestBody SaveActivityDTO saveActivityDTO,
            @PathVariable("regionId") Long regionId,
            @PathVariable("departmentId") Long departmentId,
            @PathVariable("cityId") Long cityId,
            @PathVariable("profileId") Long profileId
    ) {
        Activity activity = activityMapper.mapToEntity(saveActivityDTO);
        Activity newActivity = activityService.add(activity, regionId, departmentId, cityId, profileId);
        ActivityDTO newActivityDTO = activityMapper.mapFromEntity(newActivity, false, false);

        return new ResponseEntity<>(newActivityDTO, HttpStatus.CREATED);
    }

    @PutMapping(UPDATE)
    public ResponseEntity<ActivityDTO> updateById(@Valid @RequestBody SaveActivityDTO saveActivityDTO, @PathVariable("id") Long activityId) {


        Activity activity = activityMapper.mapToEntity(saveActivityDTO);
        Activity updatedActivity = activityService.updateById(activity, activityId);
        ActivityDTO updatedActivityDTO = activityMapper.mapFromEntity(updatedActivity, false, false);

        return new ResponseEntity<>(updatedActivityDTO, HttpStatus.OK);
    }

    @DeleteMapping(DELETE)
    public ResponseEntity<Void> deleteById(@PathVariable("id") Long activityId) {
        activityService.deleteById(activityId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

   @GetMapping("/{activityId}/count-bookings")
    public ResponseEntity<DataCountResponse> countBookingsForActivity(@PathVariable("activityId") Long activityId) {
       DataCountResponse count = activityService.countBookingsByActivityId(activityId);
        return new ResponseEntity<>(count, HttpStatus.OK);
    }
}
