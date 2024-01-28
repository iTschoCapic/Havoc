package en.com.choca.havoc.engine;

public class Timer {
    private final long duration;
    private long startTime;
    private boolean running = false;
    private boolean requested = false;
    private long remaining;

    public Timer(long duration) {
        this.duration = duration;
        remaining = duration;
    }

    public void update(long now) {
        if (running) {
            remaining = duration - (now - startTime) / 1000000;
            if (remaining < 0) {
                running = false;
            }
        } else if (requested) {
            running = true;
            requested = false;
            startTime = now;
            remaining = duration;
        }
    }

    public long remaining() {
        return remaining;
    }

    public void start() {
        if (!running)
            requested = true;
    }

    public boolean isRunning() {
        return running || requested;
    }
}
