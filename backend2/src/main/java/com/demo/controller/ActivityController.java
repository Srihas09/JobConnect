package com.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.demo.entites.Activity;
import com.demo.service.ActivityService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/activity")
@CrossOrigin("*")
public class ActivityController {

    @Autowired
    private ActivityService activityService;

    
    @PostMapping("/addactivity")
    public ResponseEntity<Activity> addActivity(@RequestBody Activity activity) {
        Activity addedActivity = activityService.addActivity(activity);
        return new ResponseEntity<>(addedActivity, HttpStatus.CREATED);
    }

   
    @GetMapping("/getallactivities")
    public ResponseEntity<List<Activity>> getAllActivities() {
        List<Activity> activities = activityService.getAllActivities();
        return new ResponseEntity<>(activities, HttpStatus.OK);
    }

    
    @GetMapping("/getactivitybyjobseeker/{jobseekerId}")
    public ResponseEntity<List<Activity>> getActivityByJobseeker(@PathVariable long jobseekerId) {
        List<Activity> activities = activityService.getActivityByJobseeker(jobseekerId);
        if (activities != null && !activities.isEmpty()) {
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @GetMapping("/getactivitybyemployer/{employerId}")
    public ResponseEntity<List<Activity>> getActivityByEmployer(@PathVariable long employerId) {
        List<Activity> activities = activityService.getActivityByEmployer(employerId);
        if (activities != null && !activities.isEmpty()) {
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @GetMapping("/getactivitybyjobpost/{jobpostId}")
    public ResponseEntity<List<Activity>> getActivityByJobPost(@PathVariable long jobpostId) {
        List<Activity> activities = activityService.getActivityByJobPost(jobpostId);
        if (activities != null && !activities.isEmpty()) {
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    
    @GetMapping("/getactivitybytype/{type}")
    public ResponseEntity<List<Activity>> getActivityByType(@PathVariable String type) {
        List<Activity> activities = activityService.getActivityByType(type);
        if (activities != null && !activities.isEmpty()) {
            return new ResponseEntity<>(activities, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

   
    @DeleteMapping("deleteactivity/{activityId}")
    public ResponseEntity<Activity> deleteActivity(@PathVariable long activityId) {
        Optional<Activity> activity = activityService.deleteActivity(activityId);
        if (activity.isPresent()) {
            return new ResponseEntity<>(activity.get(), HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
