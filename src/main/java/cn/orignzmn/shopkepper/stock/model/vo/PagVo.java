package cn.orignzmn.shopkepper.stock.model.vo;

//用于封装分页参数。
public class PagVo {
	private Integer page;
	private Integer rows;

	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public PagVo(Integer page, Integer rows) {
		super();
		this.page = page;
		this.rows = rows;
	}
	public PagVo() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "PagVo [page=" + page + ", rows=" + rows + "]";
	}
	
}
