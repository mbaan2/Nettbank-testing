package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKontoController;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Konto;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Models.Transaksjon;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstesAdminKontoController {
    @InjectMocks
    private AdminKontoController adminKontoController;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void hentAlleKonti_loggetInnOK(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Transaksjon t1 = new Transaksjon(1, "123", 50, "01.01.2022", "til øl", "godkjent", "456");
        Transaksjon t2 = new Transaksjon(2, "123", 180, "01.12.2022", "middag", "godkjent", "456");
        Transaksjon t3 = new Transaksjon(3, "123", 5, "01.24.2022", "vipps", "avventer", "456");
        transkasjoner.add(t1);
        transkasjoner.add(t2);
        transkasjoner.add(t3);

        List<Konto> alleKontoer = new ArrayList<>();
        Konto konto1 = new Konto("123456", "123", 150.15, "brukskonto", "NOK", transkasjoner);
        Konto konto2 = new Konto("34523523", "456", 18.15, "sparekonto", "USD", transkasjoner);
        alleKontoer.add(konto1);
        alleKontoer.add(konto2);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.hentAlleKonti()).thenReturn(alleKontoer);

        List<Konto> resultat = adminKontoController.hentAlleKonti();
        assertEquals(alleKontoer, resultat);
    }

    @Test
    public void hentAlleKonti_loggetInnFeil(){
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.hentAlleKonti()).thenReturn(null);

        List<Konto> resultat = adminKontoController.hentAlleKonti();
        assertNull(resultat);
    }

    @Test
    public void hentAlleKonti_ikkeloggetInn(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Transaksjon t1 = new Transaksjon(1, "123", 50, "01.01.2022", "til øl", "godkjent", "456");
        Transaksjon t2 = new Transaksjon(2, "123", 180, "01.12.2022", "middag", "godkjent", "456");
        Transaksjon t3 = new Transaksjon(3, "123", 5, "01.24.2022", "vipps", "avventer", "456");
        transkasjoner.add(t1);
        transkasjoner.add(t2);
        transkasjoner.add(t3);

        List<Konto> alleKontoer = new ArrayList<>();
        Konto konto1 = new Konto("123456", "123", 150.15, "brukskonto", "NOK", transkasjoner);
        Konto konto2 = new Konto("34523523", "456", 18.15, "sparekonto", "USD", transkasjoner);
        alleKontoer.add(konto1);
        alleKontoer.add(konto2);

        when(sjekk.loggetInn()).thenReturn(null);

        List<Konto> resultat = adminKontoController.hentAlleKonti();
        assertNull(resultat);
    }

    @Test
    public void registrerKonto_loggetInnOK(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("123456", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.registrerKonto(konto)).thenReturn("OK");

        String resultat  = adminKontoController.registrerKonto(konto);
        assertEquals("OK", resultat);
    }

    @Test
    public void registrerKonto_loggetInnFeil(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("0", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.registrerKonto(konto)).thenReturn("Feil");

        String resultat  = adminKontoController.registrerKonto(konto);
        assertEquals("Feil", resultat);
    }

    @Test
    public void registrerKonto_ikkeloggetInn(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("123456", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat  = adminKontoController.registrerKonto(konto);
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void endreKonto_loggetInnOK(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("123456", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.endreKonto(konto)).thenReturn("OK");

        String resultat  = adminKontoController.endreKonto(konto);
        assertEquals("OK", resultat);
    }

    @Test
    public void endreKonto_loggetInnFeil(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("0", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.endreKonto(konto)).thenReturn("Feil i personnummer");

        String resultat  = adminKontoController.endreKonto(konto);
        assertEquals("Feil i personnummer", resultat);
    }

    @Test
    public void endreKonto_ikkeloggetInn(){
        List<Transaksjon> transkasjoner = new ArrayList<>();
        Konto konto = new Konto("0", "123", 150.15, "brukskonto", "NOK", transkasjoner);

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat  = adminKontoController.endreKonto(konto);
        assertEquals("Ikke innlogget", resultat);
    }

    @Test
    public void slettKonto_loggetInnOK(){
        String kontonummer = "123";

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.slettKonto(kontonummer)).thenReturn("OK");

        String resultat  = adminKontoController.slettKonto(kontonummer);
        assertEquals("OK", resultat);
    }

    @Test
    public void slettKonto_loggetInnFeil(){
        String kontonummer = "0";

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.slettKonto(kontonummer)).thenReturn("Feil kontonummer");

        String resultat  = adminKontoController.slettKonto(kontonummer);
        assertEquals("Feil kontonummer", resultat);
    }

    @Test
    public void slettKonto_loggetikkeInn(){
        String kontonummer = "0";

        when(sjekk.loggetInn()).thenReturn(null);

        String resultat  = adminKontoController.slettKonto(kontonummer);
        assertEquals("Ikke innlogget", resultat);
    }
}
