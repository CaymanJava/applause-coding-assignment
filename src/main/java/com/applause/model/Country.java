package com.applause.model;

import com.applause.dto.CountrySnapshot;
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
public class Country {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    @NotNull
    private String code;

    public CountrySnapshot toSnapshot() {
        return CountrySnapshot.builder()
                .id(id)
                .code(code)
                .build();
    }

}
