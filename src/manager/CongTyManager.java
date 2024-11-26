package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojo.CongTy;
import pojo.GiamDoc;
import pojo.NhanVien;
import pojo.NhanVienThuong;
import pojo.TruongPhong;
import validation.Validator;

public class CongTyManager {
	private CongTy congTy;
	private GiamDocManager giamDocManager;
	private NhanVienThuongManager nhanVienThuongManager;
	private TruongPhongManager truongPhongManager;
	private List<NhanVien> listAllNhanVien = new ArrayList<NhanVien>();

	public CongTyManager() {
		super();
	}

	public CongTyManager(GiamDocManager giamDocManager, TruongPhongManager truongPhongManager,
			NhanVienThuongManager nhanVienThuongManager) {
		this.congTy = new CongTy();
		this.giamDocManager = giamDocManager;
		this.nhanVienThuongManager = nhanVienThuongManager;
		this.truongPhongManager = truongPhongManager;
	}

	public CongTy getCongTy() {
		return congTy;
	}

	public void setCongTy(CongTy congTy) {
		this.congTy = congTy;
	}

	public void createNewCompany() {
		Scanner scanner = new Scanner(System.in);
		String tenCT;
		String maSoThue;
		double doanhThuThang;

		System.out.println("=== Create New Company ===");

		// Input and validate tenCT
		while (true) {
			System.out.print("Enter company name (max 20 characters): ");
			tenCT = scanner.nextLine();
			if (Validator.validateTenCT(tenCT)) {
				break;
			} else {
				System.out.println("Invalid company name. It must not exceed 20 characters.");
			}
		}

		// Input and validate maSoThue
		while (true) {
			System.out.print("Enter tax code (10 or 13 digits): ");
			maSoThue = scanner.nextLine();
			if (Validator.validateMaSoThue(maSoThue)) {
				break;
			} else {
				System.out.println("Invalid tax code. It must be 10 or 13 digits.");
			}
		}

		// Input and validate doanhThuThang
		while (true) {
			System.out.print("Enter monthly revenue: ");
			try {
				doanhThuThang = Double.parseDouble(scanner.nextLine());
				if (doanhThuThang >= 0) {
					break;
				} else {
					System.out.println("Monthly revenue must be non-negative. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		// Set the new company details
		congTy = new CongTy(tenCT, maSoThue, doanhThuThang, this.listAllNhanVien);
		System.out.println("New company created successfully!");
	}

	public void UpdateCongTy() {
		Scanner scanner = new Scanner(System.in);

		while (true) {
			System.out.print("Enter company name (max 20 characters): ");
			String tenCT = scanner.nextLine();
			if (Validator.validateTenCT(tenCT)) {
				congTy.setTenCT(tenCT);
				break;
			} else {
				System.out.println("Invalid company name. Please try again.");
			}
		}

		while (true) {
			System.out.print("Enter tax code (10 or 13 digits): ");
			String maSoThue = scanner.nextLine();
			if (Validator.validateMaSoThue(maSoThue)) {
				congTy.setMaSoThue(maSoThue);
				break;
			} else {
				System.out.println("Invalid tax code. Please try again.");
			}
		}

		while (true) {
			System.out.print("Enter monthly revenue: ");
			try {
				double doanhThuThang = Double.parseDouble(scanner.nextLine());
				if (doanhThuThang >= 0) {
					congTy.setDoanhThuThang(doanhThuThang);
					break;
				} else {
					System.out.println("Revenue must be non-negative. Please try again.");
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid input. Please enter a valid number.");
			}
		}

		System.out.println("Company information updated successfully.");
	}

	public void displayCongTy() {
		if (congTy != null) {
			System.out.println("Company Name: " + congTy.getTenCT());
			System.out.println("Tax Code: " + congTy.getMaSoThue());
			System.out.println("Monthly Revenue: " + congTy.getDoanhThuThang());
		} else {
			System.out.println("No company information available.");
		}
	}

	// CRUD for GiamDoc
	public void themGiamDoc() {
		giamDocManager.createGiamDoc(this.listAllNhanVien);
		;
	}

	public void xoaGiamDoc(String maso) {
		giamDocManager.deleteGiamDoc(this.listAllNhanVien, maso);
		;
	}

	public void updateGiamDoc(String maso) {
		giamDocManager.updateGiamDoc(this.listAllNhanVien, maso);
	}

	public void displayAllGiamDoc() {
		giamDocManager.displayAllGiamDoc();
	}

	// CRUD for NhanVienThuong
	public void themNhanVienThuong() {
		nhanVienThuongManager.createNhanVienThuong(this.listAllNhanVien);
	}

	public void xoaNhanVienThuong(String maso) {
		nhanVienThuongManager.deleteNhanVienThuong(this.listAllNhanVien, maso);
		;
	}

	public void updateNhanVienThuong(String maso) {
		nhanVienThuongManager.updateNhanVienThuong(this.listAllNhanVien, maso);
	}

	public void displayAllNhanVienThuong() {
		nhanVienThuongManager.displayAllNhanVienThuong();
	}

	// CRUD for TruongPhong
	public void themTruongPhong() {
		truongPhongManager.createTruongPhong(this.listAllNhanVien);
		;
	}

	public void xoaTruongPhong(String maso) {
		// Find the TruongPhong by maso
		TruongPhong truongPhongToDelete = null;
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof TruongPhong && nv.getMaso().equalsIgnoreCase(maso)) {
				truongPhongToDelete = (TruongPhong) nv;
				break;
			}
		}

		if (truongPhongToDelete == null) {
			System.out.println("No TruongPhong found with maso: " + maso);
			return;
		}

		// Remove TruongPhong from list
		listAllNhanVien.remove(truongPhongToDelete);

		// Set TruongPhongQuanLi to null for all NhanVienThuong managed by the deleted
		// TruongPhong
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof NhanVienThuong) {
				NhanVienThuong nhanVienThuong = (NhanVienThuong) nv;
				if (nhanVienThuong.getTruongPhongQuanLi() != null
						&& nhanVienThuong.getTruongPhongQuanLi().getMaso().equalsIgnoreCase(maso)) {
					nhanVienThuong.setTruongPhongQuanLi(null);
					System.out.println("Unassigned TruongPhong from NhanVienThuong: " + nhanVienThuong.getHoTen());
				}
			}
		}

		System.out.println("Deleted TruongPhong with maso: " + maso);
	}

	public void updateTruongPhong(String maso) {
		truongPhongManager.updateTruongPhong(this.listAllNhanVien, maso);
	}

	public void displayAllTruongPhong() {
		truongPhongManager.displayAllTruongPhong();
	}

	public void addNhanVienThuong() {
		System.out.println("Adding a new NhanVienThuong...");
		nhanVienThuongManager.themNhanVienThuong();
	}

	public void addTruongPhong() {
		System.out.println("Adding a new TruongPhong...");
		truongPhongManager.themTruongPhong();
	}

	public void addGiamDoc() {
		System.out.println("Adding a new GiamDoc...");
		giamDocManager.themGiamDoc();
	}

	public void updateListAllNhanVien() {
		listAllNhanVien.clear(); // Clear the list to avoid duplication

		// Add all GiamDoc
		if (giamDocManager != null && giamDocManager.getListNhanVien() != null) {
			listAllNhanVien.addAll(giamDocManager.getListNhanVien());
		}
		// Add all TruongPhong
		if (truongPhongManager != null && truongPhongManager.getListTruongPhong() != null) {
			listAllNhanVien.addAll(truongPhongManager.getListTruongPhong());
		}

		// Add all NhanVienThuong
		if (nhanVienThuongManager != null && nhanVienThuongManager.getListNhanVienThuong() != null) {
			listAllNhanVien.addAll(nhanVienThuongManager.getListNhanVienThuong());
		}

		System.out.println("All employees have been updated in listAllNhanVien.");
	}

	public NhanVien findEmployeeByMaso(String maso) {
		for (NhanVien nv : listAllNhanVien) {
			if (nv.getMaso().equalsIgnoreCase(maso)) {
				if (nv instanceof NhanVienThuong) {
					return (NhanVienThuong) nv; 
				} else if (nv instanceof TruongPhong) {
					return (TruongPhong) nv; 
				} else if (nv instanceof GiamDoc) {
					return (GiamDoc) nv; 
				}
			}
		}
		// Return null if no matching employee is found
		System.out.println("No employee found with maso: " + maso);
		return null;
	}

	public void displayAllEmployeesFormatted() {
		updateListAllNhanVien();
		if (listAllNhanVien.isEmpty()) {
			System.out.println("No employees found in the company.");
			return;
		}

		// Table Header
		System.out.printf("%-10s %-35s %-10s %-4s %-10s %-35s\n", "Maso", "HoTen", "SDT", "Days", "Luong", "Manager");

		System.out.println("=".repeat(110));

		// Iterate through all employees
		for (NhanVien nv : listAllNhanVien) {
			String maso = nv.getMaso();
			String hoTen = nv.getHoTen();
			String sdt = nv.getSdt();
			int soNgayLamViec = nv.getSoNgayLamViec();
			double luong = nv.getLuong();
			String manager = "None";

			if (nv instanceof NhanVienThuong) {
				NhanVienThuong nvThuong = (NhanVienThuong) nv;
				if (nvThuong.getTruongPhongQuanLi() != null) {
					manager = nvThuong.getTruongPhongQuanLi().getHoTen();
				}
			} else if (nv instanceof TruongPhong) {
				manager = "Self-Managed";
			} else if (nv instanceof GiamDoc) {
				GiamDoc giamDoc = (GiamDoc) nv;
				manager = "N/A (GiamDoc)";
			}

			// Print formatted row
			System.out.printf("%-10s %-35s %-10s %-4d %-10.2f %-35s\n", maso, hoTen, sdt, soNgayLamViec, luong,
					manager);
		}
	}


	public void assignNhanVienThuongToTruongPhong(String maso) {
		NhanVien nhanVien = findEmployeeByMaso(maso);
		if (nhanVien == null || !(nhanVien instanceof NhanVienThuong)) {
			System.out.println("No NhanVienThuong found with maso: " + maso);
			return;
		}

		NhanVienThuong nhanVienThuong = (NhanVienThuong) nhanVien;

		List<TruongPhong> truongPhongList = new ArrayList<>();
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof TruongPhong) {
				TruongPhong truongPhong = (TruongPhong) nv;
				truongPhongList.add(truongPhong);
				System.out.println("Maso: " + truongPhong.getMaso() + ", HoTen: " + truongPhong.getHoTen());
			}
		}

