package pattern.observer.event.mouseevent;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MouseTest {
    private static Mouse mouse;
    @Before
    public void setUp() throws Exception {
        mouse = new Mouse();
    }

    @Test
    public void click() {
        MouseEventCallback callback = new MouseEventCallback();
        mouse.addLisenter(MouseEventType.ON_CLICK, callback);
        mouse.click();
    }

    @Test
    public void doubleClick() {
    }

    @Test
    public void up() {
    }

    @Test
    public void down() {
    }

    @Test
    public void move() {
    }

    @Test
    public void wheel() {
    }

    @Test
    public void over() {
    }

    @Test
    public void blur() {
    }

    @Test
    public void focus() {
    }
}