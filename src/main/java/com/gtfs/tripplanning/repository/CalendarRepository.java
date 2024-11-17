package com.gtfs.tripplanning.repository;

import com.gtfs.tripplanning.entity.Agency;
import org.springframework.data.neo4j.repository.Neo4jRepository;
import org.springframework.data.neo4j.repository.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends Neo4jRepository<Agency, String> {

    @Query("LOAD CSV WITH HEADERS FROM 'https://drive.google.com/uc?export=download&id=15_I6H3LvArVyEhKb4yBNbDPEUL3Od-nK' AS row\n" +
            "MERGE(c:Calendar {service_id: row.service_id, monday: toInteger(row.monday), tuesday: toInteger(row.tuesday), wednesday: toInteger(row.wednesday), " +
            "thursday: toInteger(row.thursday), friday : toInteger(row.friday), saturday: toInteger(row.saturday), sunday : toInteger(row.sunday), start_date: toInteger(row.start_date), end_date: toInteger(row.end_date)})")
    void loadCalenderNodes();
}
