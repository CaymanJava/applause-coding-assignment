package com.applause.service;

import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.utility.MountableFile;

public class CustomPostgresqlContainer extends PostgreSQLContainer<CustomPostgresqlContainer> {
    private static final String IMAGE_VERSION = "postgres:11.1";
    private static CustomPostgresqlContainer container;

    private CustomPostgresqlContainer() {
        super(IMAGE_VERSION);
    }

    static CustomPostgresqlContainer getInstance() {
        if (container == null) {
            container = new CustomPostgresqlContainer();
        }
        return container;
    }

    @Override
    public void start() {
        super.start();
        System.setProperty("DB_URL", container.getJdbcUrl());
        System.setProperty("DB_USERNAME", container.getUsername());
        System.setProperty("DB_PASSWORD", container.getPassword());
        container.copyFileToContainer(MountableFile.forClasspathResource("bugs.csv"), "/docker-entrypoint-initdb.d/bugs.csv");
        container.copyFileToContainer(MountableFile.forClasspathResource("tester_device.csv"), "/docker-entrypoint-initdb.d/tester_device.csv");
    }

    @Override
    public void stop() {
        //do nothing, JVM handles shut down
    }
}
