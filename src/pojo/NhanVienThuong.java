package pojo;

import method.TinhLuong;

public class NhanVienThuong extends NhanVien implements TinhLuong{
	private TruongPhong truongPhongQuanLi;
	private double luong = 100;

	public NhanVienThuong() {
		super();
	}

	public NhanVienThuong(String maso,String hoTen, String sdt, int soNgayLamViec, TruongPhong truongPhongQuanLi) {
		super();
		this.truongPhongQuanLi = truongPhongQuanLi;
		super.setMaso(maso);
		super.setHoTen(hoTen);
		super.setSdt(sdt);
		super.setSoNgayLamViec(soNgayLamViec);
		super.setLuong(this.luong);
	}

	public TruongPhong getTruongPhongQuanLi() {
		return truongPhongQuanLi;
	}

	public void setTruongPhongQuanLi(TruongPhong truongPhongQuanLi) {
		this.truongPhongQuanLi = truongPhongQuanLi;
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
		return super.getSoNgayLamViec()*this.luong;
	}

}
