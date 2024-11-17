package com.gtfs.tripplanning.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;

@Node
@Data
public class Stop {

    @Id
    @Property("stop_id")
    private String stopId;

    @Property("stop_name")
    private String stopName;

    @Property("stop_lat")
    private Double stopLat;

    @Property("stop_lon")
    private Double stopLon;

}
