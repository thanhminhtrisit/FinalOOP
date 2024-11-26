package pojo;

import java.util.ArrayList;
import java.util.List;

import method.TinhLuong;

public class TruongPhong extends NhanVien implements TinhLuong {
	private List<NhanVien> listNhanVien = new ArrayList<NhanVien>();
	private double luong = 200;

	public TruongPhong() {
		super();
	}

	public TruongPhong(String maso, String hoTen, String sdt, int soNgayLamViec, List<NhanVien> listNhanVien) {
		super();
		this.listNhanVien = listNhanVien;
		super.setMaso(maso);
		super.setHoTen(hoTen);
		super.setSdt(sdt);
		super.setSoNgayLamViec(soNgayLamViec);
		super.setLuong(this.luong);
	}

	public List<NhanVien> getListNhanVien() {
		return listNhanVien;
	}

	public void setListNhanVien(List<NhanVien> listNhanVien) {
		this.listNhanVien = listNhanVien;
	}

	public double getLuong() {
		return this.tinhLuong(luong);
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	@Override
	public double tinhLuong(double luong) {
		// TODO Auto-generated method stub
		return this.luong * super.getSoNgayLamViec() + 100 * listNhanVien.size();
	}

}
