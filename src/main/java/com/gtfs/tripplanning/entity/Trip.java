package com.gtfs.tripplanning.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class Trip {

    @Id
    @Property("trip_id")
    private  String tripId;

    @Property("trip_short_name")
    private  String tripShortName;

    @Property("trip_headsign")
    private  String tripHeadsign;

    @Relationship(type = "TRIP_ON_ROUTE", direction = Relationship.Direction.OUTGOING)
    private Route route;

    @Relationship(type = "TRIP_STOPS_AT", direction = Relationship.Direction.OUTGOING)
    private List<Stop> stopTimes;

}

