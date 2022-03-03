package oslomet.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Assert.*;

import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Transaksjon;




public class KontoTest {

    /*Med tom constructor*/

    @Test
    public void testPersonnumer(){
        Konto testKonto= new Konto();
        String personnummerInput = "123456789";

        testKonto.setPersonnummer(personnummerInput);
        String personummerOutput = testKonto.getPersonnummer();

        assertEquals("123456789", personummerOutput);
    }

    @Test
    public void testKontonummer(){
        Konto testKonto= new Konto();
        String kontonummerInput = "123456789";

        testKonto.setKontonummer(kontonummerInput);
        String kontonummerOutput = testKonto.getKontonummer();

        assertEquals("123456789", kontonummerOutput);
    }

    @Test
    public void testSaldo(){
        Konto testKonto= new Konto();
        Double saldoInput = 100.10;

        testKonto.setSaldo(saldoInput);
        Double saldoOutput = testKonto.getSaldo();
        
        assertEquals(100.10, saldoOutput);
    }

    @Test
    public void testType(){
        Konto testKonto= new Konto();
        String typeInput = "brukskonto";

        testKonto.setType(typeInput);
        String typeOutput = testKonto.getType();
        
        assertEquals(typeInput, typeOutput);
    }

    @Test
    public void testValuta(){
        Konto testKonto= new Konto();
        String valutaInput = "NOK";

        testKonto.setValuta(valutaInput);
        String valutaOutput = testKonto.getValuta();
        
        assertEquals("NOK", valutaOutput);
    }

    @Test
    public void testTransaksjoner(){
        Konto testKonto= new Konto();
        Transaksjon t1 = new Transaksjon(1, "123456", 152, "13.13.2023", "en melding", "venter", "123546789");
        Transaksjon t2 = new Transaksjon();
        Transaksjon t3 = new Transaksjon(2, "123456", 350, "13.13.2023", "en melding2", "godkjent", "123546789");

        List<Transaksjon> transaksjonerInput = new ArrayList<>();
        transaksjonerInput.add(t1);
        transaksjonerInput.add(t2);
        transaksjonerInput.add(t3);

        testKonto.setTransaksjoner(transaksjonerInput);
        List<Transaksjon> transaksjonerOutput = testKonto.getTransaksjoner();

        List<Transaksjon> transaksjoner = new ArrayList<>();
        transaksjoner.add(t1);
        transaksjoner.add(t2);
        transaksjoner.add(t3);

        
        assertEquals(transaksjoner, transaksjonerOutput);
    }

    //Med constructor
    List<Transaksjon> transaksjoner = new ArrayList<>();
    Konto testKonto= new Konto("123456798", "123456", 150, "testKonto", "USD", transaksjoner);

    @Test
    public void testPersonnumerConstructor(){
        String personummerOutput = testKonto.getPersonnummer();
        assertEquals("123456798", personummerOutput);
    }

    @Test
    public void testKontonummerConstructor(){
        String kontonummerOutput = testKonto.getKontonummer();
        assertEquals("123456", kontonummerOutput);
    }

    @Test
    public void testSaldoConstructor(){
        Double saldoOutput = testKonto.getSaldo();
        assertEquals(150, saldoOutput);
    }

    @Test
    public void testTypeConstructor(){
        String typeOutput = testKonto.getType();
        assertEquals("testKonto", typeOutput);
    }

    @Test
    public void testValutaConstructor(){
        String valutaOutput = testKonto.getValuta();
        assertEquals("USD", valutaOutput);
    }

    @Test
    public void testTransaksjonerConstructor(){
        List<Transaksjon> transaksjonerOutput = testKonto.getTransaksjoner();
        List<Transaksjon> transaksjoner = new ArrayList<>();
        assertEquals(transaksjoner, transaksjonerOutput);
    }
    
}
