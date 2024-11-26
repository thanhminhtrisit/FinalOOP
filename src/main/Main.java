package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import input.UserInput;
import manager.CongTyManager;
import manager.GiamDocManager;
import manager.NhanVienThuongManager;
import manager.TruongPhongManager;
import pojo.GiamDoc;
import pojo.NhanVien;
import pojo.NhanVienThuong;
import pojo.TruongPhong;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		List<NhanVien> list = new ArrayList<NhanVien>();
//		TruongPhong truongPhong = new TruongPhong("NV0028", "Luong Cao Bang", "0902115678", 15, list);
//		list.add(new NhanVienThuong("NV0001", "Nguyen Van A", "0908070605", 20, null));
//		list.add(new NhanVienThuong("NV0002", "Nguyen Van B", "0908070604", 25, truongPhong));
//		list.add(new TruongPhong("NV0028", "Luong Cao Bang", "0902115678", 15, list));
//		NhanVienThuongManager nhanVienThuongManager = new NhanVienThuongManager(list, list);
//		nhanVienThuongManager.displayAllNhanVienThuong();
//		nhanVienThuongManager.themNhanVienThuong();
//		String ms = new Scanner(System.in).nextLine();
//		nhanVienThuongManager.updateEmployee(ms);
//		nhanVienThuongManager.xoaNhanVienThuong(ms);
//		nhanVienThuongManager.displayAllNhanVienThuong();
		Scanner scanner = new Scanner(System.in);
		NhanVienThuongManager nhanVienThuongManager = new NhanVienThuongManager(taoNhanVienThuong(), taoTruongPhong());
		TruongPhongManager truongPhongManager = new TruongPhongManager(taoTruongPhong());
		GiamDocManager giamDocManager = new GiamDocManager(taoGiamDoc());
		CongTyManager congTyManager = new CongTyManager(giamDocManager, truongPhongManager, nhanVienThuongManager);
		congTyManager.updateListAllNhanVien();
		int choice;
		do {
			System.out.println("\n=== Company Management System ===");
			System.out.println("1. Enter company information");
			System.out.println("2. Assign employees to TruongPhong");
			System.out.println("3. Add/Delete an employee (NhanVienThuong, TruongPhong, or GiamDoc)");
			System.out.println("4. Display all employees in the company");
			System.out.println("5. Calculate and display total salary for the company");
			System.out.println("6. Find NhanVienThuong with the highest salary");
			System.out.println("7. Find TruongPhong with the most subordinates");
			System.out.println("8. Sort all employees alphabetically by name");
			System.out.println("9. Sort all employees by salary in descending order");
			System.out.println("10. Find GiamDoc with the most shares");
			System.out.println("11. Calculate and display the total income for each GiamDoc");
			System.out.println("12. Exit");
			System.out.print("Enter your choice: ");
			choice = (int) UserInput.getIntegerInput();
			System.err.println(congTyManager.getCongTy().getDoanhThuThang());

			switch (choice) {
			case 1:
				congTyManager.createNewCompany();
				break;
			case 2:
				System.out.print("Enter maso (must start with 'NV' followed by 4 digits):");
				String maso = new Scanner(System.in).nextLine();
				congTyManager.assignNhanVienThuongToTruongPhong(maso);
				break;
			case 3:
				manageEmployee(scanner, congTyManager);
				break;
			case 4:
				congTyManager.displayAllEmployeesFormatted();
				break;
			case 5:
				congTyManager.displayAllSalaries();
				break;
			case 6:
				congTyManager.findHighestPaidNhanVienThuong();
				break;
			case 7:
				congTyManager.findTruongPhongWithMostNhanVienThuong();
				break;
			case 8:
				congTyManager.sortEmployeesByName();
				congTyManager.displayAllEmployeesFormatted();
				break;
			case 9:
				congTyManager.sortEmployeesBySalary();
				congTyManager.displayAllEmployeesFormatted();
				break;
			case 10:
				congTyManager.findGiamDocWithMostCoPhan();
				break;
			case 11:
				congTyManager.displayAllGiamDocIncomes();
				break;
			case 12:
				System.out.println("Exiting program. Goodbye!");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 12);

	}

	private static void manageEmployee(Scanner scanner, CongTyManager congTyManager) {
		int choice;
		do {
			System.out.println("\n--- Manage Employees ---");
			System.out.println("1. Add a new employee");
			System.out.println("2. Delete an employee");
			System.out.println("3. Update an employee");
			System.out.println("4. Back to Main Menu");
			System.out.print("Enter your choice: ");
			choice = (int) UserInput.getIntegerInput();

			switch (choice) {
			case 1:
				addEmployeeMenu(scanner, congTyManager);
				break;
			case 2:
				System.out.print("Enter maso of employee to delete: ");
				String deleteMaso = scanner.nextLine();
				if (deleteMaso.startsWith("NV")) {
					congTyManager.xoaNhanVienThuong(deleteMaso);
				} else if (deleteMaso.startsWith("TP")) {
					congTyManager.xoaTruongPhong(deleteMaso);
				} else if (deleteMaso.startsWith("GD")) {
					congTyManager.xoaGiamDoc(deleteMaso);
				} else {
					System.out.println("Invalid maso. Please ensure it starts with 'NV', 'TP', or 'GD' and 4 digit followed.");
				}
				break;
			case 3:
				System.out.print("Enter maso of employee to update: ");
				String updateMaso = scanner.nextLine();
				if (updateMaso.startsWith("NV")) {
					congTyManager.updateNhanVienThuong(updateMaso);
				} else if (updateMaso.startsWith("TP")) {
					congTyManager.updateTruongPhong(updateMaso);
				} else if (updateMaso.startsWith("GD")) {
					congTyManager.updateGiamDoc(updateMaso);
				} else {
					System.out.println("Invalid maso. Please ensure it starts with 'NV', 'TP', or 'GD'.");
				}
				break;
			case 4:
				System.out.println("Returning to main menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}

	private static void addEmployeeMenu(Scanner scanner, CongTyManager congTyManager) {
		int choice;
		do {
			System.out.println("\n--- Add a New Employee ---");
			System.out.println("1. Add NhanVienThuong");
			System.out.println("2. Add TruongPhong");
			System.out.println("3. Add GiamDoc");
			System.out.println("4. Back to Manage Employees Menu");
			System.out.print("Enter your choice: ");
			choice = (int) UserInput.getIntegerInput();

			switch (choice) {
			case 1:
				congTyManager.addNhanVienThuong();
				break;
			case 2:
				congTyManager.addTruongPhong();
				break;
			case 3:
				congTyManager.addGiamDoc();
				break;
			case 4:
				System.out.println("Returning to Manage Employees menu...");
				break;
			default:
				System.out.println("Invalid choice. Please try again.");
			}
		} while (choice != 4);
	}

	private static List<NhanVien> taoNhanVienThuong() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		NhanVienThuong nvThuong1 = new NhanVienThuong("NV0001", "Tran Van C", "0911223344", 22, null);
		NhanVienThuong nvThuong2 = new NhanVienThuong("NV0002", "Pham Thi D", "0909988776", 18, null);
		NhanVienThuong nvThuong3 = new NhanVienThuong("NV0003", "Vu Van E", "0912341234", 30, null);
		NhanVienThuong nvThuong4 = new NhanVienThuong("NV0004", "Nguyen Thi H", "0945678901", 24, null);
		NhanVienThuong nvThuong5 = new NhanVienThuong("NV0005", "Le Van K", "0932123456", 15, null);
		NhanVienThuong nvThuong6 = new NhanVienThuong("NV0006", "Pham Van L", "0976543210", 20, null);
		list.add(nvThuong6);
		list.add(nvThuong5);
		list.add(nvThuong4);
		list.add(nvThuong3);
		list.add(nvThuong2);
		list.add(nvThuong1);
		return list;
	}

	private static List<NhanVien> taoTruongPhong() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		TruongPhong truongPhong1 = new TruongPhong("TP0001", "Nguyen Van A", "0912345678", 20, new ArrayList<>());
		TruongPhong truongPhong2 = new TruongPhong("TP0002", "Le Thi B", "0987654321", 25, new ArrayList<>());
		list.add(truongPhong2);
		list.add(truongPhong1);
		return list;
	}

	private static List<NhanVien> taoGiamDoc() {
		List<NhanVien> list = new ArrayList<NhanVien>();
		GiamDoc giamDoc1 = new GiamDoc("GD0001", "Nguyen Van F", "0933445566", 28, 0.10);
		GiamDoc giamDoc2 = new GiamDoc("GD0002", "Tran Thi G", "0922233445", 25, 0.15);
		list.add(giamDoc2);
		list.add(giamDoc1);
		return list;
	}

}
