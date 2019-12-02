package hu.cehessteg.vizeromu.GlobalClasses;

public class Kiomlonyilas {
    public boolean isOpen;
    public int lvl = 1;

    public Kiomlonyilas() {

    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }

    public void lvlup() {
        this.lvl++;
    }
}
