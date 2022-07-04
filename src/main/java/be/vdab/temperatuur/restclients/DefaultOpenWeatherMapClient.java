package be.vdab.temperatuur.restclients;

import be.vdab.temperatuur.dto.OpenWeatherMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.math.BigDecimal;
import java.util.Optional;

@Component
class DefaultOpenWeatherMapClient implements OpenWeatherMapClient {
    private final WebClient client;
    private final String userURI;

    DefaultOpenWeatherMapClient(WebClient.Builder builder, @Value("${openweathermap}") String userURI) {
        client = builder.build();
        this.userURI = userURI;
    }

    @Override
    public Optional<BigDecimal> getTemperatuur(String naam) {
        try {
            return Optional.of(client.get()
                    .uri(userURI, uriBuilder -> uriBuilder.build(naam))
                    .retrieve()
                    .bodyToMono(OpenWeatherMap.class)
                    .block()
                    .getMain()
                    .getTemp());
        } catch (WebClientResponseException.NotFound ex) {
            return Optional.empty();
        }
    }
}
