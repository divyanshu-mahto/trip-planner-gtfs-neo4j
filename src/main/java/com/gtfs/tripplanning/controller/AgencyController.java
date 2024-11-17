package com.gtfs.tripplanning.controller;


import com.gtfs.tripplanning.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/api")
public class AgencyController {

    @Autowired
    private AgencyRepository agencyRepository;

    @Autowired
    private CalendarRepository calendarRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private StopRepository stopRepository;

    @Autowired
    private StopTimeRepository stopTimeRepository;

    @Autowired
    private TripRepository tripRepository;

    public void loadConstraints() {
        agencyRepository.createAgencyConstraint();
        agencyRepository.createRouteConstraint();
        agencyRepository.createTripConstraint();
        agencyRepository.createTripServiceIndex();
        agencyRepository.createStopConstraint();
        agencyRepository.createStoptimeSequenceIndex();
        agencyRepository.createStopNameIndex();
    }


    @GetMapping("/agency/load")
    public ResponseEntity<String> loadData(){
        loadConstraints();
        System.out.println("constraints loaded");
        agencyRepository.loadAgencyNodes();
        System.out.println("Agency Nodes Done");
        routeRepository.loadRouteNodes();
        System.out.println("Route Nodes Done");
        tripRepository.loadTripNodes();
        System.out.println("Trip Nodes Done");
        stopRepository.loadStopRepository();
        System.out.println("Stop Nodes Done");


        stopRepository.loadParentChildRelOfStop();
        System.out.println("Stop parent children relation done");


        stopTimeRepository.loadStopTimeNodes();
        System.out.println("Stop Time Nodes Done");
        stopTimeRepository.connectStopTimeSeq();
        System.out.println("Stop time nodes sequence connected");
        return ResponseEntity.ok("\nAll Done");
    }


}
