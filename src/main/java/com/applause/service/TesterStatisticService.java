package com.applause.service;

import com.applause.dto.CountrySnapshot;
import com.applause.dto.DeviceSnapshot;
import com.applause.dto.TesterStatisticSnapshot;
import com.applause.mapper.TesterStatisticMapper;
import com.applause.repository.TesterStatisticRepository;
import com.applause.request.TesterStatisticRequest;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static java.util.stream.Collectors.toSet;
import static org.springframework.util.CollectionUtils.isEmpty;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class TesterStatisticService {

    private final CountryService countryService;
    private final DeviceService deviceService;
    private final TesterStatisticRepository testerStatisticRepository;
    private final TesterStatisticMapper testerStatisticMapper;

    public Page<TesterStatisticSnapshot> findAll(TesterStatisticRequest request, Pageable pageable) {
        log.trace("Getting testers {request: {}, pageable: {}}", request, pageable);
        Page<TesterStatisticSnapshot> testers = testerStatisticRepository.findWithBugSorting(getDeviceIds(request), getCountryIds(request), pageable)
                .map(testerStatisticMapper::toSnapshot);
        log.info("Found testers {request: {}, size: {}}", request, testers.getNumberOfElements());
        return testers;
    }

    private Set<Long> getDeviceIds(TesterStatisticRequest request) {
        return isEmpty(request.getDeviceIds())
                ? getAllDeviceIds()
                : request.getDeviceIds();
    }

    private Set<Long> getAllDeviceIds() {
        return deviceService.findAll().stream()
                .map(DeviceSnapshot::getId)
                .collect(toSet());
    }

    private Set<Long> getCountryIds(TesterStatisticRequest request) {
        return isEmpty(request.getCountryIds())
                ? getAllCountryIds()
                : request.getCountryIds();
    }

    private Set<Long> getAllCountryIds() {
        return countryService.findAll().stream()
                .map(CountrySnapshot::getId)
                .collect(toSet());
    }

}
