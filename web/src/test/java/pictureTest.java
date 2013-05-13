import edu.thu.cslab.footwith.messenger.JSONHelper;
import net.iharder.Base64;
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

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/13/13
 * Time: 9:03 PM
 * To change this template use File | Settings | File Templates.
 */
public class pictureTest {
    private String URL="http://127.0.0.1:12580/web-1.0/picture";
    private HttpPost post=new HttpPost(URL);
//    @Test
//    public void addPictureTest() throws IOException {
//        File image =new File("/home/roselone/test3.jpg");
//        DefaultHttpClient client=new DefaultHttpClient();
//        HashMap<String,String> pictureMap=new HashMap<String, String>();
//        pictureMap.put("title","test");
//        pictureMap.put("time","2013-04-05");
//        pictureMap.put("pictureName","god.jpg");
//        pictureMap.put("userID","16");
//        FileInputStream in=new FileInputStream(image);
//        byte[] imageBuf=new byte[(int)image.length()];
//        in.read(imageBuf);
//        String imageString= Base64.encodeBytes(imageBuf);
//        pictureMap.put("picture",imageString);
//        HashMap<String,String> tmp=new HashMap<String, String>();
//        tmp.put("recordID","13");
//        tmp.put("picture", JSONHelper.getJSONHelperInstance().convertToString(pictureMap));
//        ArrayList<NameValuePair> param=new ArrayList<NameValuePair>();
//        param.add(new BasicNameValuePair("add", JSONHelper.getJSONHelperInstance().convertToString(tmp)));
//        post.setEntity(new UrlEncodedFormEntity(param, HTTP.UTF_8));
//        HttpResponse response=client.execute(post);
//        HttpEntity entity=response.getEntity();
//        String result= EntityUtils.toString(entity);
//        System.out.println(result);
//
//    }
}
