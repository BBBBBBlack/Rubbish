public class Util {
    public static boolean isNum(String str) {
        return str != null && str.chars().allMatch(Character::isDigit);
    }

}
