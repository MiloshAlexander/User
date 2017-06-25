package by.it.milosh;

import org.junit.Test;

import java.util.Base64;

public class MainTest {

    @Test
    public void testConfigureGlobal() throws Exception {
        String auth = "dXNlcjp1c2Vy";
        System.out.println(new String(Base64.getDecoder().decode(auth)));
    }

}
