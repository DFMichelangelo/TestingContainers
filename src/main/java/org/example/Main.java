package org.example;

import org.testcontainers.clickhouse.ClickHouseContainer;
import org.testcontainers.utility.DockerImageName;

import java.util.HashMap;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        HashMap<String, String> clickHouseEnv = new HashMap<>();
        clickHouseEnv.put("CLICKHOUSE_DB", "database");
        clickHouseEnv.put("CLICKHOUSE_USER", "adminadmin");
        clickHouseEnv.put("CLICKHOUSE_PASSWORD", "adminadmin");
        try (var clickhouse =
                     new ClickHouseContainer(DockerImageName.parse("clickhouse/clickhouse-server:24.5"))
                             .withEnv(clickHouseEnv)
                             .withExposedPorts(8123)
        ) {
            clickhouse.setPortBindings(List.of("8123:8123"));
            clickhouse.start();
            System.out.println("container started");
        }
    }
}