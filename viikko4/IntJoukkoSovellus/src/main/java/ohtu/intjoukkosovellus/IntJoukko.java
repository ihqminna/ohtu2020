
package ohtu.intjoukkosovellus;

public class IntJoukko {

    public final static int KAPASITEETTI = 5, // aloitustalukon koko
                            OLETUSKASVATUS = 5;  // luotava uusi taulukko on 
    // näin paljon isompi kuin vanha
    private int[] taulukko;      // Joukon luvut säilytetään taulukon alkupäässä. 
    private int alkioidenLkm;    // Tyhjässä joukossa alkioiden_määrä on nolla. 

    public IntJoukko() {
        taulukko = new int[KAPASITEETTI];
        alkioidenLkm = 0;
    }

    public IntJoukko(int kapasiteetti) {
        if (kapasiteetti > 0) {
            taulukko = new int[kapasiteetti];
        }
    }
    
    public IntJoukko(int kapasiteetti, int kasvatuskoko) {
        if (kapasiteetti < 0 || kasvatuskoko < 0) {
            throw new IndexOutOfBoundsException("Syötteen tulee olla positiivinen");
        }
        taulukko = new int[kapasiteetti];
        for (int i = 0; i < taulukko.length; i++) {
            taulukko[i] = 0;
        }
    }

    public boolean lisaa(int luku) {
        if (!kuuluu(luku)) {
            taulukko[alkioidenLkm] = luku;
            alkioidenLkm++;
            if (alkioidenLkm % taulukko.length == 0) {
                int[] taulukkoVanha = taulukko;
                taulukko = new int[alkioidenLkm + OLETUSKASVATUS];
                kopioiTaulukko(taulukkoVanha, taulukko);
            }
            return true;
        }
        return false;
    }

    public boolean kuuluu(int luku) {
        for (int i = 0; i < alkioidenLkm; i++) {
            if (luku == taulukko[i]) {
                return true;
            }
        }
        return false;
    }

    public boolean poista(int luku) {
        boolean poistettu = false;
        int indeksi = 0;
        for (int i = 0; i < alkioidenLkm; i++) {
            if (taulukko[i] == luku) {
                indeksi = i;
                poistettu = true;
                break;
            }
        }
        if (poistettu) {
           for (int i = indeksi; i < alkioidenLkm ; i++){
               taulukko[i] = taulukko[i + 1];
           }
           alkioidenLkm--;
        }
        return poistettu;
    }

    private void kopioiTaulukko(int[] vanha, int[] uusi) {
        for (int i = 0; i < vanha.length; i++) {
            uusi[i] = vanha[i];
        }

    }

    public int mahtavuus() {
        return alkioidenLkm;
    }


    @Override
    public String toString() {
        if (alkioidenLkm == 0){
            return "{}";
        } else if (alkioidenLkm == 1){
            return "{" + taulukko[0] + "}";
        }
        String palautus = "{";
        for (int i = 0; i < (alkioidenLkm - 1); i++) {
            palautus += taulukko[i];
            palautus += ", ";
        }
        palautus += taulukko[alkioidenLkm - 1];
        palautus += "}";
        return palautus;
    }

    public int[] toIntArray() {
        int[] taysiTaulukko = new int[alkioidenLkm];
        for (int i = 0; i < taysiTaulukko.length; i++) {
            taysiTaulukko[i] = taulukko[i];
        }
        return taysiTaulukko;
    }
   

    public static IntJoukko yhdiste(IntJoukko a, IntJoukko b) {
        IntJoukko yhdisteTaulukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            yhdisteTaulukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            yhdisteTaulukko.lisaa(bTaulu[i]);
        }
        return yhdisteTaulukko;
    }

    public static IntJoukko leikkaus(IntJoukko a, IntJoukko b) {
        IntJoukko leikkausTaulukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            for (int j = 0; j < bTaulu.length; j++) {
                if (aTaulu[i] == bTaulu[j]) {
                    leikkausTaulukko.lisaa(bTaulu[j]);
                }
            }
        }
        return leikkausTaulukko;

    }
    
    public static IntJoukko erotus ( IntJoukko a, IntJoukko b) {
        IntJoukko erotusTaulukko = new IntJoukko();
        int[] aTaulu = a.toIntArray();
        int[] bTaulu = b.toIntArray();
        for (int i = 0; i < aTaulu.length; i++) {
            erotusTaulukko.lisaa(aTaulu[i]);
        }
        for (int i = 0; i < bTaulu.length; i++) {
            erotusTaulukko.poista(bTaulu[i]);
        }
 
        return erotusTaulukko;
    }
        
}
