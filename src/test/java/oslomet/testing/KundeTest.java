package oslomet.testing;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.Test;
import org.junit.Assert.*;

import oslomet.testing.Models.Kunde;


public class KundeTest {

    /*Med tom constructor*/
    @Test
    public void testPersonnumer(){
        Kunde testKunde = new Kunde();
        String personnummerInput = "123456789";

        testKunde.setPersonnummer(personnummerInput);
        String personummerOutput = testKunde.getPersonnummer();

        assertEquals("123456789", personummerOutput);
    }

    @Test
    public void testFornavn(){
        Kunde testKunde = new Kunde();
        String fornavnInput = "Ole";

        testKunde.setFornavn(fornavnInput);
        String fornavnOutput = testKunde.getFornavn();

        assertEquals("Ole", fornavnOutput);
    }

    @Test
    public void testEtternavn(){
        Kunde testKunde = new Kunde();
        String etternavnInput  = "Olafsen";

        testKunde.setEtternavn(etternavnInput);
        String etternavnOutput = testKunde.getEtternavn();

        assertEquals("Olafsen", etternavnOutput);
    }

    @Test
    public void testAdresse(){
        Kunde testKunde = new Kunde();
        String adresseInput  = "Engate 1";

        testKunde.setAdresse(adresseInput);
        String adresseOutput = testKunde.getAdresse();

        assertEquals("Engate 1", adresseOutput);
    }

    @Test
    public void testPostnr(){
        Kunde testKunde = new Kunde();
        String postnrInput  = "1234";

        testKunde.setPostnr(postnrInput);
        String postnrOutput = testKunde.getPostnr();

        assertEquals("1234", postnrOutput);
    }

    @Test
    public void testPoststed(){
        Kunde testKunde = new Kunde();
        String poststedInput  = "Oslo";

        testKunde.setPoststed(poststedInput);
        String poststedOutput = testKunde.getPoststed();

        assertEquals("Oslo", poststedOutput);
    }

    @Test
    public void testTelefonnr(){
        Kunde testKunde = new Kunde();
        String telefonnrInput  = "12345678";

        testKunde.setTelefonnr(telefonnrInput);
        String telefonnrOutput = testKunde.getTelefonnr();

        assertEquals("12345678", telefonnrOutput);
    }

    @Test
    public void testPassord(){
        Kunde testKunde = new Kunde();
        String passordInput  = "passord";

        testKunde.setPassord(passordInput);
        String passordOutput = testKunde.getPassord();

        assertEquals("passord", passordOutput);
    }

    //Med constructor
    Kunde testKunde = new Kunde("123456","Hansen", "Hanson", "Oslogate 2", "1234", "Drammen","123", "admin");

    @Test
    public void testPersonnumerConstructor(){
        String personummerOutput = testKunde.getPersonnummer();
        assertEquals("123456", personummerOutput);
    }

    @Test
    public void testFornavnConstructor(){
        String fornavnOutput = testKunde.getFornavn();
        assertEquals("Hansen", fornavnOutput);
    }

    @Test
    public void testEtternavnConstructor(){
        String etternavnOutput = testKunde.getEtternavn();
        assertEquals("Hanson", etternavnOutput);
    }

    @Test
    public void testAdresseConstructor(){
        String adresseOutput = testKunde.getAdresse();
        assertEquals("Oslogate 2", adresseOutput);
    }

    @Test
    public void testPostnrConstructor(){
        String postnrOutput = testKunde.getPostnr();
        assertEquals("1234", postnrOutput);
    }

    @Test
    public void testPoststedConstructor(){
        String poststedOutput = testKunde.getPoststed();
        assertEquals("Drammen", poststedOutput);
    }

    @Test
    public void testTelefonnrConstructor(){
        String telefonnrOutput = testKunde.getTelefonnr();
        assertEquals("123", telefonnrOutput);
    }

    @Test
    public void testPassordConstructor(){
        String passordOutput = testKunde.getPassord();
        assertEquals("admin", passordOutput);
    }

}
