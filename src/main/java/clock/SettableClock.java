package clock;

import java.time.Instant;

public class SettableClock implements Clock {
    private Instant instant;

    public SettableClock(Instant instant) {
        this.instant = instant;
    }

    @Override
    public Instant instant() {
        return instant;
    }

    public void setInstant(Instant instant) {
        this.instant = instant;
    }
}
