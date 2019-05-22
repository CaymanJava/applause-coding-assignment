package com.applause.service;

import com.applause.dto.DeviceSnapshot;
import com.applause.model.Device;
import com.applause.repository.DeviceRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Slf4j
@AllArgsConstructor
public class DeviceService {

    private final DeviceRepository deviceRepository;

    public List<DeviceSnapshot> findAll() {
        log.trace("Getting all devices");
        List<DeviceSnapshot> deviceSnapshots = deviceRepository.findAll().stream()
                .map(Device::toSnapshot)
                .collect(toList());
        log.info("Found devices {size: {}}", deviceSnapshots.size());
        return deviceSnapshots;
    }

}
