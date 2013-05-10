package edu.thu.cslab.footwith.AOPSupport;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/24/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public aspect AspectTest {
    Logger logger = LogManager.getLogger("TestAspect");
    pointcut doLog():call(void edu.thu.cslab.footwith.form.MainWindow.initMainFrame());

    before() : doLog() {
        logger.error("fuck AOP");
    }
}
