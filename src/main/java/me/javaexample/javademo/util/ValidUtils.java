package me.javaexample.javademo.util;

public class ValidUtils {

    /**
     * check mobile number.
     * it must be started 010.
     *
     * @param mobileNo it likes 0101231234 / 010-123-1234 / 010-1234-1234.
     * @return
     */
    public static boolean validMobileNo(String mobileNo) {
        String converted = mobileNo.replaceAll("-", "");

        if (!converted.matches("\\d+")) {
            return false;
        }

        if (converted.length() == 10) {
            return converted.matches("010\\d{7}");
        } else if (converted.length() == 11) {
            return converted.matches("010\\d{8}");
        } else {
            return false;
        }
    }

    /**
     * check email address.
     *
     * @param emailAddress it likes 'test@naver.com'.
     * @return
     */
    public static boolean validEmail(String emailAddress) {
        return emailAddress.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+.[A-Za-z]{2,6}$");
    }
}
