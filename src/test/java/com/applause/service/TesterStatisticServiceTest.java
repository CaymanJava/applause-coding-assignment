package com.applause.service;

import com.applause.dto.TesterStatisticSnapshot;
import com.applause.request.TesterStatisticRequest;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.HashSet;
import java.util.List;

import static com.google.common.collect.Sets.newHashSet;
import static java.util.Collections.emptySet;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TesterStatisticServiceTest {

    private final static Pageable DEFAULT_PAGE = PageRequest.of(0, 100);
    private final static Long US_ID = 1L;
    private final static Long GB_ID = 2L;
    private final static Long JP_ID = 3L;

    private final static Long IPHONE_4_ID = 1L;
    private final static Long IPHONE_4S_ID = 2L;
    private final static Long IPHONE_5_ID = 3L;
    private final static Long GALAXY_S3_ID = 4L;
    private final static Long GALAXY_S4_ID = 5L;
    private final static Long NEXUS_4_ID = 6L;
    private final static Long DROID_RAZOR_ID = 7L;
    private final static Long DROID_DNA_ID = 8L;
    private final static Long HTC_ONE_ID = 9L;
    private final static Long IPHONE_3_ID = 10L;

    @ClassRule
    public static PostgreSQLContainer postgreSQLContainer = CustomPostgresqlContainer.getInstance()
            .withInitScript("schema.sql");
    @Autowired
    private TesterStatisticService testerStatisticService;

    @Test
    public void testWithEmptyCriteria() {
        List<TesterStatisticSnapshot> statistic = testerStatisticService.findAll(emptyRequest(), DEFAULT_PAGE).getContent();
        assertThat(statistic.size(), is(9));
        assertThat(statistic.get(0).getId(), is(4L));
        assertThat(statistic.get(0).getCriteriaBugCount(), is(125L));
        assertThat(statistic.get(8).getId(), is(2L));
        assertThat(statistic.get(8).getCriteriaBugCount(), is(99L));
    }

    @Test
    public void testWithAllCriteria() {
        List<TesterStatisticSnapshot> statistic = testerStatisticService.findAll(allParamsRequest(), DEFAULT_PAGE).getContent();
        assertThat(statistic.size(), is(9));
        assertThat(statistic.get(0).getId(), is(4L));
        assertThat(statistic.get(0).getCriteriaBugCount(), is(125L));
        assertThat(statistic.get(8).getId(), is(2L));
        assertThat(statistic.get(8).getCriteriaBugCount(), is(99L));
    }

    @Test
    public void testWithAllCountriesAndIPhones() {
        List<TesterStatisticSnapshot> statistic = testerStatisticService.findAll(new TesterStatisticRequest(allCountries(), iPhones()), DEFAULT_PAGE).getContent();
        assertThat(statistic.size(), is(6));
        assertThat(statistic.get(0).getId(), is(4L));
        assertThat(statistic.get(0).getCriteriaBugCount(), is(125L));
        assertThat(statistic.get(5).getId(), is(3L));
        assertThat(statistic.get(5).getCriteriaBugCount(), is(32L));
    }

    @Test
    public void testWithGreatBritainAndAllPhones() {
        List<TesterStatisticSnapshot> statistic = testerStatisticService.findAll(new TesterStatisticRequest(newHashSet(GB_ID), allPhones()), DEFAULT_PAGE).getContent();
        assertThat(statistic.size(), is(3));
        assertThat(statistic.get(0).getId(), is(6L));
        assertThat(statistic.get(0).getCriteriaBugCount(), is(110L));
        assertThat(statistic.get(2).getId(), is(9L));
        assertThat(statistic.get(2).getCriteriaBugCount(), is(104L));
    }

    @Test
    public void testWithJapanAndAllAndroidPhones() {
        List<TesterStatisticSnapshot> statistic = testerStatisticService.findAll(new TesterStatisticRequest(newHashSet(GB_ID), androidPhones()), DEFAULT_PAGE).getContent();
        assertThat(statistic.size(), is(2));
        assertThat(statistic.get(0).getId(), is(9L));
        assertThat(statistic.get(0).getCriteriaBugCount(), is(104L));
        assertThat(statistic.get(1).getId(), is(3L));
        assertThat(statistic.get(1).getCriteriaBugCount(), is(74L));
    }

    private TesterStatisticRequest emptyRequest() {
        return TesterStatisticRequest.builder()
                .countryIds(emptySet())
                .deviceIds(emptySet())
                .build();
    }

    private TesterStatisticRequest allParamsRequest() {
        return TesterStatisticRequest.builder()
                .countryIds(allCountries())
                .deviceIds(allPhones())
                .build();
    }

    private HashSet<Long> allPhones() {
        return newHashSet(IPHONE_4_ID, IPHONE_4S_ID, IPHONE_5_ID,
                GALAXY_S3_ID, GALAXY_S4_ID, NEXUS_4_ID,
                DROID_RAZOR_ID, DROID_DNA_ID, HTC_ONE_ID, IPHONE_3_ID);
    }

    private HashSet<Long> iPhones() {
        return newHashSet(IPHONE_4_ID, IPHONE_4S_ID, IPHONE_5_ID, IPHONE_3_ID);
    }

    private HashSet<Long> androidPhones() {
        return newHashSet(GALAXY_S3_ID, GALAXY_S4_ID, NEXUS_4_ID,
                DROID_RAZOR_ID, DROID_DNA_ID, HTC_ONE_ID);
    }

    private HashSet<Long> allCountries() {
        return newHashSet(US_ID, GB_ID, JP_ID);
    }

}
