package oslomet.testing;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.mock.web.MockHttpSession;
import oslomet.testing.DAL.BankRepository;
import oslomet.testing.Sikkerhet.Sikkerhet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class EnhetstestSikkerhetController {

    @InjectMocks
    private Sikkerhet sikkerhetsController;

    @Mock
    private BankRepository repository;

    @Mock
    private MockHttpSession session;

    @Test
    public void sjekkLoggInnOK() {
        // arrange
        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("OK");

        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901", "HeiHeiHei");

        // assert
        assertEquals("OK", resultat);
    }

    @Test
    public void sjekkLoggInnFeilPassordRegex() {
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("12345678901", "hehe");

        // assert
        assertEquals("Feil i passord", resultat);
    }

    @Test
    public void sjekkLoggInnFeilPersonnummerRegex() {
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("1234", "Hehehehe");

        // assert
        assertEquals("Feil i personnummer", resultat);
    }

    @Test
    public void sjekkLoggInnFeil() {
        // arrange
        when(repository.sjekkLoggInn(anyString(), anyString())).thenReturn("Feil i personnummer eller passord");

        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.sjekkLoggInn("12341234123", "Hehehehehe");

        // assert
        assertEquals("Feil i personnummer eller passord", resultat);
    }

    @Test
    public void loggUtTest() {
        session.setAttribute("Innlogget", null);

        // act
        sikkerhetsController.loggUt();

        // assert
        assertNull(session.getAttribute("Innlogget"));
    }

    @Test
    public void loggInnAdminOK() {
        session.setAttribute("Innlogget", "Admin");

        // act
        String resultat = sikkerhetsController.loggInnAdmin("Admin", "Admin");

        // assert
        assertEquals("Logget inn", resultat);
    }

    @Test
    public void loggInnAdminFeil() {
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.loggInnAdmin("Admin", "heihei");

        // assert
        assertEquals("Ikke logget inn", resultat);
    }

    @Test
    public void loggetInnOK() {
        // assert
        session.setAttribute("Innlogget", "12345678901");

        // act
        String resultat = sikkerhetsController.loggetInn();

        // arrange
        assertEquals(session.getAttribute("Innlogget"), resultat);
    }

    @Test
    public void loggetInnFeil() {
        // assert
        session.setAttribute("Innlogget", null);

        // act
        String resultat = sikkerhetsController.loggetInn();

        // arrange
        assertNull(resultat);
    }
}
