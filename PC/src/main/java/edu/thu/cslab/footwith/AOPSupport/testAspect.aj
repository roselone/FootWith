package edu.thu.cslab.footwith.AOPSupport;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/24/13
 * Time: 2:17 PM
 * To change this template use File | Settings | File Templates.
 */
public aspect testAspect {
    pointcut doLog():call(void edu.thu.cslab.footwith.form.MainWindow.initMainFrame());

    before() : doLog() {
        System.out.println("FUCK AOP!");
    }
}
