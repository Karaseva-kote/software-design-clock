package statistic;

import clock.Clock;

import java.time.Duration;
import java.time.Instant;
import java.util.*;

public class EventsStatisticImpl implements EventsStatistic {
    private final Clock clock;
    private final Map<String, List<Instant>> eventInstants = new HashMap<>();

    public EventsStatisticImpl(Clock clock) {
        this.clock = clock;
    }

    @Override
    public void incEvent(String name) {
        Instant instant = clock.instant();
        eventInstants.putIfAbsent(name, new ArrayList<>());
        eventInstants.get(name).add(instant);
    }

    @Override
    public double getEventStatisticByName(String name) {
        Instant right = clock.instant();
        Instant left = right.minus(Duration.ofHours(1));
        List<Instant> instants = eventInstants.getOrDefault(name, Collections.emptyList());
        return (double) instants.stream()
                .filter(i -> i.compareTo(left) > 0 && i.compareTo(right) <= 0)
                .toList().size() / 60.0;
    }

    @Override
    public Map<String, Double> getAllEventStatistic() {
        Instant right = clock.instant();
        Instant left = right.minus(Duration.ofHours(1));
        Map<String, Double> result = new HashMap<>();
        eventInstants.forEach((name, list) -> result.put(
                name,
                (double) list.stream()
                        .filter(i -> i.compareTo(left) > 0 && i.compareTo(right) <= 0)
                        .toList().size() / 60.0
        ));
        return result;
    }

    @Override
    public void printStatistic() {
        getAllEventStatistic().forEach((name, stats) -> System.out.println(name + ": " + stats));
    }
}
