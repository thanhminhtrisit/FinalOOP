package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import input.InputNhanVienThuong;
import pojo.NhanVien;
import pojo.NhanVienThuong;
import validation.Validator;

public class NhanVienThuongManager {
	private List<NhanVien> listNhanVienThuong = new ArrayList<NhanVien>();
	private List<NhanVien> listTruongPhong = new ArrayList<NhanVien>();

	public NhanVienThuongManager() {
		super();
	}

	public NhanVienThuongManager(List<NhanVien> listNhanVienThuong, List<NhanVien> listTruongPhong) {
		super();
		this.listNhanVienThuong = listNhanVienThuong;
		this.listTruongPhong = listTruongPhong;
	}

	public List<NhanVien> getListNhanVienThuong() {
		return listNhanVienThuong;
	}

	public List<NhanVien> getListTruongPhong() {
		return listTruongPhong;
	}

	public void themNhanVienThuong() {
		NhanVienThuong nhanVienThuong = InputNhanVienThuong.inputNhanVienThuong(this.listTruongPhong,
				this.listNhanVienThuong);
		if (!this.listNhanVienThuong.contains(nhanVienThuong)) {
			this.listNhanVienThuong.add(nhanVienThuong);
			System.out.println("Added NhanVienThuong successfully!");
		} else {
			System.out.println("Added NhanVienThuong failed! Please try again!");
		}
	}

	public void xoaNhanVienThuong(String maso) {
		if (!this.listNhanVienThuong.isEmpty()) {
			for (NhanVien nhanVien : this.listNhanVienThuong) {
				if (maso.equals(nhanVien.getMaso())) {
					this.listNhanVienThuong.remove(nhanVien);
					System.out.println("Deleted NhanVienThuong with maso: " + maso);
					break;
				} else {
					System.out.println("NhanVienThuong with the specified maso not found!");
					break;
				}
			}
		} else {
			System.out.println("The list of NhanVienThuong is empty!");
		}
	}

	public void updateEmployee(String maso) {
		Scanner scanner = new Scanner(System.in);
		int sizeOfList = this.listNhanVienThuong.size();
		for (int i = 0; i < sizeOfList; i++) {
			if (this.listNhanVienThuong.get(i).getMaso().equals(maso)) {
				NhanVien currentEmployee = this.listNhanVienThuong.get(i);

				System.out.println("Updating employee: " + currentEmployee.getHoTen());
				System.out.println("Press Enter to keep the current value.");

				// Update hoTen
				System.out.print("Enter new hoTen (current: " + currentEmployee.getHoTen() + "): ");
				String newHoTen = scanner.nextLine();
				if (!newHoTen.isBlank() && Validator.validateHoTen(newHoTen)) {
					currentEmployee.setHoTen(newHoTen);
				}

				// Update sdt
				System.out.print("Enter new sdt (current: " + currentEmployee.getSdt() + "): ");
				String newSdtInput = scanner.nextLine();
				if (!newSdtInput.isBlank()) {
					try {
						if (Validator.validateSdt(newSdtInput)) {
							currentEmployee.setSdt(newSdtInput);
						} else {
							System.out.println("Invalid sdt. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for sdt. Keeping the current value.");
					}
				}

				// Update soNgayLamViec
				System.out.print("Enter new soNgayLamViec (current: " + currentEmployee.getSoNgayLamViec() + "): ");
				String newSoNgayLamViecInput = scanner.nextLine();
				if (!newSoNgayLamViecInput.isBlank()) {
					try {
						int newSoNgayLamViec = Integer.parseInt(newSoNgayLamViecInput);
						if (Validator.validateSoNgayLamViec(newSoNgayLamViec)) {
							currentEmployee.setSoNgayLamViec(newSoNgayLamViec);
						} else {
							System.out.println("Invalid soNgayLamViec. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for soNgayLamViec. Keeping the current value.");
					}
				}

				// Update luong
				System.out.print("Enter new luong (current: " + currentEmployee.getLuong() + "): ");
				String newLuongInput = scanner.nextLine();
				if (!newLuongInput.isBlank()) {
					try {
						double newLuong = Double.parseDouble(newLuongInput);
						if (Validator.validateLuong(newLuong)) {
							currentEmployee.setLuong(newLuong);
						} else {
							System.out.println("Invalid luong. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for luong. Keeping the current value.");
					}
				}

				this.listNhanVienThuong.set(i, currentEmployee);
				System.out.println("Employee updated successfully.");
				return;
			}
		}
		System.out.println("Employee not found.");
	}

	public void createNhanVienThuong(List<NhanVien> listAllNhanVien) {
		themNhanVienThuong(); 
		listAllNhanVien.addAll(getListNhanVienThuong());
	}

	public void readAllNhanVienThuong(List<NhanVien> listAllNhanVien) {
		listAllNhanVien.stream().filter(nv -> nv instanceof NhanVienThuong).forEach(nv -> System.out.println(nv));
	}

	public void updateNhanVienThuong(List<NhanVien> listAllNhanVien, String maso) {
		updateEmployee(maso); 
		listAllNhanVien.clear();
		listAllNhanVien.addAll(getListNhanVienThuong());
	}

	public void deleteNhanVienThuong(List<NhanVien> listAllNhanVien, String maso) {
		xoaNhanVienThuong(maso); 
		listAllNhanVien.removeIf(nv -> nv.getMaso().equals(maso) && nv instanceof NhanVienThuong);
	}

	public void displayAllNhanVienThuong() {
		if (this.listNhanVienThuong.isEmpty()) {
			System.out.println("No employees found.");
		} else {
			for (NhanVien employee : this.listNhanVienThuong) {
				System.out.println(employee.getHoTen());
				System.out.println(employee.getMaso());
			}
		}
	}

}
