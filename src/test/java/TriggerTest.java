import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void whenTrigger() {
        Trigger trigger = new Trigger();
        int expected = 8;
        assertThat(trigger.check(4), is(expected));
    }

}