package br.com.fiap.tech.challenge.purchase.launcher.containers;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class DatabaseContainers {

    public static MySQLContainer<?> localDatabaseContainer(){
        return new MySQLContainer<>(DockerImageName.parse("mysql:8.0"))
                .withDatabaseName("purchase")
                .withUsername("tech_challenge")
                .withPassword("102030");
    }

}
