package com.gtfs.tripplanning.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;

@Node
@Data
public class Route {

    @Id
    @Property("route_id")
    private String routeId;

    @Property("route_short_name")
    private String routeShortName;

    @Property("routeLongName")
    private String routeLongName;

    @Property("route_type")
    private String routeType;

    @Relationship(type = "TRIP_ON_ROUTE", direction = Relationship.Direction.INCOMING)
    private List<Trip> trips;

    @Relationship(type = "OPERATES", direction = Relationship.Direction.INCOMING)
    private Agency agency;

}
