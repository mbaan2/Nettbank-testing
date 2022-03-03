package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.BankController;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestBankController {

    @InjectMocks
    // denne skal testes
    private BankController bankController;

    @Mock
    // denne skal Mock'es
    private BankRepository repository;

    @Mock
    // denne skal Mock'es
    private Sikkerhet sjekk;

    @Test
    public void hentKundeInfo_loggetInnOK() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKundeInfo(anyString())).thenReturn(enKunde);

        // act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertEquals(enKunde, resultat);
    }

    @Test
    public void hentKundeInfo_IkkeloggetInn() {

        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        //act
        Kunde resultat = bankController.hentKundeInfo();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentKonti_LoggetInn()  {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentKonti_IkkeLoggetInn()  {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentTransaksjoner_LoggetInn() {
        // arrange
        Konto konto = new Konto("1050101123456", "01010110523", 72000, "Brukskonto", "NOK", null);

        when(sjekk.loggetInn()).thenReturn("1050101123456");

        when(repository.hentTransaksjoner(konto.getKontonummer(), "", "")).thenReturn(konto);

        // act
        Konto resultat = bankController.hentTransaksjoner(konto.getKontonummer(), "", "");

        // assert
        assertEquals(konto, resultat);
    }

    @Test
    public void hentTransaksjoner_IkkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        Konto resultat = bankController.hentTransaksjoner("01010101010", "", "");

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentSaldi_LoggetInn() {
        // arrange
        List<Konto> konti = new ArrayList<>();
        Konto konto1 = new Konto("105010123456", "01010110523",
                720, "Lønnskonto", "NOK", null);
        Konto konto2 = new Konto("105010123456", "12345678901",
                1000, "Lønnskonto", "NOK", null);
        konti.add(konto1);
        konti.add(konto2);

        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.hentKonti(anyString())).thenReturn(konti);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertEquals(konti, resultat);
    }

    @Test
    public void hentSaldi_IkkeLoggetInn()  {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Konto> resultat = bankController.hentKonti();

        // assert
        assertNull(resultat);
    }

    @Test
    public void registrerBetaling_LoggetInnOK() {
        // arrange
        Transaksjon betaling = new Transaksjon(123, "02020220645", 750, "14-12-2021", "Betaling til deg", "01010110523", "godkjent");

        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.registrerBetaling(betaling)).thenReturn("OK");

        // act
        String resultat = bankController.registrerBetaling(betaling);

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void registrerBetaling_LoggetInnIkkeOK() {
        // arrange
        Transaksjon betaling = new Transaksjon(123, "02020220645", 750, "14-12-2021", "Betaling til deg", "godkjent", "01010110523");

        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.registrerBetaling(betaling)).thenReturn("Feil");

        // act
        String resultat = bankController.registrerBetaling(betaling);

        // assert
        assertEquals("Feil", resultat);
    }

    @Test
    public void registrerBetaling_IkkeLoggetInn() {
        // arrange
        Transaksjon betaling = new Transaksjon();
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.registrerBetaling(betaling);

        // assert
        assertNull(resultat);
    }

    @Test
    public void hentBetalinger_LoggetInn() {
        // arrange
        Transaksjon betaling = new Transaksjon(123, "02020220645", 750, "14-12-2021", "Betaling til deg", "avventer", "01010110523");
        Transaksjon betaling1 = new Transaksjon(456, "03030330756", 550, "12-12-2021", "Jaja", "avventer", "01010110523");
        List<Transaksjon> transaksjoner = new ArrayList<>();
        transaksjoner.add(betaling);
        transaksjoner.add(betaling1);

        when(sjekk.loggetInn()).thenReturn("105010123456");

        when(repository.hentBetalinger("105010123456")).thenReturn(transaksjoner);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertEquals(transaksjoner, resultat);
    }

    @Test
    public void hentBetalinger_IkkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.hentBetalinger();

        // assert
        assertNull(resultat);
    }

    @Test
    public void utforBetaling_LoggetInnOK() {
        // arrange
        Transaksjon betaling = new Transaksjon(123, "02020220645", 750, "14-12-2021", "Betaling til deg", "avventer", "01010110523");

        Konto konto = new Konto("0101199111111", "01010110523", 9000, "Brukskonto", "NOK", null);
        double saldo = konto.getSaldo() - betaling.getBelop();
        konto.setSaldo(saldo);

        when(sjekk.loggetInn()).thenReturn("0101199111111");

        when(repository.utforBetaling(betaling.getTxID())).thenReturn("OK");

        // act
        List<Transaksjon> ventetResultat = bankController.hentBetalinger();
        List<Transaksjon> resultat = bankController.utforBetaling(betaling.getTxID());

        // assert
        assertEquals(ventetResultat, resultat);
    }

    @Test
    public void utforBetaling_LoggetInnIkkeOK() {
        // arrange
        Transaksjon betaling = new Transaksjon(123, "02020220645", 750, "14-12-2021", "Betaling til deg", "avventer", "01010110523");

        Konto konto = new Konto("0101199111111", "01010110523", 9000, "Brukskonto", "NOK", null);
        double saldo = konto.getSaldo() - betaling.getBelop();
        konto.setSaldo(saldo);

        when(sjekk.loggetInn()).thenReturn("0101199111111");

        when(repository.utforBetaling(betaling.getTxID())).thenReturn("Feil");

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(betaling.getTxID());

        // assert
        assertNull(resultat);
    }

    @Test
    public void utforBetaling_IkkeLoggetInn() {
        // arrange
        when(sjekk.loggetInn()).thenReturn(null);

        // act
        List<Transaksjon> resultat = bankController.utforBetaling(123);

        // assert
        assertNull(resultat);
    }

    @Test
    public void endreKundeInfo_LoggetInnOK() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(enKunde)).thenReturn("OK");

        // act
        String resultat = bankController.endre(enKunde);

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void endreKundeInfo_LoggetInnIkkeOK() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn("01010110523");

        when(repository.endreKundeInfo(enKunde)).thenReturn("Feil");

        // act
        String resultat = bankController.endre(enKunde);

        // assert
        assertEquals("Feil", resultat);
    }

    @Test
    public void endreKundeInfo_IkkeLoggetInn() {
        // arrange
        Kunde enKunde = new Kunde("01010110523",
                "Lene", "Jensen", "Askerveien 22", "3270",
                "Asker", "22224444", "HeiHei");

        when(sjekk.loggetInn()).thenReturn(null);

        // act
        String resultat = bankController.endre(enKunde);

        // assert

        assertNull(resultat);
    }
}

