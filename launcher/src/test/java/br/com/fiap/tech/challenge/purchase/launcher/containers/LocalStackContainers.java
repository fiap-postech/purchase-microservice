package br.com.fiap.tech.challenge.purchase.launcher.containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.startupcheck.MinimumDurationRunningStartupCheckStrategy;
import org.testcontainers.utility.DockerImageName;

import java.time.Duration;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class LocalStackContainers {

    public static GenericContainer<?> localStackContainer() {
        var container = new GenericContainer<>(
                DockerImageName.parse("fiapsoat2grupo13/localstack-resources:latest")
        ).withStartupCheckStrategy(
                new MinimumDurationRunningStartupCheckStrategy(Duration.ofSeconds(20))
        );

        container.setPortBindings(List.of(
                "4566:4566"
        ));

        return container;
    }

}
