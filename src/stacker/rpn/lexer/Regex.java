package stacker.rpn.lexer;

public class Regex {
    private static final String NUM_REGEX = "(\\d)+";
    private static final String OP_REGEX = "(\\+|-|\\*|/)";
    private static final String PLUS_REGEX = "(\\+)";
    private static final String MINUS_REGEX = "(\\-)";
    private static final String SLASH_REGEX = "(/)";
    private static final String STAR_REGEX = "(\\*)";
    public static boolean isNum(String token) {
        return token.matches(NUM_REGEX);
    }

    public static boolean isOp(String token) {
        return token.matches(OP_REGEX);
    }

    public static boolean isPlus(String token) {
        return token.matches(PLUS_REGEX);
    }

    public static boolean isMinus(String token) {
        return token.matches(MINUS_REGEX);
    }

    public static boolean isSlash(String token) {
        return token.matches(SLASH_REGEX);
    }

    public static boolean isStar(String token) {
        return token.matches(STAR_REGEX);
    }
}
