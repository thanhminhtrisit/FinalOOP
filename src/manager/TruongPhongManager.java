package manager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import pojo.NhanVien;
import pojo.TruongPhong;
import validation.Validator;

public class TruongPhongManager {
	private List<NhanVien> listTruongPhong = new ArrayList<NhanVien>();

	public TruongPhongManager() {
		super();
	}

	public TruongPhongManager(List<NhanVien> listTruongPhong) {
		super();
		this.listTruongPhong = listTruongPhong;
	}

	public List<NhanVien> getListTruongPhong() {
		return listTruongPhong;
	}

	public void themTruongPhong() {
		Scanner scanner = new Scanner(System.in);
		String maso, hoTen, sdt;
		int soNgayLamViec;

		// Input and validate maso
		while (true) {
			System.out.print("Enter maso (must start with 'TP' followed by 4 digits and max 10 characters): ");
			// Create a new variable for each iteration
			String inputMaso = scanner.nextLine(); 
			boolean exists = this.listTruongPhong.stream().anyMatch(tp -> tp.getMaso().equals(inputMaso));

			if (Validator.validateMaSoTruongPhong(inputMaso) && !exists) {
				maso = inputMaso; 
				break;
			}
			if (exists) {
				System.out.println("The maso already exists. Please enter a different maso.");
			} else {
				System.out.println("Invalid maso. Please try again.");
			}
		}

		// Input and validate hoTen
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
			sdt = scanner.nextLine();
			if (Validator.validateSdt(sdt)) {
				break;
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
			}
		}

		TruongPhong truongPhong = new TruongPhong(maso, hoTen, sdt, soNgayLamViec, new ArrayList<>());
		this.listTruongPhong.add(truongPhong);
		System.out.println("Added TruongPhong successfully!");
	}

	public void xoaTruongPhong(String maso) {
		if (!this.listTruongPhong.isEmpty()) {
			for (NhanVien truongPhong : this.listTruongPhong) {
				if (maso.equals(truongPhong.getMaso())) {
					this.listTruongPhong.remove(truongPhong);
					System.out.println("Deleted TruongPhong with maso: " + maso);
					return;
				}
			}
			System.out.println("TruongPhong with the specified maso not found!");
		} else {
			System.out.println("The list of TruongPhong is empty!");
		}
	}

	public void updateTruongPhong(String maso) {
		Scanner scanner = new Scanner(System.in);
		for (int i = 0; i < this.listTruongPhong.size(); i++) {
			if (this.listTruongPhong.get(i).getMaso().equals(maso)) {
				TruongPhong currentTruongPhong = (TruongPhong) this.listTruongPhong.get(i);

				System.out.println("Updating TruongPhong: " + currentTruongPhong.getHoTen());
				System.out.println("Press Enter to keep the current value.");

				// Update hoTen
				System.out.print("Enter new hoTen (current: " + currentTruongPhong.getHoTen() + "): ");
				String newHoTen = scanner.nextLine();
				if (!newHoTen.isBlank() && Validator.validateHoTen(newHoTen)) {
					currentTruongPhong.setHoTen(newHoTen);
				}

				// Update sdt
				System.out.print("Enter new sdt (current: " + currentTruongPhong.getSdt() + "): ");
				String newSdt = scanner.nextLine();
				if (!newSdt.isBlank() && Validator.validateSdt(newSdt)) {
					currentTruongPhong.setSdt(newSdt);
				}

				// Update soNgayLamViec
				System.out.print("Enter new soNgayLamViec (current: " + currentTruongPhong.getSoNgayLamViec() + "): ");
				String newSoNgayLamViecInput = scanner.nextLine();
				if (!newSoNgayLamViecInput.isBlank()) {
					try {
						int newSoNgayLamViec = Integer.parseInt(newSoNgayLamViecInput);
						if (Validator.validateSoNgayLamViec(newSoNgayLamViec)) {
							currentTruongPhong.setSoNgayLamViec(newSoNgayLamViec);
						} else {
							System.out.println("Invalid soNgayLamViec. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for soNgayLamViec. Keeping the current value.");
					}
				}

				this.listTruongPhong.set(i, currentTruongPhong);
				System.out.println("Updated TruongPhong successfully.");
				return;
			}
		}
		System.out.println("TruongPhong not found.");
	}

	public void createTruongPhong(List<NhanVien> listAllNhanVien) {
		themTruongPhong();
		listAllNhanVien.addAll(getListTruongPhong());
	}

	public void readAllTruongPhong(List<NhanVien> listAllNhanVien) {
		listAllNhanVien.stream().filter(nv -> nv instanceof TruongPhong).forEach(nv -> System.out.println(nv));
	}

	public void updateTruongPhong(List<NhanVien> listAllNhanVien, String maso) {
		updateTruongPhong(maso); 
		listAllNhanVien.clear();
		listAllNhanVien.addAll(getListTruongPhong());
	}

	public void deleteTruongPhong(List<NhanVien> listAllNhanVien, String maso) {
		xoaTruongPhong(maso); 
		listAllNhanVien.removeIf(nv -> nv.getMaso().equals(maso) && nv instanceof TruongPhong);
	}

	public void displayAllTruongPhong() {
		if (this.listTruongPhong.isEmpty()) {
			System.out.println("No TruongPhong found.");
		} else {
			for (NhanVien tp : this.listTruongPhong) {
				System.out.println("Maso: " + tp.getMaso());
				System.out.println("HoTen: " + tp.getHoTen());
			}
		}
	}
}
