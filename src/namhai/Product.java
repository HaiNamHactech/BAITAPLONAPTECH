package namhai;

import java.io.Serializable;
import java.util.Scanner;

public class Product implements IProduct,Serializable{
	private static final long serialVersionUID = 1L;
	private String productId;
	private String productName;
	private String title;
	private float importPrice;
	private float exportPrice;
	private float profit;
	private String descriptions;
	private boolean productStatus;
	private Categories catelog;

	public Product() {
		super();
	}

	public Product(String productId, String productName, String title, float importPrice, float exportPrice,
			float profit, String descriptions, boolean productStatus, Categories catelog) {
		super();
		this.productId = productId;
		this.productName = productName;
		this.title = title;
		this.importPrice = importPrice;
		this.exportPrice = exportPrice;
		this.profit = profit;
		this.descriptions = descriptions;
		this.productStatus = productStatus;
		this.catelog = catelog;
	}

	public String getProductId() {
		return productId;
	}

	public boolean setProductId(String productId) {
		for (Product item : Dao.ListPt) {
			if (item.getProductId().equalsIgnoreCase(productId)) {
				System.err.println("ma san pham da ton tai !!! (nhap lai): ");
				return false;
			}
		}
		if (productId.startsWith("C") && productId.length() == 4) {
			this.productId = productId;
			return true;
		} else {
			System.err.println("ma danh muc bat dau bang C va co do dai la 4 (nhap lai) : ");
			return false;
		}
	}

	public String getProductName() {
		return productName;
	}

	public boolean setProductName(String productName) {
		for (Product item : Dao.ListPt) {
			if (item.getProductName().equalsIgnoreCase(productName)) {
				System.err.println("ten san pham da ton tai !!! (nhap lai): ");
				return false;
			}
		}
		if (productName.length() >= 5 && productName.length() <= 60) {
			this.productName = productName;
			return true;
		} else {
			System.err.println("productName co do dai tu 5 -> 60 (nhap lai) : ");
			return false;
		}
	}

	public String getTitle() {
		return title;
	}

	public boolean setTitle(String title) {
		if (title.length() >= 6 && title.length() <= 30) {
			this.title = title;
			return true;
		} else {
			System.err.println("title co do dai tu 6 -> 30 (nhap lai) : ");
			return false;
		}
	}

	public float getImportPrice() {
		return importPrice;
	}

	public boolean setImportPrice(float importPrice) {
		if (importPrice > 0) {
			this.importPrice = importPrice;
			return true;
		} else {
			System.err.println("importPrice la so thuc > 0 (nhap lai) : ");
			return false;
		}
	}

	public float getExportPrice() {
		return exportPrice;
	}

	public boolean setExportPrice(float exportPrice) {
		if (exportPrice >= getImportPrice() * MIN_INTEREST_RATE + getImportPrice()) {
			this.exportPrice = exportPrice;
			return true;
		} else {
			System.err.println("gia ban lon hon : " + (getImportPrice() * MIN_INTEREST_RATE + getImportPrice())
					+ " (nhap lai ) : ");
			return false;
		}
	}

	public float getProfit() {
		return profit;
	}

	public void setProfit(float profit) {
		this.profit = profit;
	}

	public String getDescriptions() {
		return descriptions;
	}

	public boolean setDescriptions(String descriptions) {
		if (descriptions.length() > 0) {
			this.descriptions = descriptions;
			return true;
		} else {
			System.err.println("khong duoc de trong  (nhap lai) :  ");
			return false;
		}
	}

	public boolean isProductStatus() {
		return productStatus;
	}

	public void setProductStatus(boolean productStatus) {
		this.productStatus = productStatus;
	}

	public Categories getCatelog() {
		return catelog;
	}

	public void setCatelog(Categories catelog) {
		this.catelog = catelog;
	}

	static Scanner sc = new Scanner(System.in);

	// phÆ°Æ¡ng thá»©c nháº­p mÃ£ sáº£n pháº©m //
	public void inputDataProductID() {
		System.out.println("nhap id san pham : ");
		while (true) {
			String reProductId = sc.nextLine();
			boolean check = setProductId(reProductId);
			if (check) {
				break;
			}
		}
	}

