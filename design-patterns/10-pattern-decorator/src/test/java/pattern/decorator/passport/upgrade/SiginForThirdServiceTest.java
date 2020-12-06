package pattern.decorator.passport.upgrade;

import org.junit.Test;
import pattern.decorator.passport.old.SigninService;

import java.io.BufferedReader;

import static org.junit.Assert.*;

public class SiginForThirdServiceTest {

    @Test
    public void login() {

        ISiginForThirdService siginForThirdService = new SiginForThirdService(new SigninService());
        siginForThirdService.loginForQQ("111");

        //BufferedReader
    }
}