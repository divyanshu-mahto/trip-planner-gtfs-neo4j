package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Stop;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface StopRepository extends Neo4jRepository<Stop, String> {

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=1Q3t5Hd62OI0AS_XeWllGlfEzZX5bqlCM' AS row\n" +
            "CREATE (s:Stop {stop_id: row.stop_id, stop_name: row.stop_name, stop_lat: toFloat(row.stop_lat), stop_lon: toFloat(row.stop_lon), parent_station : row.parent_station})")
    void loadStopRepository();

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=1Q3t5Hd62OI0AS_XeWllGlfEzZX5bqlCM' AS row\n" +
            "WITH row\n" +
            "WHERE NOT (row.parent_station IS NULL)\n" +
            "MATCH (ps:Stop {stop_id: row.parent_station}), (s:Stop {stop_id: row.stop_id})\n" +
            "MERGE (ps)<-[:PART_OF]-(s)")
    void loadParentChildRelOfStop();
}
