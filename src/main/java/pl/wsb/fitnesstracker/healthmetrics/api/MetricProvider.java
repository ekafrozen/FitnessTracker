package pl.wsb.fitnesstracker.healthmetrics.api;

import pl.wsb.fitnesstracker.user.api.User;

import java.util.Optional;

public interface MetricProvider {

    /**
     * Retrieves a training based on their ID.
     * If the user with given ID is not found, then {@link Optional#empty()} will be returned.
     *
     * @param metricId id of the training to be searched
     * @return An {@link Optional} containing the located Training, or {@link Optional#empty()} if not found
     */
    Optional<User> getMetric(Long metricId);

}
