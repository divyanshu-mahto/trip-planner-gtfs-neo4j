package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Stop;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StopTimeRepository extends Neo4jRepository<Stop, String> {

    @Query(value =
            "CALL apoc.periodic.iterate( " +
                    "  'LOAD CSV WITH HEADERS FROM \"https://drive.google.com/uc?export=download&id=1HXGzovFnCOLiyErp8E99_O0HVcD9Vp_-\" AS row RETURN row', " +
                    "  'WITH row " +
                    "   MATCH (t:Trip {trip_id: row.trip_id}) " +
                    "   OPTIONAL MATCH (s:Stop {stop_id: row.stop_id}) " +
                    "   MERGE (st:Stoptime { " +
                    "     arrival_time: row.arrival_time, " +
                    "     departure_time: row.departure_time, " +
                    "     stop_sequence: toInteger(row.stop_sequence), " +
                    "     trip_id: row.trip_id " +
                    "   }) " +
                    "   ON CREATE SET st.stop_sequence = toInteger(row.stop_sequence) " +
                    "   MERGE (t)<-[:PART_OF_TRIP]-(st)-[:LOCATED_AT]->(s)', " +
                    "  {batchSize: 1000, parallel:false}" +
                    ")")
    void loadStopTimeNodes();



    @Query("MATCH (s1:Stoptime)-[:PART_OF_TRIP]->(t:Trip),\n" +
            "(s2:Stoptime)-[:PART_OF_TRIP]->(t)\n" +
            "WHERE s2.stop_sequence = s1.stop_sequence+1\n" +
            "MERGE (s1)-[:PRECEDES]->(s2)")
    void connectStopTimeSeq();

}
