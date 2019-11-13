package hu.cehessteg.vizeromu;

public class Matek {
    //if esik akkor percenként 50% esély rá hogy eláll következő checknél, ha nem akkor 1-2% esély hogy elkezd esni. Eső = + bemenő víz!
    //kell időt mérni
    //kell pénz kalkulálni (valahogy)
    //szélsőértékszámtás (min és max mert game over)
    //Vízzel való számítások(bemenő, kimenő, random események)
    //gátak be ki kapcsolása és ezekhez lekérdezés
    //
    //Bónusz ötletek miután már a minimum megvan:
    //Atomreaktor a háttérbe és hozzá easter egg
    //túristáka gátok
    //több random esemény
    //víznyomás kalkuláció (ez-to-hard math)
    //karbantartás(lezárás, javítási költség)
    //power upok (nagyobb eséllyel eső pl vagy + víz időnként)
    //nehézségi szintek
    //árvízveszély a városra nézve
    //szárazság calc

    float vizmennyiseg = 1000000; // 1misivel kezdünk;
    int minviz = 1000; //gameover ha kevesebb
    int maxviz = 1100000; //gameover ha nagyobb
    int beviz = 100; //befolyó víz
    int kiviz = 40; //azért ne legyen pont osztható a bevizzel(annyira azért ne legyen könnyű)
    int ora = 8;
    int perc = 0;
    boolean eso = false;
    boolean volteso = false;
    Kiomlonyilas[] nyilasok = new Kiomlonyilas[5];
    int openek = 0; // mennyi van nyitva

    public Matek() {
        for (int i = 0; i < 5; i++) {
            Kiomlonyilas res = new Kiomlonyilas();
            nyilasok[i] =  res;
        }
    }

    void opencounter(){
        int a = 0;
        for (Kiomlonyilas b : nyilasok) {
            if(b.isOpen) a++;
        }
        openek=a;
    }

    public void main(String[] args) {
        if (eso) {
            vizmennyiseg += 50;
            if(!volteso){} //animation trigger
            else{volteso = true;}
        }else{}

        vizmennyiseg += beviz;
        vizmennyiseg -= kiviz * openek;
    }
}
