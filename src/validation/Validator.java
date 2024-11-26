package validation;

import java.util.regex.Pattern;

public class Validator {
	public static boolean validateMaso(String maso) {
		String regex = "^NV\\d{4}$";
		return maso != null && maso.length() <= 10 && Pattern.matches(regex, maso);
	}

	public static boolean validateHoTen(String hoTen) {
		String regex = "^[a-zA-ZÀ-ỹ\\s]{1,35}$";
		return hoTen != null && Pattern.matches(regex, hoTen);
	}

	public static boolean validateSdt(String sdt) {
		String sdtStr = String.valueOf(sdt);
		String regex = "^(03|05|07|08|09)\\d{8}$";
		return sdtStr.length() == 10 && Pattern.matches(regex, sdtStr);
	}

	public static boolean validateSoNgayLamViec(int soNgayLamViec) {
		return soNgayLamViec >= 0 && soNgayLamViec <= 31;
	}

	public static boolean validateLuong(double luong) {
		return luong >= 0;
	}

	public static boolean validateTenCT(String tenCT) {
		return tenCT != null && tenCT.length() <= 20;
	}

	public static boolean validateMaSoThue(String maSoThue) {
		String regex = "^(\\d{10}|\\d{13})$"; // 10 or 13 digits only
		return maSoThue != null && maSoThue.matches(regex);
	}

	public static boolean validateMaSoGiamDoc(String maSoGiamDoc) {
		String regex = "^GD\\d{4}$";
		return maSoGiamDoc != null && Pattern.matches(regex, maSoGiamDoc);
	}

	public static boolean validateMaSoTruongPhong(String maSoTruongPhong) {
		String regex = "^TP\\d{4}$";
		return maSoTruongPhong != null && Pattern.matches(regex, maSoTruongPhong);
	}
	
    public static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
