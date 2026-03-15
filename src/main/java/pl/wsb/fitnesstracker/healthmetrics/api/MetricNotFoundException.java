package pl.wsb.fitnesstracker.healthmetrics.api;

import pl.wsb.fitnesstracker.exception.api.NotFoundException;
import pl.wsb.fitnesstracker.healthmetrics.api.Metric;

/**
 * Exception indicating that the {@link Metric} was not found.
 */
@SuppressWarnings("squid:S110")
public class MetricNotFoundException extends NotFoundException {

    private MetricNotFoundException(String message) {
        super(message);
    }

    public MetricNotFoundException(Long id) {
        this("Training with ID=%s was not found".formatted(id));
    }

}
