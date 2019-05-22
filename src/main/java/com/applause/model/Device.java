package com.applause.model;

import com.applause.dto.DeviceSnapshot;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(schema = "applause")
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Device {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String description;

    public DeviceSnapshot toSnapshot() {
        return DeviceSnapshot.builder()
                .id(id)
                .description(description)
                .build();
    }

}
