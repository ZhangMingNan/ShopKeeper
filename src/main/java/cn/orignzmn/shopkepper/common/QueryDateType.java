package cn.orignzmn.shopkepper.common;


/**
 * YMD 代表年月日
 * YM 代表年月
 * @author lenovo
 *
 */
public enum QueryDateType {
    YMD("%Y-%m-%d"),YM("%Y-%m"),Y("%Y");
    private String format;
    QueryDateType(String format){
    	this.format = format;
    }
    public String format(){
    	return this.format;
    }
}
