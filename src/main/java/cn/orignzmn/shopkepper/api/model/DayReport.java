package cn.orignzmn.shopkepper.api.model;

/**
 * 显示
 * @author 张明楠
 */
public class DayReport {
    //销售量，销售额，利润
	private Integer id;
	private Float saleroom;
	private Integer volumen;
	private Float  profit;
	public Integer getVolumen() {
		return volumen;
	}
	public void setVolumen(Integer volumen) {
		this.volumen = volumen;
	}
	public Float getSaleroom() {
		return saleroom;
	}
	public void setSaleroom(Float saleroom) {
		this.saleroom = saleroom;
	}
	public Float getProfit() {
		return profit;
	}
	public void setProfit(Float profit) {
		this.profit = profit;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "DayReport [id=" + id + ", volumen=" + volumen + ", saleroom="
				+ saleroom + ", profit=" + profit + "]";
	}
}
