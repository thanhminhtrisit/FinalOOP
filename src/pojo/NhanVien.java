package pojo;

public class NhanVien {
	private String maso;
	private String hoTen;
	private String sdt;
	private int soNgayLamViec;
	private double luong;

	public NhanVien() {
		super();
	}

	public NhanVien(String maso, String hoTen, String sdt, int soNgayLamViec, double luong) {
		super();
		this.maso = maso;
		this.hoTen = hoTen;
		this.sdt = sdt;
		this.soNgayLamViec = soNgayLamViec;
		this.luong = luong;
	}

	public String getMaso() {
		return maso;
	}

	public void setMaso(String maso) {
		this.maso = maso;
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public int getSoNgayLamViec() {
		return soNgayLamViec;
	}

	public void setSoNgayLamViec(int soNgayLamViec) {
		this.soNgayLamViec = soNgayLamViec;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}
}
