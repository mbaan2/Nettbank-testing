package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import oslomet.testing.API.AdminKundeController;
import oslomet.testing.DAL.AdminRepository;
import oslomet.testing.Models.Kunde;
import oslomet.testing.Sikkerhet.Sikkerhet;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstesAdminKundeController {
    @InjectMocks
    private AdminKundeController adminKundeController;

    @Mock
    private AdminRepository adminRepository;

    @Mock
    private Sikkerhet sjekk;

    @Test
    public void hentAll_loggetInnOK(){
        List<Kunde> kunder = new ArrayList<>();
        Kunde kunde1 = new Kunde("123456","Per", "Pettersen", "Trondhjemsgate 212", "1234", "Tromsø","12345679", "passord");
        Kunde kunde2 = new Kunde("123456","Hansen", "Hanson", "Oslogate 2", "1234", "Drammen","123", "admin");
        kunder.add(kunde1);
        kunder.add(kunde2);

        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.hentAlleKunder()).thenReturn(kunder);

        List<Kunde> resultat = adminKundeController.hentAlle();
        assertEquals(kunder, resultat);
    }

    @Test
    public void hentAll_loggetInnFeil(){
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.hentAlleKunder()).thenReturn(null);

        List<Kunde> resultat = adminKundeController.hentAlle();
        assertNull(resultat);
    }

    @Test
    public void hentAll_ikkeloggetInn(){
        when(sjekk.loggetInn()).thenReturn(null);

        List<Kunde> resultat = adminKundeController.hentAlle();
        assertNull(resultat);
    }

    @Test
    public void lagreKunde_loggetInnOK(){
        Kunde kunde = new Kunde("123456","Per", "Pettersen", "Trondhjemsgate 212", "1234", "Tromsø","12345679", "passord");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.registrerKunde(kunde)).thenReturn("OK");

        String resultat = adminKundeController.lagreKunde(kunde);
        assertEquals("OK", resultat);
    }

    @Test
    public void lagreKunde_loggetInnFeil(){
        Kunde kunde = new Kunde("0","Per", "Pettersen", "Trondhjemsgate 212", "1234", "Tromsø","12345679", "passord");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.registrerKunde(kunde)).thenReturn("Feil");

        String resultat = adminKundeController.lagreKunde(kunde);
        assertEquals("Feil", resultat);
    }

    @Test
    public void lagreKunde_ikkeloggetInn(){
        Kunde kunde = new Kunde("0","Per", "Pettersen", "Trondhjemsgate 212", "1234", "Tromsø","12345679", "passord");
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.lagreKunde(kunde);
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void endre_loggetInnOK(){
        Kunde kunde = new Kunde("123456","Hansen", "Hanson", "Oslogate 2", "1234", "Drammen","123", "admin");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.endreKundeInfo(kunde)).thenReturn("OK");

        String resultat = adminKundeController.endre(kunde);
        assertEquals("OK", resultat);
    }

    @Test
    public void endre_loggetInnFeil(){
        Kunde kunde = new Kunde("0","Hansen", "Hanson", "Oslogate 2", "1234", "Drammen","123", "admin");
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.endreKundeInfo(kunde)).thenReturn("Feil");

        String resultat = adminKundeController.endre(kunde);
        assertEquals("Feil", resultat);
    }

    @Test
    public void endre_ikkeloggetInn(){
        Kunde kunde = new Kunde("123456","Hansen", "Hanson", "Oslogate 2", "1234", "Drammen","123", "admin");
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.endre(kunde);
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void slett_loggetInnOK(){
        String personnummer = "12214213432";
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.slettKunde(personnummer)).thenReturn("OK");

        String resultat = adminKundeController.slett(personnummer);
        assertEquals("OK", resultat);
    }

    @Test
    public void slett_loggetInnFeil(){
        String personnummer = "0";
        when(sjekk.loggetInn()).thenReturn("01010110523");
        when(adminRepository.slettKunde(personnummer)).thenReturn("Feil");

        String resultat = adminKundeController.slett(personnummer);
        assertEquals("Feil", resultat);
    }

    @Test
    public void slett_ikkeloggetInn(){
        String personnummer = "12214213432";
        when(sjekk.loggetInn()).thenReturn(null);

        String resultat = adminKundeController.slett(personnummer);
        assertEquals("Ikke logget inn", resultat);
    }


}
