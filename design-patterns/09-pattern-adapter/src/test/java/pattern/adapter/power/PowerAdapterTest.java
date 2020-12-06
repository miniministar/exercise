package pattern.adapter.power;

import org.junit.Test;

public class PowerAdapterTest {

    @Test
    public void outputDC5() {
        PowerAdapter powerAdapter = new PowerAdapter(new AC220());
        powerAdapter.outputDC5();
    }
}