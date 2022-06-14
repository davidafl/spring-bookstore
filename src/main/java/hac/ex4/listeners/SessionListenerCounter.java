package hac.ex4.listeners;

import org.springframework.stereotype.Component;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Session listener counter.
 */
@Component
@WebListener
public class SessionListenerCounter implements HttpSessionListener {

    /**
     * The counter.
     */
    private final AtomicInteger activeSessions;

    /**
     * Constructor for the session listener counter.
     */
    public SessionListenerCounter() {
        super();
        activeSessions = new AtomicInteger();
    }

    /**
     * Gets the active sessions.
     * @return the active sessions
     */
    public int getTotalActiveSession() {
        return activeSessions.get();
    }

    /**
     * Session created.
     * prints the number of active sessions when a session is created.
     * @param event - the event
     */
    public void sessionCreated(final HttpSessionEvent event) {
        activeSessions.incrementAndGet();
        System.out.println("SessionListenerCounter +++ Total active session are " + activeSessions.get());
    }

    /**
     * Session destroyed.
     * prints the number of active sessions when a session is destroyed.
     * @param event - the event
     */
    public void sessionDestroyed(final HttpSessionEvent event) {
        activeSessions.decrementAndGet();
        System.out.println("SessionListenerCounter --- Total active session are " + activeSessions.get());
    }
}
