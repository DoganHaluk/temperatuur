package be.vdab.temperatuur.restclients;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class DefaultOpenWeatherMapClientTest {
    private final DefaultOpenWeatherMapClient client;

    DefaultOpenWeatherMapClientTest(DefaultOpenWeatherMapClient client) {
        this.client = client;
    }

    @Test
    void temperatuurLigtTussenMinZestigEnZestig() {
        assertThat(client.getTemperatuur("Brussel")).hasValueSatisfying(temperatuur -> assertThat(temperatuur).isBetween(BigDecimal.valueOf(-60), BigDecimal.valueOf(60)));
    }

    @Test
    void onbestaandeGemeente() {
        assertThat(client.getTemperatuur("xxx")).isEmpty();
    }
}
