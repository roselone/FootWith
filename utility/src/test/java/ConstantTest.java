import edu.thu.cslab.footwith.utility.Constant;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 4/5/13
 * Time: 3:22 PM
 * To change this template use File | Settings | File Templates.
 */
public class ConstantTest {
    @Test
    public void getConstantTest(){
        System.out.println(Constant.getInstantce().getProperty("PROJECT_PATH"));
    }
}
