package namhai;


import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Dao {
	public static List<Categories> listCS = new ArrayList<Categories>();
	public static List<Product> ListPt = new ArrayList<Product>();
	static Scanner sc = new Scanner(System.in);
	
	// phương thức nhập thông tin danh mục cha
	public void inputCatagoriesDad(){
		Categories cs = new Categories();
		cs.inputData();
		cs.setParentId(0);
		cs.setCount(1);
		listCS.add(cs);
	}
	
	// phương thức kiểm  tra CatalogID tồn tại duy nhất  //
	public void checkcatalogID(){
	for (int i = 0; i < listCS.size(); i++) {
		for(int j = listCS.size() - 1; j > i; j--) {
				while (listCS.get(i).getCatalogID() == (listCS.get(j).getCatalogID())) {
					System.err.println("catalogID : " + listCS.get(i).getCatalogID() + " da ton tai (nhap lai) : ");
					listCS.get(j).setCatalogID(Integer.parseInt(sc.nextLine()));
				}
			}
		}
	}

	// phương thức nhập thông tin danh mục //
	public void inputDataCatalog() {
		String ch = "y";
		do {
			System.out.println("==========================================================");
			System.out.println("( nhap 1 ): nhap danh muc cha \n( nhap 2 ): nhap danh muc con");
			try {
				int choice = Integer.parseInt(sc.nextLine());
				if (choice == 1) {
					inputCatagoriesDad();
				}
				if(choice == 2) {
					if (listCS.size() == 0) {
						System.err.println("chua co danh muc cha (nhap danh muc cha): ");
						inputCatagoriesDad();
					} else {
						System.out.println("nhap ma danh muc cha can tim : ");
						int reParentId = Integer.parseInt(sc.nextLine());
						boolean check = false;
						int count = 0;
						for (int i = 0; i < listCS.size(); i++) {
							if (listCS.get(i).getCatalogID() == reParentId && listCS.get(i).getCount() < 3) {
								System.out.println("ma danh muc: " + listCS.get(i).getCatalogID() + " - ten danh muc: "+ listCS.get(i).getCatalogName());
								Categories cs = new Categories();
								cs.inputData();
								cs.setParentId(listCS.get(i).getCatalogID());
								cs.setCount(listCS.get(i).getCount() + 1);
								listCS.add(cs);
								checkcatalogID();
								check = true;
								break;
							} else if (listCS.get(i).getCatalogID() != reParentId && listCS.get(i).getCount() < 3) {
								count = 1;
								check = false;
							} else {
								count = 0;
								check = false;
							}
						}
						if (!check) {
							if (count == 0) {
								System.err.println("vuot qua so cap danh muc !!!");
							} else if (count == 1) {
								System.err.println("khong tim thay ma danh muc cha");
							}
						}
					}
				}
			}catch (Exception e) {
				System.err.println("nhap khong dung dinh dang !!!");
			}
			System.out.println("nhap tiep (y/n)");
			ch = sc.nextLine();
		} while (ch.equalsIgnoreCase("y"));
	}
	
	// phương thức hiển thị chi tiết danh mục //
	public void displayDataCategories() {
		System.out.println("nhap ten danh muc can xem :");
		String recatalogName = sc.nextLine();
		List<Categories> result = listCS.stream().filter(a -> a.getCatalogName().contains(recatalogName)).collect(Collectors.toList());
		if(result.size() == 0) {
			System.err.println("khong tim thay danh muc !!!");
		}else {
			result.forEach((cs) -> {cs.displayData();});
		}
	}

	// phương thức hiện thị danh mục theo dạng cây //
	public void showCategoriesTree(int parentId,String tab,int num, String dot) {
		num = 1;
		for (Categories cs : listCS) {
			if(cs.getParentId() == parentId) {
				System.out.println( tab + dot + num + "." + cs.getCatalogName());
				showCategoriesTree(cs.getCatalogID(),tab+"\t",num,dot + (num++) + ".");
			}
		}
	}

	// phương thức xóa danh mục theo mã danh mục//
	public void removeCategories() {
		System.out.println("Nhap Ma Danh Muc Can Xoa : ");
		try {
			int reCatalogID = Integer.parseInt(sc.nextLine());
			Categories cs = listCS.stream().filter(item -> item.getCatalogID() == reCatalogID).findFirst().get();
			listCS.remove(cs);
			System.out.println("xoa thanh cong danh muc: " + cs.getCatalogName());
		} catch (Exception e) {
			System.err.println("khong tim thay danh muc can xoa ");
		}
	}

	// phương thức tìm kiếm danh mục theo mã danh mục 
	public void searchCategories() {
		try {
			System.out.println("nhap ten danh muc can tim kiem : ");
			String reCategoriesName = sc.nextLine();
			Categories cs =  listCS.stream().filter(a -> a.getCatalogName().equalsIgnoreCase(reCategoriesName)).findFirst().get();
			cs.displayData();	
		} catch (Exception e) {
			System.err.println("khong tim thay danh muc !!!");
		}
	}

	// phương thức lưu thông tin vào trong  file categories.txt //
	public void saveDataCategories() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("categories.txt");
			for (Categories cs : listCS) {
				String line = cs.convertText();
				byte c[] = line.getBytes();
				fos.write(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// phương thức lấy thông tin trong file categories.txt và in ra màn hình //
	public void uploadDaTaCategories() {
		FileInputStream fis = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream("categories.txt");
			is = new InputStreamReader(fis);
			br = new BufferedReader(is);
			String line;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				System.out.println(line);
				Categories cs = new Categories();
				cs.convertObject(line);
				listCS.add(cs);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	// ===================================  các phương thức của lớp  product ===================================== //

	// phương thức nhập thông tin sản phẩm 
	public void inputDataProduct() {
		String ch = "y";
		do {
			try {
				System.out.println("nhap ma danh muc can them san pham : ");
				int reCatalogID = Integer.parseInt(sc.nextLine());
				Categories cs = listCS.stream().filter((item) -> item.getCatalogID() == reCatalogID).findFirst().get();
					cs.displayData();
					System.out.println("nhap thong tin san pham: ");
					Product prd = new Product();
					prd.setCatelog(cs);
					prd.inputDataProductID();
					prd.inputData();
					ListPt.add(prd);	
			} catch (Exception e) {
				System.err.println("khong tim thay ma danh muc  !!!");
			}
			System.out.println("nhap tiep (y/n)");
			ch = sc.nextLine();
		} while (ch.equalsIgnoreCase("y"));
	}

	// phương thức hiển thị chi tiết thông tin sản phẩm theo tên sản phẩm 
	public void displayProduct(){
		System.out.println("nhap ten san pham can tim kiem :");
		String reProductName = sc.nextLine();
		List<Product> result = ListPt.stream().filter(item -> item.getProductName().toLowerCase().contains(reProductName.toLowerCase())).collect(Collectors.toList());
		if(result.size() > 0 ) {
			for (Product prd : result) {
				prd.displayData();
			}
		}else {
			System.err.println("khong tim thay doi tuong san pham ");
		}
	}

	// phương thức hiển thị thông tin sản phẩm theo danmh mục //
	public void showCategoriesPrd() {
		for (int i = 0; i < listCS.size(); i++) {
			int count = 0;
			for (int j = 0; j < ListPt.size(); j++) {
				if (listCS.get(i).getCatalogID() == ListPt.get(j).getCatelog().getCatalogID()) {
					++count;
					if (count == 1){
						System.out.println("Ma Danh Muc: " + listCS.get(i).getCatalogID() + " - Ten Danh Muc: "+ listCS.get(i).getCatalogName());
						System.out.println("====================================");
					}
					System.out.printf("\tMa San Pham: %s - Ten San Pham: %s\n",ListPt.get(j).getProductId(),ListPt.get(j).getProductName());
				}
			}
		}
	}

	// phương thức tính lợi nhuận sản phẩm 
	public void profitPrd() {
		ListPt.forEach((prd) ->{prd.calProfit();});
	}
	
	// phương thức tìm kiếm sản phẩm theo id sản phẩm 
	public Product prdById(String prdID) {
		Product prd = ListPt.stream().filter(item -> item.getProductId().equalsIgnoreCase(prdID)).findFirst().get();
		return prd;
	}

	// phương thức cập nhật thông tin sản phẩm theo mã sản phẩm //
	public void editDataPrd() {
		try {
			System.out.println("nhap ma san pham can cap nhat : ");
			String reProductId = sc.nextLine();
			System.out.println("doi tuong san pham can cap nhat la: ");
			prdById(reProductId).displayData();
			prdById(reProductId).inputData();
			System.out.println("cap nhap thong tin san pham thanh cong");
		} catch (Exception e){
			System.err.println("khong tim thay doi tuong san pham can cap nhat !!!");
		}
	}
	
	// phương thức cập nhật trạng thái sản phẩm theo mã sản phẩm 
	public void editDataPrdStatus() {
		try {
			System.out.println("nhap ma san pham can cap nhat : ");
			String reProductId = sc.nextLine();
			prdById(reProductId).setProductStatus(!prdById(reProductId).isProductStatus());
			System.out.println("cap nhat trang thai thanh cong");
		} catch (Exception e) {
			System.err.println("khong tim thay doi tuong san pham can cap nhat trang thai !!!");
		}
	}
	
	
	// phương thức hiển thị sản phẩm theo giá xuất tăng dần 
	public void sortCategoriesByExportPrice() {
		ListPt.sort((p1, p2) -> (int) (p1.getExportPrice() - p2.getExportPrice()));
		ListPt.forEach((prd)->{prd.displayData();});
	}

	// phương thức hiển thị sản phẩm theo lợi nhuận giảm dần 
	public void sortCategoriesByProfit() {
		ListPt.sort((p1, p2) -> (int) (p2.getProfit() - p1.getProfit()));
		ListPt.forEach((prd)->{prd.displayData();});
	}

	// phương thức lưu thông tin sản phẩm vào  file product.txt //
	public void saveDataPrd() {
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("product.txt");
			for (Product prd : ListPt) {
				String line = prd.convertLineText();
				byte pd [] = line.getBytes();
				fos.write(pd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if(fos != null){
				try {
					fos.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	// phương thức lấy thông tin sản phẩm từ  file product.txt //
	public void uploatDataPrd() {
		FileInputStream fis = null;
		InputStreamReader is = null;
		BufferedReader br = null;
		try {
			fis = new FileInputStream("product.txt");
			is = new InputStreamReader(fis);
			br = new BufferedReader(is);

			String line;
			while ((line = br.readLine()) != null) {
				if (line.isEmpty()) {
					continue;
				}
				System.out.println(line);
				Product prd = new Product();
				prd.covertObjectPrd(line);
				ListPt.add(prd);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


}

