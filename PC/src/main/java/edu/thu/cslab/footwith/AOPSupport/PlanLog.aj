package edu.thu.cslab.footwith.AOPSupport;

import edu.thu.cslab.footwith.server.Plan;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/30/13
 * Time: 10:00 PM
 * To change this template use File | Settings | File Templates.
 */
public aspect PlanLog {
    Logger logger = LogManager.getLogger(this.getClass().getName());

    before(Plan plan):call(* edu.thu.cslab.footwith.server.PlanManager.addPlan(edu.thu.cslab.footwith.server.Plan)) && args(plan){
        logger.info("add plan : {}",plan.getTitle());
    }

    before(int planID):call(* edu.thu.cslab.footwith.server.PlanManager.deletePlan(int)) && args(planID){
        logger.info("delete plan : {}",planID);
    }

    before(int planID,Plan plan):call(* edu.thu.cslab.footwith.server.PlanManager.editPlan(int,edu.thu.cslab.footwith.server.Plan)) && args(planID,plan){
        logger.info("modify plan : {}",planID);
        if (plan.getTitle() != null){
            logger.info("    change title to {}",plan.getTitle());
        }
        if (plan.getStartTime()!=null){
            logger.info("    change start time to {}",plan.getStartTime().toString());
        }
        if (plan.getEndTime()!=null){
            logger.info("    change end time to {}",plan.getEndTime().toString());
        }
        if (plan.getBudget()!=-1){
            logger.info("    change budget to {}",plan.getBudget());
        }
    }
}
