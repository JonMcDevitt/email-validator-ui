package email_validator;

import java.util.regex.*;

/**
 * Created by Owner on 2017-02-23.
 */
public class EmailValidator {
    private Pattern[] regexes;

    public EmailValidator() {
        regexes = new Pattern[3];
        getValidPattern();
    }

    /**
     *  getValidPattern
     *
     *      initialize valid patterns. We scan the string in steps.
     *
     *          1.  Check that there is no whitespace
     *          2.  Check that it has a valid domain suffix (.com; modified from original email validator)
     *          3.  Check that it has an alpha-numeric (underscore inclusive) name and the domain is correct
     * */
    private void getValidPattern() {
        for(int i = 0; i < regexes.length; i++) {
            switch (i) {
                case 0:
                    /** No Whitespace   */
                    regexes[i] = Pattern.compile("^\\S*|\\D$");
                    break;
                case 1:
                    /** Alphanumeric characters only    */
                    regexes[i] = Pattern.compile(".*\\.com");
                    break;
                default:
                    regexes[i] = Pattern.compile("^[A-Za-z0-9_-]*(@).*\\.com$");
                    break;
            }
        }
    }

    public boolean validate(String email) {
        boolean matches = true;
        for(Pattern p : regexes) {
            matches &= email.matches(p.pattern());
        }
        return matches;
    }
}
