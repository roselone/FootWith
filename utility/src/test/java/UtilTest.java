import edu.thu.cslab.footwith.utility.Util;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 5/2/13
 * Time: 3:02 PM
 * To change this template use File | Settings | File Templates.
 */
public class UtilTest {
    @Test
    public void string2JsonTest(){
        String buf="bab=";
        buf= Util.string2Json(buf);
        System.out.println(buf);
    }
}
