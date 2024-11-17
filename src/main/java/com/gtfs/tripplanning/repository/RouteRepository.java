package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Route;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RouteRepository extends Neo4jRepository<Route, String> {

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=1vTlSQBJRwOBfNjjBIguOL1j_13By98bs' AS row\n " +
            "MATCH (a:Agency {agency_id: row.agency_id})\n" +
            "MERGE (a)-[:OPERATES]->(r:Route {route_id: row.route_id, route_short_name: row.route_short_name, route_long_name: row.route_long_name, route_type: toInteger(row.route_type)})")
    void loadRouteNodes();

}
