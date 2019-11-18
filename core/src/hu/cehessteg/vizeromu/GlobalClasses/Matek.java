package hu.cehessteg.vizeromu.GlobalClasses;

import java.util.Random;

public class Matek {
    //if esik akkor percenként 50% esély rá hogy eláll következő checknél, ha nem akkor 1-2% esély hogy elkezd esni. Eső = + bemenő víz! készen
    //kell időt mérni készen
    //szélsőértékszámtás (min és max mert game over) készen, de lehet hogy finomítani kell
    //
    //kell pénz kalkulálni (valahogy)
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
    int sec = 0;
    boolean eso = false;
    boolean volteso = false;
    boolean gameover = false;
    Kiomlonyilas[] nyilasok = new Kiomlonyilas[5];
    int openek = 0; // mennyi van nyitva

    Random rnd = new Random();

    public Matek() {
        for (int i = 0; i < 5; i++) {
            Kiomlonyilas res = new Kiomlonyilas();
            res.setOpen(true);
            nyilasok[i] = res;
        }
    }

    void opencounter() {
        int a = 0;
        for (Kiomlonyilas b : nyilasok) {
            if (b.isOpen) a++;
        }
        openek = a;
    }

    public void step() {
        if (eso) {
            vizmennyiseg += 50;
            if (!volteso) { volteso = true; } //animation trigger
            else {
                if (rnd.nextInt(2) == 1) { eso = false; }}} //eso elmegy 50% eséllyel.
        else {
            if (sec == 0 && rnd.nextInt(100) <= 10) { eso = false; } //eso megered 10% eséllyel.
        }
        opencounter();
        vizmennyiseg += beviz;
        vizmennyiseg -= kiviz * openek;
        //ido
        sec++;
        if (sec == 60) {
            sec = 0;
            perc++;
        }
        if (perc == 60) {
            perc = 0;
            ora++;
        }
        if (ora == 24) {
            ora = 0;
        }

        //gameover trigger
        if (vizmennyiseg <= minviz || vizmennyiseg >= maxviz) { gameover = true; }//gameover trigger
    }

    public boolean isVolteso() {
        return volteso;
    }

    public float getVizmennyiseg() {
        return vizmennyiseg;
    }

    public int getMaxviz() {
        return maxviz;
    }

    public Kiomlonyilas[] getNyilasok() {
        return nyilasok;
    }

    public boolean isGameover() {
        return gameover;
    }

    public String getHour()
    {
        String s = "";

        if (ora < 10) s += "0" + ora + ":";
        else s += ora + ":";

        if (perc < 10) s += "0" + perc + ":";
        else s += perc + ":";

        if (sec < 10) s += "0" + sec;
        else s += sec;

        return s;
    }
}

