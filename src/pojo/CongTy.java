package pojo;

import java.util.ArrayList;
import java.util.List;

public class CongTy {
	private String tenCT;
	private String maSoThue;
	private double doanhThuThang;
	private List<NhanVien> listNhanVien = new ArrayList<NhanVien>();

	public CongTy() {
		super();
	}

	public CongTy(String tenCT, String maSoThue, double doanhThuThang, List<NhanVien> listNhanVien) {
		super();
		this.tenCT = tenCT;
		this.maSoThue = maSoThue;
		this.doanhThuThang = doanhThuThang;
		this.listNhanVien = listNhanVien;
	}

	public String getTenCT() {
		return tenCT;
	}

	public void setTenCT(String tenCT) {
		this.tenCT = tenCT;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public double getDoanhThuThang() {
		return doanhThuThang;
	}

	public void setDoanhThuThang(double doanhThuThang) {
		this.doanhThuThang = doanhThuThang;
	}

	public List<NhanVien> getListNhanVien() {
		return listNhanVien;
	}

	public void setListNhanVien(List<NhanVien> listNhanVien) {
		this.listNhanVien = listNhanVien;
	}

}
