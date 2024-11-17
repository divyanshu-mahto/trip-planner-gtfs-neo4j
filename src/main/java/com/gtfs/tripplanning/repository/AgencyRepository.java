package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Agency;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AgencyRepository extends Neo4jRepository<Agency, String> {

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=1DiVQllpNtyPTFcBwpVdssfNp0I4qcFzr' AS row\n"+
    "MERGE(a:Agency {agency_id : row.agency_id, agency_name : row.agency_name, agency_timezone : row.agency_timezone})")
    void loadAgencyNodes();

    @Query("CREATE CONSTRAINT agency_id_unique IF NOT EXISTS FOR (a:Agency) REQUIRE a.agency_id IS UNIQUE")
    void createAgencyConstraint();

    @Query("CREATE CONSTRAINT route_id_unique IF NOT EXISTS FOR (r:Route) REQUIRE r.route_id IS UNIQUE")
    void createRouteConstraint();

    @Query("CREATE CONSTRAINT trip_id_unique IF NOT EXISTS FOR (t:Trip) REQUIRE t.trip_id IS UNIQUE")
    void createTripConstraint();

    @Query("CREATE INDEX trip_service_id_index IF NOT EXISTS FOR (t:Trip) ON (t.service_id)")
    void createTripServiceIndex();

    @Query("CREATE CONSTRAINT stop_id_unique IF NOT EXISTS FOR (s:Stop) REQUIRE s.stop_id IS UNIQUE")
    void createStopConstraint();

    @Query("CREATE INDEX stoptime_sequence_index IF NOT EXISTS FOR (st:Stoptime) ON (st.stop_sequence)")
    void createStoptimeSequenceIndex();

    @Query("CREATE INDEX stop_name_index IF NOT EXISTS FOR (s:Stop) ON (s.name)")
    void createStopNameIndex();

}

