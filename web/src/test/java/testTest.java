import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/9/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class testTest {
    private String URL="http://127.0.0.1:12580/web-1.0/test";
    private HttpPost post=new HttpPost(URL);
    @Test
    public void doPost() throws IOException {
        DefaultHttpClient client=new DefaultHttpClient();

        HttpResponse response=client.execute(post);
        HttpEntity entity=response.getEntity();
        String tmps= EntityUtils.toString(entity);
        HashMap<String,String> map=JSONHelper.getJSONHelperInstance().convertToMap(tmps);
        String jpg=map.get("test.jpg");
        byte[] image= Base64.decodeBase64(jpg);
//        BufferedImage images= ImageIO.read(new ByteArrayInputStream(image));
//        ImageIO.write(images,"jpg",new File("/home/roselone/test2.jpg"));
        FileOutputStream outputStream=new FileOutputStream("/home/roselone/test3.jpg");
        outputStream.write(image);
        outputStream.close();

       // System.out.println(map.get("test.jpg").getBytes());

    }
}
