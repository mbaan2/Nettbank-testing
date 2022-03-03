package oslomet.testing;

import org.junit.Test;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TransaksjonTest {

    //Uten controller

    @Test
    public void testTxID(){
        Transaksjon testTransaksjon = new Transaksjon();
        int txIDInput = 98765424;

        testTransaksjon.setTxID(txIDInput);
        int txIDOutput = testTransaksjon.getTxID();

        assertEquals(98765424, txIDOutput);
    }

    @Test
    public void testFraTilKontonummer(){
        Transaksjon testTransaksjon = new Transaksjon();
        String fraTilKontonummerInput = "1234567";

        testTransaksjon.setFraTilKontonummer(fraTilKontonummerInput);
        String fraTilKontonummerOutput = testTransaksjon.getFraTilKontonummer();

        assertEquals("1234567", fraTilKontonummerOutput);
    }

    @Test
    public void testBelop(){
        Transaksjon testTransaksjon = new Transaksjon();
        double belopInput = 345;

        testTransaksjon.setBelop(belopInput);
        Double belopOutput = testTransaksjon.getBelop();

        assertEquals(345, belopOutput);
    }

    @Test
    public void testDato(){
        Transaksjon testTransaksjon = new Transaksjon();
        String datoInput = "13.13.2013";

        testTransaksjon.setDato(datoInput);
        String dataOutput = testTransaksjon.getDato();

        assertEquals("13.13.2013", dataOutput);
    }

    @Test
    public void testMelding(){
        Transaksjon testTransaksjon = new Transaksjon();
        String meldingInput = "en melding";

        testTransaksjon.setMelding(meldingInput);
        String meldingOutput = testTransaksjon.getMelding();

        assertEquals("en melding", meldingOutput);
    }

    @Test
    public void testAvventer(){
        Transaksjon testTransaksjon = new Transaksjon();
        String avventerInput = "venter";

        testTransaksjon.setAvventer(avventerInput);
        String avventerOutput = testTransaksjon.getAvventer();

        assertEquals("venter", avventerOutput);
    }

    @Test
    public void testKontonummer(){
        Transaksjon testTransaksjon = new Transaksjon();
        String kontonummerInput = "12456875";

        testTransaksjon.setKontonummer(kontonummerInput);
        String kontonummerOutput = testTransaksjon.getKontonummer();

        assertEquals("12456875", kontonummerOutput);
    }

    //Med constructor
    Transaksjon testTransaksjon = new Transaksjon(123, "123456", 5000.15, "01.01.0001", "Hei", "godkjent", "123456879");

    @Test
    public void testTxIDConstructor(){
        int txIDOutput = testTransaksjon.getTxID();
        assertEquals(123, txIDOutput);
    }

    @Test
    public void testFraTilKontonummerConstructor(){
        String fraTilKontonummerOutput = testTransaksjon.getFraTilKontonummer();
        assertEquals("123456", fraTilKontonummerOutput);
    }

    @Test
    public void testBelopConstructor(){
        Double belopOutput = testTransaksjon.getBelop();
        assertEquals(5000.15, belopOutput);
    }

    @Test
    public void testDatoConstructor(){
        String dataOutput = testTransaksjon.getDato();
        assertEquals("01.01.0001", dataOutput);
    }

    @Test
    public void testMeldingConstructor(){
        String meldingOutput = testTransaksjon.getMelding();
        assertEquals("Hei", meldingOutput);
    }

    @Test
    public void testAvventerConstructor(){
        String avventerOutput = testTransaksjon.getAvventer();
        assertEquals("godkjent", avventerOutput);
    }

    @Test
    public void testKontonummerConstructor(){
        String kontonummerOutput = testTransaksjon.getKontonummer();
        assertEquals("123456879", kontonummerOutput);
    }


}
