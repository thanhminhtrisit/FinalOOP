package pojo;

import method.TinhLuong;

public class GiamDoc extends NhanVien implements TinhLuong {
	private double coPhan;
	private double luong = (double)300;

	public GiamDoc() {
		super();
	}

	public GiamDoc(String maso, String hoTen, String sdt, int soNgayLamViec, double coPhan) {
		super();
		this.coPhan = coPhan;
		super.setMaso(maso);
		super.setHoTen(hoTen);
		super.setSdt(sdt);
		super.setSoNgayLamViec(soNgayLamViec);
		super.setLuong(this.luong);
	}

	public double getCoPhan() {
		return coPhan;
	}

	public void setCoPhan(double coPhan) {
		this.coPhan = coPhan;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	@Override
	public double tinhLuong(double luong) {
		// TODO Auto-generated method stub
		return this.luong * super.getSoNgayLamViec();
	} 

}
