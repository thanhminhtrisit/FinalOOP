package input;

import java.util.List;
import java.util.Scanner;

import pojo.NhanVien;
import pojo.NhanVienThuong;
import pojo.TruongPhong;
import validation.Validator;

public class InputNhanVienThuong {

	public static NhanVienThuong inputNhanVienThuong(List<NhanVien> availableTruongPhongs,
			List<NhanVien> existingEmployees) {
		Scanner scanner = new Scanner(System.in);
		String maso;
		String hoTen = null;
		String sdt;
		int soNgayLamViec;
		double luong = 100; 
		TruongPhong truongPhongQuanLi = null;

		// Input and validate maso
		while (true) {
			System.out.print("Enter maso (must start with 'NV' followed by 4 digits and max 10 characters): ");
			String inputMaso = scanner.nextLine();
			boolean exists = existingEmployees.stream().anyMatch(emp -> emp.getMaso().equals(inputMaso));
			if (Validator.validateMaso(inputMaso) && !exists) {
				maso = inputMaso;
				break;
			}
			if (exists) {
				System.out.println("The maso already exists. Please enter a different maso.");
			} else {
				System.out.println("Invalid maso. Please try again.");
			}
		}

		while (true) {
			System.out.print("Enter hoTen (letters and spaces only, max 35 characters): ");
			hoTen = scanner.nextLine();
			if (Validator.validateHoTen(hoTen)) {
				break;
			}
			System.out.println("Invalid hoTen. Please try again.");
		}

		// Input and validate sdt
		while (true) {
			System.out.print("Enter sdt (10-digit valid Vietnamese phone number): ");
			try {
				sdt = scanner.nextLine();
				if (Validator.validateSdt(sdt)) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid sdt. Please enter a 10-digit number.");
				continue;
			}
			System.out.println("Invalid sdt. Please try again.");
		}

		// Input and validate soNgayLamViec
		while (true) {
			System.out.print("Enter soNgayLamViec (0–31): ");
			try {
				soNgayLamViec = Integer.parseInt(scanner.nextLine());
				if (Validator.validateSoNgayLamViec(soNgayLamViec)) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid soNgayLamViec. Please enter a number between 0 and 31.");
				continue;
			}
			System.out.println("Invalid soNgayLamViec. Please try again.");
		}

		// Select TruongPhong
		while (true) {
			System.out.print("Does this employee have a TruongPhong manager? (yes/no): ");
			String response = scanner.nextLine().trim().toLowerCase();
			if (response.equals("yes")) {
				if (availableTruongPhongs.isEmpty()) {
					System.out.println("No TruongPhong available to assign. Setting TruongPhongQuanLi to null.");
					truongPhongQuanLi = null;
					break;
				}

				System.out.println("Available TruongPhong managers:");
				for (NhanVien tp : availableTruongPhongs) {
					System.out.println("Maso: " + tp.getMaso() + ", HoTen: " + tp.getHoTen());
				}

				System.out.print("Enter the maso of the TruongPhong to assign: ");
				String selectedMaso = scanner.nextLine();

				boolean found = false;
				for (NhanVien tp : availableTruongPhongs) {
					if (tp.getMaso().equals(selectedMaso)) {
						truongPhongQuanLi = (TruongPhong) tp;
						found = true;
						break;
					}
				}

				if (found) {
					break;
				} else {
					System.out.println("Invalid maso. Please select a valid maso from the list.");
				}
			} else if (response.equals("no")) {
				truongPhongQuanLi = null;
				break;
			} else {
				System.out.println("Invalid input. Please enter 'yes' or 'no'.");
			}
		}

		// Create and return a validated NhanVienThuong object
		NhanVienThuong nhanVienThuong = new NhanVienThuong(maso, hoTen, sdt, soNgayLamViec, truongPhongQuanLi);
		System.out.println("NhanVienThuong object created successfully!");
		return nhanVienThuong;
	}
}