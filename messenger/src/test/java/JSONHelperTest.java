import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.utility.Util;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONException;
import org.junit.Test;

import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/16/13
 * Time: 1:44 AM
 * To change this template use File | Settings | File Templates.
 */
public class JSONHelperTest {
    private JSONHelper jsonHelper=JSONHelper.getJSONHelperInstance();
    private Logger logger= LogManager.getLogger(this.getClass().getName());
    @Test
    public void convertToStringTest(){
        Vector<Integer> tmp=new Vector<Integer>();
        tmp.add(123);
        tmp.add(2143);
        tmp.add(213);
        System.out.println(jsonHelper.convertToString(tmp));
    }
    @Test
    public void convertToArrayTest() throws JSONException {
        String tmp="[123,2143,213]";
        Vector<Integer> result=jsonHelper.convertToArray(tmp);
        for (int i=0;i<result.size();i++)
            System.out.println(result.get(i));
    }
    @Test
    public void addTest() throws JSONException {
        String tmp= "[123,2143,213]";
        int one=222;
        System.out.println(jsonHelper.addToArray(tmp,one));
    }
    @Test
    public void deleteTest() throws JSONException {
        String tmp ="[123,2143,213,222]";
        int one=123;
        System.out.println(jsonHelper.deleteFromArray(tmp,one));
    }
    @Test
    public void mapTest(){
        HashMap<String,String> map=new HashMap<String, String>();
        map.put("1",Util.string2Json("1"));
        map.put("2","2");
        map.put("3","3");
        String s=jsonHelper.convertToString(map);
        logger.debug(s);
        map=jsonHelper.convertToMap(s);
        logger.debug(map.toString());
    }

    @Test
    public void map2Test(){
        HashMap<String,byte[]> map=new HashMap<String, byte[]>();
        map.put("1","1".getBytes());
        map.put("2","2".getBytes());
        map.put("3","3".getBytes());
        String s=jsonHelper.convertToString2(map);
        logger.debug("2:{}",s);
        map=jsonHelper.convertToMap2(s);
        logger.debug("2:{}",map.toString());
    }

}