	// phÆ°Æ¡ng thá»©c vá»«a Ä‘á»ƒ cáº­p nháº­t thÃ´ng tin sáº£n pháº©m ,vá»«a Ä‘á»ƒ
	// nháº­p sáº£n pháº©m//
	@Override
	public void inputData() {
		System.out.println("nhap ten san pham : ");
		while (true) {
			String reProductName = sc.nextLine();
			boolean check = setProductName(reProductName);
			if (check) {
				break;
			}
		}

		System.out.println("nhap tieu de san pham : ");
		while (true) {
			String reTitle = sc.nextLine();
			boolean check = setTitle(reTitle);
			if (check) {
				break;
			}
		}

		System.out.println("nhap gia nhap san pham : ");
		while (true) {
			try {
				float reImportPrice = Float.parseFloat(sc.nextLine());
				boolean check = setImportPrice(reImportPrice);
				if (check) {
					break;
				}
			} catch (Exception e) {
				System.err.println("nhap khong dung dinh dang !!! (nhap lai): ");
			}
		}

		System.out.println("nhap gia ban san pham : ");
		while (true) {
			try {
				float reExportPrice = Float.parseFloat(sc.nextLine());
				boolean check = setExportPrice(reExportPrice);
				if (check) {
					break;
				}
			} catch (Exception e) {
				System.out.println("nhap khong dung dinh dang !!! (nhap lai): ");
			}
		}

		System.out.println("nhap mo ta san pham ");
		while (true) {
			String reDescriptions = sc.nextLine();
			boolean check = setDescriptions(reDescriptions);
			if (check) {
				break;
			}
		}

		System.out.println("nhap trang thai san pham : ");
		String reStatus = sc.nextLine();
		this.productStatus = Boolean.parseBoolean(reStatus);
		while (!(reStatus.equalsIgnoreCase("true") || reStatus.equalsIgnoreCase("false"))) {
			System.err.println("nhap true hoac false (nhap lai) : ");
			reStatus = sc.nextLine();
			this.productStatus = Boolean.parseBoolean(reStatus);
		}
	}

	@Override
	public void displayData() {
		System.out.printf(
				"productId: %s - productName: %s - title: %s\nimportPrice: %.2f - exportPrice: %.2f => profit: %.2f\n"
						+ "descriptions: %s - productStatus: %s - catalog: %s\n\n",
				productId, productName, title, importPrice, exportPrice, profit, descriptions, Status(productStatus),
				catelog.getCatalogName());
	}

	String S = "";

	public String Status(boolean prdStatus) {
		if (prdStatus) {
			S = " hoat dong";
		} else {
			S = "khong hoat dong";
		}
		return S;
	}

	@Override
	public void calProfit() {
		this.profit = exportPrice - importPrice;
	}

	// phương thức chuyển dạng đối tượng sang dạnh LineText
	public String convertLineText() {
		return productId + "," + productName + "," + title + "," + importPrice + "," + exportPrice + "," + profit + ","
				+ descriptions + "," + Status(productStatus) + "," + catelog.convertText() + "\n";
	}

	// phương thức chuyển dạng LineText sang dạng đối tượng //
	public void covertObjectPrd(String line) {
		String[] prama = line.split(",");
		try {
			productId = prama[0];
			productName = prama[1];
			title = (String) prama[2];
			importPrice = Float.parseFloat(prama[3]);
			exportPrice = Float.parseFloat(prama[4]);
			profit = Float.parseFloat(prama[5]);
			descriptions = prama[6];
			productStatus = Boolean.parseBoolean(prama[7]);
			Categories cs = new Categories();
			cs.setCatalogID(Integer.parseInt(prama[8]));
			cs.setCatalogName(prama[9]);
			cs.setCatalogStatu(Boolean.parseBoolean(prama[10]));
			cs.setDescriptions(prama[11]);
			cs.setParentId(Integer.parseInt(prama[12]));
			this.catelog = cs;
		} catch (ArrayIndexOutOfBoundsException e) {

		}
	}

}
