package hu.cehessteg.vizeromu.GlobalClasses;

import java.util.Random;

public class Matek {
    //if esik akkor percenként 50% esély rá hogy eláll következő checknél, ha nem akkor 1-2% esély hogy elkezd esni. Eső = + bemenő víz! készen
    //kell időt mérni készen
    //szélsőértékszámtás (min és max mert game over) készen, de lehet hogy finomítani kell
    //
    //kell pénz kalkulálni (valahogy)
    //Vízzel való számítások(bemenő, kimenő, random események) félkész
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

    public static int coins = 0;

    float vizmennyiseg = 1000000; // 1misivel kezdünk;
    float patakVizmennyiseg = 0;
    int minviz = 1000; //gameover ha kevesebb
    int maxviz = 1100000; //gameover ha nagyobb
    int beviz = 18; //befolyó víz
    int kiviz = 10; //azért ne legyen pont osztható a bevizzel(annyira azért ne legyen könnyű)
    float time;
    float kimentviz = 0;
    float termeltwatt = 0;


    boolean eso = false;
    boolean volteso = false;
    boolean gameover = false;
    Kiomlonyilas[] nyilasok = new Kiomlonyilas[5];
    int openek = 0; // mennyi van nyitva

    Random rnd = new Random();

    public Matek() {
        for (int i = 0; i < 5; i++) {
            Kiomlonyilas res = new Kiomlonyilas();
            nyilasok[i] = res;
        }
        time = 32000;
    }

    float idonelertek(float kerttime){
        //y3 = (y2-y1) / (x2-x1) * x3
        float x1 = 1;
        float x2 = 20;
        float y1 = 0;
        float y2 = 1;
        float dif = y2-y1;
        float timedif = x1-x2;
        float asd = dif / timedif * kerttime;
        return asd;
    }

    public void Opengate(int wer){
        getNyilasok()[wer].setOpen(true);
    }

    public void Closegate(int wer){
            getNyilasok()[wer].setOpen(false);
        }


    void opencounter() {
        int a = 0;
        for (Kiomlonyilas b : nyilasok) {
            if (b.isOpen) a++;
        }
        openek = a;
    }

    float coinTime = 0;

    float kimenoszamitas(){
        float sum = 0;
        for (int i = 0; i < getNyilasok().length; i++) {
            if(getNyilasok()[i].isOpen) sum += getNyilasok()[i].lvl * kiviz;
        }
        return sum;
    }

    public void step(float delta) {
        if (eso) {
            vizmennyiseg += 50;
            if (!volteso) { volteso = true; } //animation trigger
            else {
                if (rnd.nextInt(2) == 1) { eso = false; }}} //eso elmegy 50% eséllyel.
        else {
            if (getS()== 0 && rnd.nextInt(100) <= 10) { eso = false; } //eso megered 10% eséllyel.
        }
        opencounter();
        vizmennyiseg += beviz;
        vizmennyiseg -= kimenoszamitas();
        kimentviz += kimenoszamitas();
        if(time > coinTime+(36*60)) {
            coins += openek;
            coinTime = time;
            termeltwatt += kimentviz/48;
            kimentviz = 0;
            coins += termeltwatt/3;
        }//5 másodpercenként annyi coint kap, ahány csap megvan nyitva, csak ideiglenesen van bent, hogy működjön a pénzszámláló
        if(patakVizmennyiseg < 225000)patakVizmennyiseg += kiviz * openek;
        if(patakVizmennyiseg >= beviz) patakVizmennyiseg -= beviz;//Mondjuk ami patakból kifolyik víz, azt vezetjük vissza a gáthoz
        time += delta;
        //gameover trigger
        if (vizmennyiseg <= minviz || vizmennyiseg >= maxviz) { gameover = true; }//gameover trigger
    }

    public void buysometing(int coins) {
        Matek.coins -= coins;
    }

    public void jövedelem(int coins) {
        Matek.coins += coins;
    }

    public void lvlupcso(int hanyadikat){
        getNyilasok()[hanyadikat].lvlup();
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

    public String getTimeToString()
    {
        return "" + getH() + ":" + getM() + ":" + getS() + "." + getMs();
    }

    public float getTime()
    {
        return time;
    }

    /**
     * TODO: Az eső mértékét kell visszaadni 0f és 1f között.
     * @return
     */
    public float getRain(){
        //return 0f;
        return (float)(Math.sin(time / 30000) < 0 ? 0 : Math.sin(time / 30000));
    }

    public int getS(){
        return ((int)time) % 60;
    }

    public int getH(){
        return (int)time / 3600;
    }

    public int getM(){
        return ((int)time / 60) % 60;
    }

    public int getMs(){
        return ((int)(time * 1000)) % 1000;
    }

    public boolean isEso() {
        return eso;
    }

    public float getPatakVizmennyiseg() {
        return patakVizmennyiseg;
    }

    public void addDemoTime(float demoTime)
    {
        this.time += demoTime;
    }
}

