import clock.NormalClock;
import statistic.EventsStatistic;
import statistic.EventsStatisticImpl;

public class Main {
    public static void main(String[] args) {
        EventsStatistic eventsStatistic = new EventsStatisticImpl(new NormalClock());
        eventsStatistic.incEvent("event1");
        eventsStatistic.incEvent("event2");
        eventsStatistic.incEvent("event3");
        eventsStatistic.incEvent("event2");
        eventsStatistic.incEvent("event2");
        eventsStatistic.printStatistic();
    }
}
