package com.bitspondon.quiz.infrastructure.aop;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class PerformanceTrackerHandler implements ObservationHandler<Observation.Context> {
    @Override
    public void onStart(Observation.Context context) {
        log.info("*** execution started *** {}", context.getName());
        context.put("time", System.currentTimeMillis());
        ObservationHandler.super.onStart(context);
    }

    @Override
    public void onError(Observation.Context context) {
        log.error("Error occurred {}", Objects.requireNonNull(context.getError()).getMessage());
        ObservationHandler.super.onError(context);
    }

    @Override
    public void onEvent(Observation.Event event, Observation.Context context) {
        ObservationHandler.super.onEvent(event, context);
    }

    @Override
    public void onScopeOpened(Observation.Context context) {
        ObservationHandler.super.onScopeOpened(context);
    }

    @Override
    public void onScopeClosed(Observation.Context context) {
        ObservationHandler.super.onScopeClosed(context);
    }

    @Override
    public void onScopeReset(Observation.Context context) {
        ObservationHandler.super.onScopeReset(context);
    }

    @Override
    public void onStop(Observation.Context context) {
        log.info("*** execution ended ***   {}", context.getName() + " duration " + (System.currentTimeMillis() - context.getOrDefault("time", 0L)) + " milliseconds");

        ObservationHandler.super.onStop(context);
    }

    @Override
    public boolean supportsContext(Observation.Context context) {
        return true;
    }
}
