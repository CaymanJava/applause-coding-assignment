package com.applause.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(schema = "applause")
@AllArgsConstructor
@Data
@Builder
public class Tester extends BaseTester {

}
