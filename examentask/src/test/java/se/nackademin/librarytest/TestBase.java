package se.nackademin.librarytest;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.junit.After;
import org.junit.Before;
import static com.codeborne.selenide.Selenide.open;

/**
 * @author testautomatisering
 */
public class TestBase {
    private static final Logger LOG = Logger.getLogger(TestBase.class.getName());
    public String usersRole;

    @Before
    public void setup() {
        open("http://localhost:8080/librarytest");
    }

    @After
    public void teardown() {

    }
    
    public static Integer convertToInteger(String input) {
    Integer output = null;
    try {
        output = Integer.valueOf(input);
    } catch (NumberFormatException numberFormatException) {
        //if an exception is caught we'll return null
    }
    return output;
    }
    
    protected void giveInfoAboutAction(String descriptor, String value) {
        LOG.log(Level.INFO, "Perform {0} {1}", new Object[]{descriptor, value});
    }
}
