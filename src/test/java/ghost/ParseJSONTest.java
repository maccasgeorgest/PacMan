package ghost;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.Test;

public class ParseJSONTest {

    @Test
    public void constructor(){
        assertNotNull(new ParseJSON());
    }

    @Test
    public void fileExceptions() {
        GameEvent ge = new GameEvent(null);
        ParseJSON.reader(null, ge); 
    }
}
