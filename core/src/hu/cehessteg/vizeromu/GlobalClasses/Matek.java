package hu.cehessteg.vizeromu.GlobalClasses;

import java.util.Random;

import hu.cehessteg.vizeromu.Stage.GameStage;
import hu.cehessteg.vizeromu.Vizeromu;

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

    public static int coins = Vizeromu.gameSave.getInteger("coins");

    float vizmennyiseg = 800000; // 800 ezerrel kezdünk;
    float patakVizmennyiseg = 0;
    int minviz = 1000; //gameover ha kevesebb
    int maxviz = 1100000; //gameover ha nagyobb
    int beviz = 36; //befolyó víz
    int kiviz = 20; //azért ne legyen pont osztható a bevizzel(annyira azért ne legyen könnyű)
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
            nyilasok[i].setOpen(Vizeromu.gameSave.getBoolean("csoOpen" + (i+1)));
        }
        nyilasokSetSavedLevels();
        time = 115200;//Másnap reggel 8 óra, hogy ne egyből essen az eső
    }

    void nyilasokSetSavedLevels()
    {
        nyilasok[0].lvl = Vizeromu.gameSave.getInteger("csoLevel1");
        nyilasok[1].lvl = Vizeromu.gameSave.getInteger("csoLevel2");
        nyilasok[2].lvl = Vizeromu.gameSave.getInteger("csoLevel3");
        nyilasok[3].lvl = Vizeromu.gameSave.getInteger("csoLevel4");
        nyilasok[4].lvl = Vizeromu.gameSave.getInteger("csoLevel5");
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

    float maxOsszesKimeno(){
        float sum = 0;
        for (int i = 0; i < getNyilasok().length; i++) {
            sum += getNyilasok()[i].lvl * kiviz;
        }
        return sum;
    }

    float aramPercentCsokk = 0.3f;

    public float aramPercent()
    {
        if((vizmennyiseg/maxviz) < 0.3) {
            if(aramPercentCsokk > 0.01) aramPercentCsokk-=0.01;
            else aramPercentCsokk = 0;
            return (kimenoszamitas() / maxOsszesKimeno()) * (aramPercentCsokk);
        }
        return (kimenoszamitas()/maxOsszesKimeno()) * (0.3f + (vizmennyiseg/maxviz));
    }

    public void step(float delta, int simulationSpeed) {
        if (getRain() > 0.05) vizmennyiseg += maxOsszesKimeno()*0.7-beviz; //Annyi víz esik be az esővel, mint amennyi az összes csapon kitud menni - az alapból befolyó vízmennyiség
        opencounter();
        beviz = (int)((maxOsszesKimeno()*0.075) * (simulationSpeed/60.0f));//60 az alapértelmezett sebesség
        vizmennyiseg += beviz;
        vizmennyiseg -= kimenoszamitas() * (simulationSpeed/60.0f);
        kimentviz += kimenoszamitas() * (simulationSpeed/60.0f);
        if(time > coinTime+(35*60)) {
            coinTime = time;
            termeltwatt += (kimentviz / 48)*aramPercent();
            kimentviz = 0;
            coins += termeltwatt / 6;
            termeltwatt = 0;
        }
        if(patakVizmennyiseg < 225000)patakVizmennyiseg += kimenoszamitas() * (simulationSpeed/60.0f);
        if(patakVizmennyiseg >= beviz) patakVizmennyiseg -= beviz;//Mondjuk ami patakból kifolyik víz, azt vezetjük vissza a gáthoz
        time += delta;
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

