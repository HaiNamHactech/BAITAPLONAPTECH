package namhai;

import java.util.Scanner;

public class ShopManagement {
	static Scanner sc = new Scanner(System.in);
	static Dao dao = new Dao();

	public static void main(String[] args) {
		do {
			System.out.println("********* MENU **********");
			System.out.println("1. Quan Ly Danh Muc ");
			System.out.println("2. Quan Ly San Pham ");
			System.out.println("3. Thoat ");
			System.out.println("nhap lua chon cua ban : ");
			int choice = Integer.parseInt(sc.nextLine());
			switch (choice) {
			case 1:
				boolean check1 = false;
				do {
					System.out.println("******* QUAN LY DANH MUC ********");
					System.out.println("1. Danh Sach Danh Muc  ");
					System.out.println("2. Them Danh Muc ");
					System.out.println("3. Xoa Danh Muc ");
					System.out.println("4. Tim Kiem Danh Muc ");
					System.out.println("5. Luu Thong Tin Danh Muc Vao File ");
					System.out.println("6. Load Thong Tin Tu Trong File ");
					System.out.println("7. Quay Lai");
					System.out.println("nhap lua chon cua ban : ");
					int C1 = Integer.parseInt(sc.nextLine());
					switch (C1) {
					case 1:
						boolean check2 = false;
						do {
							System.out.println("****** DANH SACH DANH MUC ****** ");
							System.out.println("1. Danh Sach Cay Danh Muc  ");
							System.out.println("2. Thong Tin Chi Tiet Danh Muc ");
							System.out.println("3. Quay Lai ");
							System.out.println("nhap lua chon cua ban : ");
							int C2 = Integer.parseInt(sc.nextLine());
							switch (C2) {
							case 1:
								System.out.println("Danh Sach Cay Danh Muc ");
								dao.showCategoriesTree(0, "", 1, "");
								break;
							case 2:
								System.out.println("Thong Tin Chi Tiet Danh Muc ");
								dao.displayDataCategories();
								break;
							case 3:
								System.err.println("Thoat khoi DANH SACH DANH MUC  ");
								check2 = true;
								break;
							default:
								System.err.println("vui long chon tu 1 -> 3");
								break;
							}
							if (check2) {
								break;
							}
						} while (true);
						break;
					case 2:
						System.out.println("them danh muc ");
						dao.inputDataCatalog();
						break;
					case 3:
						System.out.println("xoa danh muc ");
						dao.removeCategories();
						break;
					case 4:
						System.out.println("tim kiem danh muc ");
						dao.searchCategories();
						break;
					case 5:
						System.out.println("luu thong tin vao trong file ");
						dao.saveDataCategories();
						break;
					case 6:
						System.out.println("Load thong tin tu trong file ");
						dao.uploadDaTaCategories();
						break;
					case 7:
						System.err.println("thoat khoi QUAN LY DANH MUC  ");
						check1 = true;
						break;
					default:
						System.err.println("chon tu 1 -> 7");
						break;
					}
					if (check1) {
						break;
					}
				} while (true);
				break;
			case 2:
				boolean check3 = false;
				do {
					System.out.println("********* QUAN LY SAN PHAM *********");
					System.out.println("1. Them San Pham Moi  ");
					System.out.println("2. Tinh loi Nhuan San Pham ");
					System.out.println("3. Hien Thi Thong Tin San Pham ");
					System.out.println("4. Sap Xep San Pham ");
					System.out.println("5. Cap Nhat Thong Tin San Pham ");
					System.out.println("6. Cap Nhat Trang Thai San Pham ");
					System.out.println("7. luu thong tin san pham vao file product.txt");
					System.out.println("8. load thong tin san pham tu file product.txt ");
					System.out.println("9. Quay Lai ");
					System.out.println("nhap su lua chon cua ban : ");
					int C3 = Integer.parseInt(sc.nextLine());
					switch (C3) {
					case 1:
						System.out.println("them san pham moi");
						dao.inputDataProduct();
						break;
					case 2:
						System.out.println("tinh loi nhuan san pham");
						dao.profitPrd();
						break;
					case 3:
						boolean check4 = false;
						do {
							System.out.println("****** THONG TIN SAN PHAM ******");
							System.out.println("1. Hien Thi San Pham Theo Danh Muc ");
							System.out.println("2. Hien Thi Chi Tiet San Pham ");
							System.out.println("3. Quay Lai ");
							System.out.println("nhap lua chon cua ban ");
							int C4 = Integer.parseInt(sc.nextLine());
							switch (C4) {
							case 1:
								System.out.println("hien thi san pham theo danh muc ");
								dao.showCategoriesPrd();
								break;
							case 2:
								System.out.println("hien thi chi tiet san pham ");
								dao.displayProduct();
								break;
							case 3:
								System.err.println("thoat khoi THONG TIN SAN PHAM ");
								check4 = true;
								break;
							default:
								System.err.println("vui long chon tu 1 -> 3");
								break;
							}
							if (check4) {
								break;
							}
						} while (true);
						break;
					case 4:
						boolean check5 = false;
						do {
							System.out.println("****** SAP XEP SAN PHAM *******");
							System.out.println("1. Sap Xep San Pham Theo Gia Suat Tang Dan ");
							System.out.println("2. Sap Xep San Pham Theo Loi Nhuan Giam Dan  ");
							System.out.println("3. Quay Lai ");
							System.out.println(" nhap lua chon cua ban ");
							int C5 = Integer.parseInt(sc.nextLine());
							switch (C5) {
							case 1:
								System.out.println("sap xep san pham theo gia suat tang dan ");
								dao.sortCategoriesByExportPrice();
								break;
							case 2:
								System.out.println("sap xep san pham theo loi nhuan giam dan ");
								dao.sortCategoriesByProfit();
								break;
							case 3:
								System.err.println("Thoat khoi SAP XEP SAN PHAM ");
								check5 = true;
								break;

							default:
								System.err.println("vui long chon tu 1 -> 3");
								break;
							}
							if (check5) {
								break;
							}
						} while (true);
						break;
					case 5:
						System.out.println("cap nhat thong tin san pham ");
						dao.editDataPrd();
						break;
					case 6:
						System.out.println("cap nhat trang thai san pham ");
						dao.editDataPrdStatus();
						break;
					case 7:
						System.out.println("luu thong tin san pham vao file product.txt");
						dao.saveDataPrd();
						break;
					case 8:
						System.out.println("load thong tin san pham tu file product.txt");
						dao.uploatDataPrd();
						break;
					case 9:
						System.err.println("thoat khoi QUAN LY SAN PHAM ");
						check3 = true;
						break;
					default:
						System.err.println("vui long chon tu 1 -> 7");
						break;
					}
					if (check3) {
						break;
					}
				} while (true);
				break;
			case 3:
				System.err.println("thoat khoi CHUONG TRINH ");
				System.exit(0);
				break;
			default:
				break;
			}
		} while (true);
	}
}
