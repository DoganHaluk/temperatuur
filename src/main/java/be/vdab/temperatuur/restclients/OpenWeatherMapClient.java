package be.vdab.temperatuur.restclients;

import java.math.BigDecimal;
import java.util.Optional;

public interface OpenWeatherMapClient {
    Optional<BigDecimal> getTemperatuur(String naam);
}
