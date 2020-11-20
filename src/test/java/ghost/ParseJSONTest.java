package ghost;

import org.junit.Test;

public class ParseJSONTest {
    @Test
    public void fileExceptions() {
        GameEvent ge = new GameEvent(null);
        ParseJSON.reader(null, ge); 
    }
}
