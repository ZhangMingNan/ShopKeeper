package cn.orignzmn.shopkepper.stock.model.vo;


public class ProductNoVO {
	private Long id;
	private String no;
	private Float price; //进货价格
	private Float sellingPrice;//销售价格、
	private String shortNo;
	public Float getPrice() {
		return price;
	}
	public Float getSellingPrice() {
		return sellingPrice;
	}

	public void setSellingPrice(Float sellingPrice) {
		this.sellingPrice = sellingPrice;
	}

	public String getShortNo() {
		return shortNo;
	}

	public void setShortNo(String shortNo) {
		this.shortNo = shortNo;
	}

	public void setPrice(Float price) {
		this.price = price;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}

	@Override
	public String toString() {
		return "ProductNoVO [id=" + id + ", no=" + no + ", price=" + price
				+ ", sellingPrice=" + sellingPrice + ", shortNo=" + shortNo
				+ "]";
	}
	public ProductNoVO(Long id, String no) {
		super();
		this.id = id;
		this.no = no;
	}
	public ProductNoVO() {
		super();
		// TODO Auto-generated constructor stub
	}


}
