import java.io.*;
import java.util.*;

public class Main {
    private static Scanner sc = new Scanner(System.in);
    private static List<LaiXe> dsLaiXe = new ArrayList<>();
    private static List<Tuyen> dsTuyenXe = new ArrayList<>();
    private static List<BangPhanCong> dsPhanCong = new ArrayList<>();

    public static void main(String[] args) {
        loadData();
        while (true) {
            System.out.println("Menu");
            System.out.println("1. Nhập lái xe mới.");
            System.out.println("2. Nhập tuyến xe mới.");
            System.out.println("3. Nhập phân công mới.");
            System.out.println("4. Sắp xếp danh sách phân công theo họ tên lái xe.");
            System.out.println("5. Sắp xếp danh sách phân công theo số lượng tuyến đảm nhận trong ngày (giảm dần)");
            System.out.println("6. Lập bảng thống kê tổng khoảng cách chạy xe trong ngày.");
            System.out.println("0. Thoát.");

            int choice = Integer.parseInt(sc.nextLine());
            switch (choice) {
                case 1:
                    nhapLaiXe();
                    inDsLaiXe();
                    break;
                case 2:
                    nhapTuyenXe();
                    inDsTuyenXe();
                    break;
                case 3:
                    nhapPhanCong();
                    inDsPhanCong();
                    break;
                case 4:
                    sapXepTheoTenLaiXe();
                    break;
                case 5:
                    sapXepTheoSoLuongTuyen();
                    break;
                case 6:
                    lapThongKe();
                    break;
                case 0:
                    saveData();
                    System.out.println("Kết thúc!");
                    return;
                default:
                    System.out.println("Lựa chọn không hợp lệ!");
                    break;
            }
        }
    }

    private static void nhapLaiXe() {
        int current = LaiXe.getCnt();
        LaiXe.setCnt(current + dsLaiXe.size());
        System.out.print("Nhập số lượng lái xe: ");
        int slLaiXe = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < slLaiXe; i++) {
            System.out.println("Nhập thông tin lái xe thứ " + (i + 1));
            System.out.print("Họ tên: ");
            String hoTen = sc.nextLine();
            System.out.print("Địa chỉ: ");
            String dchi = sc.nextLine();
            System.out.print("Số điện thoại: ");
            String sdt = sc.nextLine();
            System.out.print("Trình độ: ");
            String trinhDo = sc.nextLine();
            dsLaiXe.add(new LaiXe(hoTen, dchi, sdt, trinhDo));
        }
    }

    private static void inDsLaiXe() {
        System.out.println("Danh sách lái xe");
        for (int i = 0; i < dsLaiXe.size(); i++) {
            System.out.println(dsLaiXe.get(i));
        }
    }

    private static void nhapTuyenXe() {
        int current = Tuyen.getCnt();
        Tuyen.setCnt(current + dsTuyenXe.size());
        System.out.print("Nhập số lượng tuyến xe: ");
        int slTuyen = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < slTuyen; i++) {
            System.out.println("Nhập thông tin tuyến xe thứ " + (i + 1));
            System.out.print("Khoảng cách: ");
            int khoangCach = Integer.parseInt(sc.nextLine());
            System.out.print("Số điểm dừng: ");
            int soDiemDung = Integer.parseInt(sc.nextLine());
            dsTuyenXe.add(new Tuyen(khoangCach, soDiemDung));
        }
    }

    private static void inDsTuyenXe() {
        System.out.println("Danh sách tuyến xe");
        for (int i = 0; i < dsTuyenXe.size(); i++) {
            System.out.println(dsTuyenXe.get(i));
        }
    }

    private static void nhapPhanCong() {
        System.out.print("Nhập mã lái xe: ");
        int maLaiXe = Integer.parseInt(sc.nextLine());
        LaiXe laiXePC = null;
        for (LaiXe laiXe : dsLaiXe) {
            if (laiXe.getMaLX() == maLaiXe) {
                laiXePC = laiXe;
                break;
            }
        }
        if (laiXePC == null) {
            System.out.println("Không tìm thấy lái xe!");
            return;
        }
        BangPhanCong phanCong = null;
        for (BangPhanCong phanCong1 : dsPhanCong) {
            if (phanCong1.getLaiXe() == laiXePC) {
                phanCong = phanCong1;
                break;
            }
        }
        if (phanCong == null) {
            phanCong = new BangPhanCong(laiXePC);
            dsPhanCong.add(phanCong);
        }
        System.out.print("Nhập số lượng tuyến xe muốn phân công: ");
        int slPC = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < slPC; i++) {
            System.out.print("Nhập mã tuyến xe thứ " + (i + 1) + ": ");
            int maTuyen = Integer.parseInt(sc.nextLine());
            Tuyen tuyenPC = null;
            for (Tuyen tuyen : dsTuyenXe) {
                if (tuyen.getMaTuyen() == maTuyen) {
                    tuyenPC = tuyen;
                    break;
                }
            }
            if (tuyenPC == null) {
                System.out.println("Không tìm thấy tuyến xe!");
                continue;
            }
            System.out.print("Nhập số lượt phân công: ");
            int soLuot = Integer.parseInt(sc.nextLine());
            if (!phanCong.phanCong(tuyenPC, soLuot)) {
                System.out.println("Phân công không thành công!");
            } else {
                System.out.println("Phân công thành công!");
            }
        }
    }

    private static void inDsPhanCong() {
        System.out.println("Bảng phân công");
        for (BangPhanCong phanCong : dsPhanCong) {
            System.out.println(phanCong);
        }
    }

    private static void sapXepTheoTenLaiXe() {
        Collections.sort(dsPhanCong, new Comparator<BangPhanCong>() {
            @Override
            public int compare(BangPhanCong o1, BangPhanCong o2) {
                if (o1.getLaiXe().getHoTen().compareToIgnoreCase(o2.getLaiXe().getHoTen()) < 0)
                    return -1;
                else return 1;
            }
        });
        inDsPhanCong();
    }

    private static void sapXepTheoSoLuongTuyen() {
        Collections.sort(dsPhanCong, new Comparator<BangPhanCong>() {
            public int compare (BangPhanCong o1, BangPhanCong o2) {
                if (o1.tongSoLuot() > o2.tongSoLuot()) return -1;
                else return 1;
            }
        });
        inDsPhanCong();
    }

    private static void lapThongKe() {
        for (BangPhanCong phanCong : dsPhanCong) {
            System.out.print("Tổng khoảng cách chạy xe trong ngày của lái xe " + phanCong.getLaiXe().getHoTen() + ": ");
            System.out.println(phanCong.tongKhoangCach());
        }
    }

    private static void loadData() {
        File file = new File("data.txt");
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("data.txt"))) {
                dsLaiXe = (List<LaiXe>) ois.readObject();
                dsTuyenXe = (List<Tuyen>) ois.readObject();
                dsPhanCong = (List<BangPhanCong>) ois.readObject();
                System.out.println("Đọc dữ liệu thành công!");
            } catch (IOException | ClassNotFoundException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void saveData() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("data.txt"))) {
            oos.writeObject(dsLaiXe);
            oos.writeObject(dsTuyenXe);
            oos.writeObject(dsPhanCong);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}