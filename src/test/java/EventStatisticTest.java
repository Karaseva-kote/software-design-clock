import clock.Clock;
import clock.SettableClock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

import java.time.Duration;
import java.time.Instant;

public class EventStatisticTest {
    @Test
    public void emptyTest() {
        Clock clock = new SettableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        Assertions.assertEquals(0.0, eventsStatistic.getEventStatisticByName("event"));
    }

    @Test
    public void incTest() {
        Clock clock = new SettableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("event");
        Assertions.assertEquals(1.0 / 60, eventsStatistic.getEventStatisticByName("event"));
    }

    @Test
    public void commonTest() {
        Clock clock = new SettableClock(Instant.now());
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("event1");
        eventsStatistic.incEvent("event2");
        eventsStatistic.incEvent("event3");
        eventsStatistic.incEvent("event2");
        eventsStatistic.incEvent("event2");
        Assertions.assertEquals(1.0 / 60, eventsStatistic.getEventStatisticByName("event1"));
        Assertions.assertEquals(3.0 / 60, eventsStatistic.getEventStatisticByName("event2"));
        Assertions.assertEquals(1.0 / 60, eventsStatistic.getEventStatisticByName("event3"));
    }

    @Test
    public void offsetTest() {
        Instant now = Instant.now();
        SettableClock clock = new SettableClock(now);
        EventsStatistic eventsStatistic = new EventsStatisticImpl(clock);
        eventsStatistic.incEvent("event");
        clock.setInstant(now.plus(Duration.ofHours(1)));
        Assertions.assertEquals(0.0, eventsStatistic.getEventStatisticByName("event"));
    }
}
