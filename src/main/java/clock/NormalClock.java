package clock;

import java.time.Instant;

public class NormalClock implements Clock {
    @Override
    public Instant instant() {
        return Instant.now();
    }
}
