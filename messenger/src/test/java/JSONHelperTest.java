import edu.thu.cslab.footwith.messenger.JSONHelper;
import org.json.JSONException;
import org.junit.Test;

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
}
