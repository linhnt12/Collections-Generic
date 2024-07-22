import java.io.Serializable;

public class Tuyen implements Serializable {
    private static int cnt = 100;
    private int maTuyen, khoangCach, soDiemDung;

    public Tuyen() {
    }

    public Tuyen(int khoangCach, int soDiemDung) {
        this.maTuyen = cnt++;
        this.khoangCach = khoangCach;
        this.soDiemDung = soDiemDung;
    }

    public static int getCnt() {
        return cnt;
    }

    public static void setCnt(int cnt) {
        Tuyen.cnt = cnt;
    }

    public int getMaTuyen() {
        return maTuyen;
    }

    public void setMaTuyen(int maTuyen) {
        this.maTuyen = maTuyen;
    }

    public int getKhoangCach() {
        return khoangCach;
    }

    public void setKhoangCach(int khoangCach) {
        this.khoangCach = khoangCach;
    }

    public int getSoDiemDung() {
        return soDiemDung;
    }

    public void setSoDiemDung(int soDiemDung) {
        this.soDiemDung = soDiemDung;
    }

    @Override
    public String toString() {
        return maTuyen + "\t" + khoangCach + "\t" + soDiemDung;
    }
}
