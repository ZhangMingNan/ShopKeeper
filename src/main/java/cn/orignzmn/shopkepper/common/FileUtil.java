package cn.orignzmn.shopkepper.common;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.UUID;

import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

public class FileUtil {
	private static String ak = "Ooubo321U42M8aPOBNR-yM30fP3GOVsYDmoKWNwK";
	private static String sk = "mbS4icSNwPL0iX1hz0HftBmyeLfC8jd21dBFXwMz";
	private static String uptoken = "Ooubo321U42M8aPOBNR-yM30fP3GOVsYDmoKWNwK:t6vxtyQFjkeqrh2eii-o4AAE-MU=:eyJzY29wZSI6ImNhaXpoYW5nZ3VpIiwiZGVhZGxpbmUiOjE0MjI2MTcyNTZ9";

	public static void updataToQiNiu(InputStream in,String key) throws FileNotFoundException{
	   // PutExtra extra = new PutExtra();
       // PutRet ret = IoApi.Put(uptoken, key,in, extra);
        //System.out.println(ret.getStatusCode());
	}
//	   public static void main(String[] args) throws Exception {
//	        Config.ACCESS_KEY = ak;
//	        Config.SECRET_KEY = sk;
//	        Mac mac = new Mac(Config.ACCESS_KEY, Config.SECRET_KEY);
//	        // 请确保该bucket已经存在
//	        String bucketName = "caizhanggui";
//	        PutPolicy putPolicy = new PutPolicy(bucketName);
//	        String uptoken = putPolicy.token(mac);
//	        System.out.println(uptoken);
//	    }
	public static String getUuidFileName(MultipartFile file) {
		// TODO Auto-generated method stub
		return  UUID.randomUUID().toString()+"."+Files.getFileExtension(file.getOriginalFilename());
	}
}
