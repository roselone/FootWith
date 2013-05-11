import edu.thu.cslab.footwith.plan_record.PictureManager;
import edu.thu.cslab.footwith.utility.Constant;
import net.iharder.Base64;
import org.junit.Test;

import java.io.FileOutputStream;
import java.io.IOException;
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
    @Test
    public void getPictureTest() throws IOException, SQLException{
        int id=1;
        String path= Constant.IMAGE_PATH;
        HashMap<String,String> pictrueMap= PictureManager.getPictureInfo(id,path);
        String picture=pictrueMap.get("picture");
        System.out.println(picture);
        byte[] image= Base64.decode(picture);
        FileOutputStream outputStream=new FileOutputStream("/home/roselone/test7.jpg");
        outputStream.write(image);
        outputStream.close();
    }
}
