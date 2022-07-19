package namhai;

import java.io.Serializable;
import java.util.Scanner;

public class Categories  implements ICategories,Serializable {
	private static final long serialVersionUID = 1L;
	private int catalogID;
	private String catalogName;
	private String descriptions;
	private boolean catalogStatu;
	private int parentId;
	int count;

	public Categories() {
		super();
	}

	public Categories(int catalogID, String catalogName, String descriptions, boolean catalogStatu, int parentId) {
		super();
		this.catalogID = catalogID;
		this.catalogName = catalogName;
		this.descriptions = descriptions;
		this.catalogStatu = catalogStatu;
		this.parentId = parentId;
	}

	public int getCatalogID() {
		return catalogID;
	}

	public boolean setCatalogID(int catalogID) {
		if (catalogID > 0) {
			this.catalogID = catalogID;
			return true;
		} else {
			System.err.println("so nguyen > 0 (nhap lai) : ");
			return false;
		}
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public String getCatalogName() {
		return catalogName;
	}

	public boolean setCatalogName(String catalogName) {
		if (catalogName.length() >= 6 && catalogName.length() <= 30) {
			this.catalogName = catalogName;
			return true;
		} else {
			System.err.println("do dai 6 -> 30 ky tu (nhap lai) : ");
			return false;
		}

	}

	public String getDescriptions() {
		return descriptions;
	}

	public boolean setDescriptions(String descriptions) {
		if (descriptions.length() > 0) {
			this.descriptions = descriptions;
			return true;
		} else {
			System.err.println("khong duoc de trong (nhap lai) : ");
			return false;
		}
	}

	public boolean isCatalogStatu() {
		return catalogStatu;
	}

	public void setCatalogStatu(boolean catalogStatu) {
		this.catalogStatu = catalogStatu;
	}

	public int getParentId() {
		return parentId;
	}

	public void setParentId(int parentId) {
		this.parentId = parentId;
	}

	static Scanner sc = new Scanner(System.in);

	@Override
	public void inputData() {
		System.out.println("nhap ma danh muc  : ");
		while (true) {
			try {
				int reCatalogID = Integer.parseInt(sc.nextLine());
				boolean check = setCatalogID(reCatalogID);
				if (check) {
					break;
				}
			} catch (Exception e) {
				System.err.println("nhap khong dung dinh dang!!!");
			}
		}

		System.out.println("nhap ten danh muc : ");
		while (true) {
			String reCatalogName = sc.nextLine();
			boolean check = setCatalogName(reCatalogName);
			if (check) {
				break;
			}
		}

		System.out.println("nhap mo ta danh muc : ");
		while (true) {
			String reDescriptions = sc.nextLine();
			boolean check = setDescriptions(reDescriptions);
			if (check) {
				break;
			}
		}

		System.out.println("nhap trang thai danh muc : ");
		String reStatus = sc.nextLine();
		this.catalogStatu = Boolean.parseBoolean(reStatus);
		while (!(reStatus.equalsIgnoreCase("true") || reStatus.equalsIgnoreCase("false"))) {
			System.err.println("nhap true hoac false (nhap lai) : ");
			reStatus = sc.nextLine();
			this.catalogStatu = Boolean.parseBoolean(reStatus);
		}

	}

	String S = "";

	public String Status(boolean catalogStatu) {
		if (catalogStatu) {
			S = " hoat dong";
		} else {
			S = "khong hoat dong";
		}
		return S;
	}

	@Override
	public void displayData() {
		System.out.printf("Ma Danh Muc: %d - Ten Danh Muc : %s\nMo Ta: %s\nDanh Muc Cha: %s - Trang Thai: %s\n\n",
				catalogID, catalogName, descriptions, parentId, Status(catalogStatu));
	}

	// phương thức chuyển dạng đối tượng sang dạng lineText
	public String convertText() {
		return catalogID + "," + catalogName + "," + descriptions + "," + Status(catalogStatu) + "," + parentId + "\n";
	}

	// phương thức chuyển dạng linetext sang dạng đối tượng 
	public void convertObject(String line) {
		String[] param = line.split(",");
		try {
			catalogID = Integer.parseInt(param[0]);
			catalogName = param[1];
			descriptions = param[2];
			catalogStatu = Boolean.parseBoolean(param[3]);
			parentId = Integer.parseInt(param[4]);
		} catch (ArrayIndexOutOfBoundsException e) {
		}
	}
}
