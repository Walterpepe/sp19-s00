public class OffByOne implements CharacterComparator {
    // equalChars returns true for characters that are different
    // by exactly one. For example the following calls to obo
    // should return true. Note that characters are delineated
    //  in Java by single quotes, in contrast to Strings,
    //  which use double quotes.
    // OffByOne obo = new OffByOne();
    // obo.equalChars('a', 'b');  // true
    // obo.equalChars('r', 'q');  // true

    // obo.equalChars('a', 'e');  // false
    // obo.equalChars('z', 'a');  // false
    // obo.equalChars('a', 'a');  // false
    @Override
    public boolean equalChars(char x, char y) {
        return Math.abs(x - y) == 1;
    }
}
