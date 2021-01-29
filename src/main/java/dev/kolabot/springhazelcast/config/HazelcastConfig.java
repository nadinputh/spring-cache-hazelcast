package dev.kolabot.springhazelcast.config;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.client.config.ClientConfig;
import com.hazelcast.client.config.ClientConnectionStrategyConfig;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HazelcastConfig {

    @Value("${hazelcast.server.address:localhost}")
    private String serverAddress;

    @Value("${hazelcast.server.port:5701}")
    private String serverPort;

    @Value("${hazelcast.group.name:dev}")
    private String groupName;

    @Value("${hazelcast.server.connection.attemptLimit:2}")
    private Integer attemptLimit;

    @Value("${hazelcast.server.connection.attemptPeriod:5000}")
    private Integer attemptPeriod;

    @Bean
    public ClientConfig clientConfig() {

        ClientConfig config = new ClientConfig();

        config
            .getNetworkConfig()
            .setConnectionAttemptLimit(attemptLimit);

        config
            .getNetworkConfig()
            .setConnectionAttemptPeriod(attemptPeriod);

        config
            .getConnectionStrategyConfig()
            .setReconnectMode(ClientConnectionStrategyConfig.ReconnectMode.ON);

        config
            .getNetworkConfig()
            .addAddress(String.format("%s:%s", serverAddress, serverPort));

        config
            .getGroupConfig()
            .setName(groupName);

        return config;

    }

    @Bean
    public HazelcastInstance hazelcastInstance(ClientConfig config) {
        return HazelcastClient.newHazelcastClient(config);
    }

}
