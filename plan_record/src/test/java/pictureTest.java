import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.plan_record.PictureManager;
import edu.thu.cslab.footwith.plan_record.RecordManager;
import edu.thu.cslab.footwith.utility.Constant;
import net.iharder.Base64;
import org.json.JSONException;
import org.junit.Test;

import java.io.*;
import java.sql.SQLException;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/11/13
 * Time: 7:49 PM
 * To change this template use File | Settings | File Templates.
 */
public class pictureTest {
//    @Test
//    public void getPictureTest() throws IOException, SQLException{
//        int id=1;
//        String path= Constant.IMAGE_PATH;
//        HashMap<String,String> pictrueMap= PictureManager.getPictureInfo(id,path);
//        String picture=pictrueMap.get("picture");
//        System.out.println(picture);
//        byte[] image= Base64.decode(picture);
//        FileOutputStream outputStream=new FileOutputStream("/home/roselone/test7.jpg");
//        outputStream.write(image);
//        outputStream.close();
//    }
//    @Test
//    public void addPictureTest() throws IOException, SQLException, JSONException, TextFormatException {
//        File image =new File("/home/roselone/test3.jpg");
//        HashMap<String,String> pictureMap=new HashMap<String, String>();
//        pictureMap.put("title","test");
//        pictureMap.put("time","2013-04-05");
//        pictureMap.put("pictureName","gods.jpg");
//        pictureMap.put("userID","16");
//        FileInputStream in=new FileInputStream(image);
//        byte[] imageBuf=new byte[(int)image.length()];
//        in.read(imageBuf);
//        String imageString= Base64.encodeBytes(imageBuf);
//        pictureMap.put("picture",imageString);
//        RecordManager.addPicture(13,pictureMap);
//    }
}
