import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TriggerTest {

    @Test
    public void whenTrigger() {
        Trigger trigger = new Trigger();
        int expected = 4;
        assertThat(trigger.check(2), is(expected));
    }

}