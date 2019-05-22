package com.applause.service;

import com.applause.dto.CountrySnapshot;
import com.applause.model.Country;
import com.applause.repository.CountryRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public List<CountrySnapshot> findAll() {
        log.trace("Getting all countries");
        List<CountrySnapshot> countrySnapshots = countryRepository.findAll().stream()
                .map(Country::toSnapshot)
                .collect(toList());
        log.info("Found countries {size: {}}", countrySnapshots.size());
        return countrySnapshots;
    }

}
