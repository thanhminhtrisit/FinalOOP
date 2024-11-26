package manager;

import java.util.List;
import java.util.Scanner;

import pojo.GiamDoc;
import pojo.NhanVien;
import validation.Validator;

public class GiamDocManager {
	private List<NhanVien> listNhanVien;

	public GiamDocManager(List<NhanVien> listNhanVien) {
		this.listNhanVien = listNhanVien;
	}

	public List<NhanVien> getListNhanVien() {
		return listNhanVien;
	}

	// Add a GiamDoc
	public void themGiamDoc() {
		Scanner scanner = new Scanner(System.in);
		String maso, hoTen, sdt;
		int soNgayLamViec;
		double coPhan;

		// Input and validate maso
		while (true) {
			System.out.print("Enter maso (must start with 'GD' followed by 4 digits and max 10 characters): ");
			String inputMaso = scanner.nextLine();
			boolean exists = this.listNhanVien.stream().anyMatch(nv -> nv.getMaso().equals(inputMaso));
			if (Validator.validateMaSoGiamDoc(inputMaso) && !exists) {
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

		// Input and validate coPhan
		while (true) {
			System.out.print("Enter coPhan (percentage, must be >= 0): ");
			try {
				coPhan = Double.parseDouble(scanner.nextLine());
				if (coPhan >= 0) {
					break;
				}
			} catch (NumberFormatException e) {
				System.out.println("Invalid coPhan. Please enter a valid non-negative number.");
			}
		}

		// Create and add GiamDoc
		GiamDoc giamDoc = new GiamDoc(maso, hoTen, sdt, soNgayLamViec, coPhan);
		this.listNhanVien.add(giamDoc);
		System.out.println("Added GiamDoc successfully!");
	}

	// Delete a GiamDoc by maso
	public void xoaGiamDoc(String maso) {
		if (!this.listNhanVien.isEmpty()) {
			for (NhanVien nv : this.listNhanVien) {
				if (nv instanceof GiamDoc && maso.equals(nv.getMaso())) {
					this.listNhanVien.remove(nv);
					System.out.println("Deleted GiamDoc with maso: " + maso);
					return;
				}
			}
			System.out.println("GiamDoc with the specified maso not found!");
		} else {
			System.out.println("The list of employees is empty!");
		}
	}

	// Update a GiamDoc
	public void updateGiamDoc(String maso) {
		Scanner scanner = new Scanner(System.in);
		for (NhanVien nv : this.listNhanVien) {
			if (nv instanceof GiamDoc && nv.getMaso().equals(maso)) {
				GiamDoc currentGiamDoc = (GiamDoc) nv;

				System.out.println("Updating GiamDoc: " + currentGiamDoc.getHoTen());
				System.out.println("Press Enter to keep the current value.");

				// Update hoTen
				System.out.print("Enter new hoTen (current: " + currentGiamDoc.getHoTen() + "): ");
				String newHoTen = scanner.nextLine();
				if (!newHoTen.isBlank() && Validator.validateHoTen(newHoTen)) {
					currentGiamDoc.setHoTen(newHoTen);
				}

				// Update sdt
				System.out.print("Enter new sdt (current: " + currentGiamDoc.getSdt() + "): ");
				String newSdt = scanner.nextLine();
				if (!newSdt.isBlank() && Validator.validateSdt(newSdt)) {
					currentGiamDoc.setSdt(newSdt);
				}

				// Update soNgayLamViec
				System.out.print("Enter new soNgayLamViec (current: " + currentGiamDoc.getSoNgayLamViec() + "): ");
				String newSoNgayLamViecInput = scanner.nextLine();
				if (!newSoNgayLamViecInput.isBlank()) {
					try {
						int newSoNgayLamViec = Integer.parseInt(newSoNgayLamViecInput);
						if (Validator.validateSoNgayLamViec(newSoNgayLamViec)) {
							currentGiamDoc.setSoNgayLamViec(newSoNgayLamViec);
						} else {
							System.out.println("Invalid soNgayLamViec. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for soNgayLamViec. Keeping the current value.");
					}
				}

				// Update coPhan
				System.out.print("Enter new coPhan (current: " + currentGiamDoc.getCoPhan() + "): ");
				String newCoPhanInput = scanner.nextLine();
				if (!newCoPhanInput.isBlank()) {
					try {
						double newCoPhan = Double.parseDouble(newCoPhanInput);
						if (newCoPhan >= 0) {
							currentGiamDoc.setCoPhan(newCoPhan);
						} else {
							System.out.println("Invalid coPhan. Keeping the current value.");
						}
					} catch (NumberFormatException e) {
						System.out.println("Invalid input for coPhan. Keeping the current value.");
					}
				}

				System.out.println("Updated GiamDoc successfully.");
				return;
			}
		}
		System.out.println("GiamDoc not found.");
	}

	public void createGiamDoc(List<NhanVien> listAllNhanVien) {
		themGiamDoc(); // Đã được định nghĩa trong class
		listAllNhanVien.addAll(getListNhanVien());
	}

	public void readAllGiamDoc(List<NhanVien> listAllNhanVien) {
		listAllNhanVien.stream().filter(nv -> nv instanceof GiamDoc).forEach(nv -> System.out.println(nv));
	}

	public void updateGiamDoc(List<NhanVien> listAllNhanVien, String maso) {
		updateGiamDoc(maso); // Đã được định nghĩa trong class
		listAllNhanVien.clear();
		listAllNhanVien.addAll(getListNhanVien());
	}

	public void deleteGiamDoc(List<NhanVien> listAllNhanVien, String maso) {
		xoaGiamDoc(maso); // Đã được định nghĩa trong class
		listAllNhanVien.removeIf(nv -> nv.getMaso().equals(maso) && nv instanceof GiamDoc);
	}

	// Display all GiamDoc
	public void displayAllGiamDoc() {
		boolean hasGiamDoc = false;
		for (NhanVien nv : this.listNhanVien) {
			if (nv instanceof GiamDoc) {
				GiamDoc gd = (GiamDoc) nv;
				System.out.println("Maso: " + gd.getMaso());
				System.out.println("HoTen: " + gd.getHoTen());
				System.out.println("CoPhan: " + gd.getCoPhan());
				hasGiamDoc = true;
			}
		}
		if (!hasGiamDoc) {
			System.out.println("No GiamDoc found.");
		}
	}
}
