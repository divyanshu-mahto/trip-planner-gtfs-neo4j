package com.gtfs.tripplanning.entity;

import lombok.Data;
import org.springframework.data.neo4j.core.schema.Id;
import org.springframework.data.neo4j.core.schema.Node;
import org.springframework.data.neo4j.core.schema.Property;
import org.springframework.data.neo4j.core.schema.Relationship;

import java.util.List;


@Node
@Data
public class Agency {

    @Id
    @Property("agency_id")
    private String agencyId;

    @Property("agency_name")
    private String agencyName;

    @Property("agency_timezone")
    private String agencyTimezone;

    @Property("agency_lang")
    private String agencyLang;

    @Relationship(type = "OPERATES", direction = Relationship.Direction.OUTGOING)
    private List<Route> routes;

}

