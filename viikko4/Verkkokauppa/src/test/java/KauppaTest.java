
import ohtu.verkkokauppa.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class KauppaTest {
    Pankki pankki;
    Viitegeneraattori viitegen;
    Varasto varasto;
    Ostoskori ostoskori;
    
    @Before
    public void setUp(){
        pankki = mock(Pankki.class);
        viitegen = mock(Viitegeneraattori.class);
        varasto = mock(Varasto.class);
        ostoskori = mock(Ostoskori.class);
    }
    
    @Test
    public void ostoksenPaaytyttyaPankinMetodiaTilisiirtoKutsutaan() {
        // määritellään että viitegeneraattori palauttaa viitten 42
        when(viitegen.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viitegen);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(anyString(), anyInt(), anyString(), anyString(),anyInt());   
        // toistaiseksi ei välitetty kutsussa käytetyistä parametreista
    }    
    
    @Test
    public void ostoksenTiedotOikein() {
        when(viitegen.uusi()).thenReturn(42);

        // määritellään että tuote numero 1 on maito jonka hinta on 5 ja saldo 10
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));

        // sitten testattava kauppa 
        Kauppa k = new Kauppa(varasto, pankki, viitegen);              

        // tehdään ostokset
        k.aloitaAsiointi();
        k.lisaaKoriin(1);     // ostetaan tuotetta numero 1 eli maitoa
        k.tilimaksu("pekka", "12345");

        // sitten suoritetaan varmistus, että pankin metodia tilisiirto on kutsuttu
        verify(pankki).tilisiirto(eq("pekka"), eq(42), eq("12345"), eq("33333-44455"),eq(5));   
        //(String nimi, int viitenumero, String tililta, String tilille, int summa)
    }
    
    @Test
    public void kahdenEriTuotteenOstaminenOnnistuu() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(5);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));
        
        Kauppa k = new Kauppa(varasto, pankki, viitegen);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(0), eq("12345"), eq("33333-44455"),eq(8));  
    }
    
    @Test
    public void kahdenSamanTuotteenOstaminenOnnistuu() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        
        Kauppa k = new Kauppa(varasto, pankki, viitegen);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(1);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(0), eq("12345"), eq("33333-44455"),eq(10));  
    }
    
    @Test
    public void kahdenEriTuotteenOstaminenToinenLoppu() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        when(varasto.saldo(2)).thenReturn(0);
        when(varasto.haeTuote(2)).thenReturn(new Tuote(2, "leipa", 3));
        
        Kauppa k = new Kauppa(varasto, pankki, viitegen);              
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.lisaaKoriin(2);
        k.tilimaksu("pekka", "12345");
        
        verify(pankki).tilisiirto(eq("pekka"), eq(0), eq("12345"), eq("33333-44455"),eq(5));  
    }
    
    @Test
    public void uusiOstosNollaaVanhan() {
        Kauppa k = new Kauppa(varasto, pankki, viitegen);
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));
        k.aloitaAsiointi();
        k.lisaaKoriin(1);         
        
        k.aloitaAsiointi();
        assertEquals(ostoskori.hinta(), 0);
    }
    
    @Test
    public void uusiViiteJokaTapahtumalle(){
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));        
        Kauppa k = new Kauppa(varasto, pankki, viitegen);
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.tilimaksu("pekka", "12345");
        
        verify(viitegen).uusi();
    }
    
    @Test
    public void poistoKoristaOnnistuu() {
        when(varasto.saldo(1)).thenReturn(10); 
        when(varasto.haeTuote(1)).thenReturn(new Tuote(1, "maito", 5));        
        Kauppa k = new Kauppa(varasto, pankki, viitegen);
        k.aloitaAsiointi();
        k.lisaaKoriin(1); 
        k.poistaKorista(1);
        
        assertEquals(ostoskori.hinta(), 0);
    }
}
