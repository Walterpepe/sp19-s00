import org.junit.Test;
import static org.junit.Assert.*;

public class TestOffByN {
    // You must use this CharacterComparator and not instantiate
    // new ones, or the autograder might be upset.

    // Your tests go here.
    @Test
    public void TestEqualChars(){
        CharacterComparator offBy5 = new OffByN(5);

        assertTrue(offBy5.equalChars('a', 'f'));  // true
        assertTrue(offBy5.equalChars('f', 'a'));  // true
        assertFalse(offBy5.equalChars('f', 'h')); // false

    }

}
