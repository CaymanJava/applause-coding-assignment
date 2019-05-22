package com.applause.repository;

import com.applause.model.TesterStatistic;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Set;

public interface TesterStatisticRepository extends JpaRepository<TesterStatistic, Long> {

    @Query(nativeQuery = true,
            value = "SELECT \n" +
                    "DISTINCT testers.*,\n" +
                    "COUNT (bugs.id) OVER (PARTITION BY testers.id) AS criteria_bug_count \n" +
                    "FROM applause.tester testers \n" +
                    "LEFT JOIN applause.tester_device tester_device ON tester_device.tester_id = testers.id \n" +
                    "LEFT JOIN applause.bug bugs ON bugs.device_id = tester_device.device_id AND bugs.tester_id = tester_device.tester_id \n" +
                    "WHERE tester_device.device_id IN (:deviceIds) \n" +
                    "AND testers.country_id IN (:countryIds) \n" +
                    "ORDER BY COUNT(bugs.id) OVER (PARTITION BY testers.id) DESC",
            countQuery = "SELECT COUNT(DISTINCT testers.*) \n" +
                    "FROM applause.tester testers \n" +
                    "LEFT JOIN applause.tester_device tester_device ON tester_device.tester_id = testers.id \n" +
                    "LEFT JOIN applause.bug bugs ON bugs.device_id = tester_device.device_id AND bugs.tester_id = tester_device.tester_id \n" +
                    "WHERE tester_device.device_id IN (:deviceIds) \n" +
                    "AND testers.country_id IN (:countryIds)")
    Page<TesterStatistic> findWithBugSorting(@Param("deviceIds") Set<Long> deviceIds, @Param("countryIds") Set<Long> countryIds, Pageable pageable);

}
