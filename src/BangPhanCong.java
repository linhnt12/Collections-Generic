import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BangPhanCong implements Serializable {
    private LaiXe laiXe;
    private Map<Tuyen, Integer> dsLuot;

    public BangPhanCong() {
    }

    public BangPhanCong(LaiXe laiXe) {
        this.laiXe = laiXe;
        this.dsLuot = new HashMap<>();
    }

    public LaiXe getLaiXe() {
        return laiXe;
    }

    public void setLaiXe(LaiXe laiXe) {
        this.laiXe = laiXe;
    }

    public boolean phanCong (Tuyen tuyenPC, int soLuot) {
        if (tongSoLuot() + soLuot > 15) return false;
        if (dsLuot.containsKey(tuyenPC)) {
            int luot = dsLuot.get(tuyenPC);
            dsLuot.put(tuyenPC, luot + soLuot);
        } else {
            dsLuot.put(tuyenPC, soLuot);
        }
        return true;
    }

    public int tongSoLuot() {
        int cnt = 0;
        for (int luot : dsLuot.values()) cnt += luot;
        return cnt;
    }

    public int tongKhoangCach() {
        int sum = 0;
        for (Map.Entry<Tuyen, Integer> entry : dsLuot.entrySet()) {
            sum += entry.getValue() * entry.getKey().getKhoangCach();
        }
        return sum;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("Danh sách phân công của lái xe " + this.laiXe.getHoTen() + "\n");
        for (Map.Entry<Tuyen, Integer> entry : dsLuot.entrySet()) {
            sb.append("Mã tuyến: " + entry.getKey().getMaTuyen() + "\tSố lượt: " + entry.getValue() + "\n");
        }
        return sb.toString();
    }
}
