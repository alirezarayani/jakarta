package session04.challenge;

public class LogEvent {
    enum Level {SEVERE, ERROR, WARN}

    private Level level;
    private String message;

    public LogEvent(Level level, String message) {
        this.level = level;
        this.message = message;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
