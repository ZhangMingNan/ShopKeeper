package cn.orignzmn.shopkepper.stock.quartz; 

import java.io.File;
import java.util.List;

import javapns.Push;
import javapns.notification.PushNotificationPayload;
import jodd.util.ClassLoaderUtil;

import org.springframework.beans.factory.annotation.Autowired;

import cn.orignzmn.shopkepper.stock.model.TradingRecord;
import cn.orignzmn.shopkepper.stock.model.vo.TradingRecordVO;
import cn.orignzmn.shopkepper.stock.service.TradingRecordService;

public class PostTask {
	private static  String filePath;
	static{
		File file = ClassLoaderUtil.getResourceFile("aps_developer_identity.p12");
		filePath = file.getAbsolutePath();
	}

	public  String alertBody(TradingRecord tr){
		StringBuilder sb = new StringBuilder(tr.getEmployee().getName()+"\n售出:");
		sb.append(tr.getStockProduct().getProductNo().getNo()+":"+tr.getSalesVolume()+"(件)\n");
		sb.append("售价:"+tr.getSale()+"(元)\n");
		sb.append("进价:"+tr.getStockProduct().getProductNo().getPrice()+"(元)");
		return sb.toString();
	}
	private static final	String tk = "432019b88da17ca04268dccc92eee108851b45e5fda71aaeec80771feebc29e3";
	private static	PushNotificationPayload payload  = new PushNotificationPayload();
	@Autowired
	private TradingRecordService tradingRecordService;
	public void  post(){
		TradingRecordVO trv = new TradingRecordVO();
		trv.setPost(1);//查询没有发送过的新销售记录
		trv.setDate(null);
		List<TradingRecord> trList = 	tradingRecordService.findAll(trv);
		for(TradingRecord tr :trList){
			//更新数据库状态
			tradingRecordService.updatePost(tr);
			try {
				payload.addCustomAlertBody(alertBody(tr));
				payload.addSound("default");
				payload.addBadge(10);
				Push.payload(payload,filePath, "2w3e4r5t", false, tk);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}

