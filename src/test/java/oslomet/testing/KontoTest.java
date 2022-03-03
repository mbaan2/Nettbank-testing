package oslomet.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.Assert.*;

import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Transaksjon;




public class KontoTest {

    @Test
    public void testPersonnumer(){
        Konto testKonto= new Konto();
        String personummerInput = "123456789";
        testKonto.setPersonnummer(personummerInput);

        String personummerOutput = testKonto.getPersonnummer();

        assertEquals(personummerInput, personummerOutput);
    }

    @Test
    public void testKontonummer(){
        Konto testKonto= new Konto();
        String kontonummerInput = "123456789";
        testKonto.setKontonummer(kontonummerInput);

        String kontonummerOutput = testKonto.getKontonummer();

        assertEquals(kontonummerInput, kontonummerOutput);
    }

    @Test
    public void testSaldo(){
        Konto testKonto= new Konto();
        Double saldoInput = 100.10;
        testKonto.setSaldo(saldoInput);

        Double saldoOutput = testKonto.getSaldo();
        
        assertEquals(saldoInput, saldoOutput);
    }

    @Test
    public void testType(){
        Konto testKonto= new Konto();
        String typeInput = "123456789";
        testKonto.setType(typeInput);

        String typeOutput = testKonto.getType();
        
        assertEquals(typeInput, typeOutput);
    }

    @Test
    public void testValuta(){
        Konto testKonto= new Konto();
        String valutaInput = "123456789";
        testKonto.setValuta(valutaInput);

        String valutaOutput = testKonto.getValuta();
        
        assertEquals(valutaInput, valutaOutput);
    }

    @Test
    public void testTransaksjoner(){
        Konto testKonto= new Konto();
        Transaksjon t1 = new Transaksjon();
        Transaksjon t2 = new Transaksjon();
        Transaksjon t3 = new Transaksjon();

        List<Transaksjon> transaksjonerInput = new ArrayList<>();
        transaksjonerInput.add(t1);
        transaksjonerInput.add(t2);
        transaksjonerInput.add(t3);

        testKonto.setTransaksjoner(transaksjonerInput);

        List<Transaksjon> transaksjonerOutput = testKonto.getTransaksjoner();
        
        assertEquals(transaksjonerInput, transaksjonerOutput);
    }


    
}
