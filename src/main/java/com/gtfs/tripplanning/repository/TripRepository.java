package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Trip;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TripRepository extends Neo4jRepository<Trip, String> {

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=1aS-7Zhq4TpL3sjJLqH1IwGLa5RlCN4hP' AS row\n" +
            "MATCH (r:Route {route_id: row.route_id})\n" +
            "MERGE (r)<-[:USES]-(t:Trip {trip_id: row.trip_id, service_id: row.service_id})")
    void loadTripNodes();
}