		if (truongPhongList.isEmpty()) {
			System.out.println("No TruongPhong available for assignment.");
			return;
		}

		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the maso of the TruongPhong to assign this NhanVienThuong: ");
		String selectedMaso = scanner.nextLine();

		TruongPhong selectedTruongPhong = null;
		for (TruongPhong tp : truongPhongList) {
			if (tp.getMaso().equalsIgnoreCase(selectedMaso)) {
				selectedTruongPhong = tp;
				break;
			}
		}

		if (selectedTruongPhong == null) {
			System.out.println("Invalid maso. No TruongPhong found with maso: " + selectedMaso);
			return;
		}

		nhanVienThuong.setTruongPhongQuanLi(selectedTruongPhong);
		selectedTruongPhong.getListNhanVien().add(nhanVienThuong);

		System.out.println("Assigned NhanVienThuong: " + nhanVienThuong.getHoTen() + " to TruongPhong: "
				+ selectedTruongPhong.getHoTen());
	}

	public void calculateLuongTruongPhong() {
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof TruongPhong) {
				TruongPhong truongPhong = (TruongPhong) nv;
				int soNhanVienQuanLy = truongPhong.getListNhanVien().size();
				truongPhong.setLuong(truongPhong.getLuong() + 100 * soNhanVienQuanLy);
			}
		}
		System.out.println("Salaries of TruongPhong calculated successfully.");
	}

	public void displayAllSalaries() {
		for (NhanVien nv : listAllNhanVien) {
			System.out.println("Maso: " + nv.getMaso() + ", HoTen: " + nv.getHoTen() + ", Luong: " + nv.getLuong());
		}
	}

	public void findHighestPaidNhanVienThuong() {
		NhanVienThuong highestPaid = null;
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof NhanVienThuong) {
				if (highestPaid == null || nv.getLuong() > highestPaid.getLuong()) {
					highestPaid = (NhanVienThuong) nv;
				}
			}
		}
		if (highestPaid != null) {
			System.out.println("Highest Paid NhanVienThuong: " + highestPaid.toString());
		}
	}

	public void findTruongPhongWithMostNhanVienThuong() {
		TruongPhong mostManaging = null;
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof TruongPhong) {
				TruongPhong truongPhong = (TruongPhong) nv;
				if (mostManaging == null
						|| truongPhong.getListNhanVien().size() > mostManaging.getListNhanVien().size()) {
					mostManaging = truongPhong;
				}
			}
		}
		if (mostManaging != null) {
			System.out.println("TruongPhong managing the most NhanVienThuong: " + mostManaging.toString());
		}
	}

	public void sortEmployeesByName() {
		listAllNhanVien.sort((nv1, nv2) -> nv1.getHoTen().compareToIgnoreCase(nv2.getHoTen()));
		System.out.println("Employees sorted by name alphabetically.");
	}

	public void sortEmployeesBySalary() {
		listAllNhanVien.sort((nv1, nv2) -> Double.compare(nv2.getLuong(), nv1.getLuong()));
		System.out.println("Employees sorted by salary in descending order.");
	}

	public void findGiamDocWithMostCoPhan() {
		GiamDoc mostCoPhan = null;
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof GiamDoc) {
				GiamDoc giamDoc = (GiamDoc) nv;
				if (mostCoPhan == null || giamDoc.getCoPhan() > mostCoPhan.getCoPhan()) {
					mostCoPhan = giamDoc;
				}
			}
		}
		if (mostCoPhan != null) {
			System.out.println("GiamDoc with the most CoPhan: " + mostCoPhan.getHoTen());
		}
	}

	public void calculateLuongGiamDoc() {
		for (NhanVien nv : this.listAllNhanVien) {
			if (nv instanceof GiamDoc) {
				GiamDoc giamDoc = (GiamDoc) nv;
				giamDoc.setLuong(300);
				double luongGiamDoc = giamDoc.getLuong() * giamDoc.getSoNgayLamViec()
						+ giamDoc.getCoPhan() * this.getCongTy().getDoanhThuThang();
				giamDoc.setLuong(luongGiamDoc);
			}
		}
		System.out.println("Salaries of GiamDoc calculated successfully.");
	}

	public void displayAllGiamDocIncomes() {
		calculateLuongGiamDoc();
		System.out.println("\n=== List of GiamDoc Incomes ===");
		for (NhanVien nv : listAllNhanVien) {
			if (nv instanceof GiamDoc) {
				GiamDoc giamDoc = (GiamDoc) nv;
				System.out.println("Maso: " + giamDoc.getMaso() + ", HoTen: " + giamDoc.getHoTen() + ", CoPhan: "
						+ giamDoc.getCoPhan() + ", Income: " + giamDoc.getLuong());
			}
		}
	}
}
