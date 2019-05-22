package com.applause.mapper;

import com.applause.dto.TesterStatisticSnapshot;
import com.applause.model.Bug;
import com.applause.model.Device;
import com.applause.model.TesterStatistic;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

@Component
public class TesterStatisticMapper {

    public TesterStatisticSnapshot toSnapshot(TesterStatistic tester) {
        return TesterStatisticSnapshot.builder()
                .id(tester.getId())
                .firstName(tester.getFirstName())
                .lastName(tester.getLastName())
                .countryCode(tester.getCountry().getCode())
                .bugs(bugsToSnapshot(tester.getBugs()))
                .criteriaBugCount(tester.getCriteriaBugCount())
                .lastLogin(tester.getLastLogin())
                .build();
    }

    private Map<String, Long> bugsToSnapshot(Set<Bug> bugs) {
        return bugs.stream()
                .map(Bug::getDevice)
                .map(Device::getDescription)
                .collect(groupingBy(identity(), counting()));
    }

}
