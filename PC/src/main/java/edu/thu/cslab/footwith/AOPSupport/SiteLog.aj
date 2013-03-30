package edu.thu.cslab.footwith.AOPSupport;

import edu.thu.cslab.footwith.server.Site;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

/**
 * Created with IntelliJ IDEA.
 * User: roselone
 * Date: 3/30/13
 * Time: 5:04 PM
 * To change this template use File | Settings | File Templates.
 */
public aspect SiteLog {
    Logger logger = LogManager.getLogger(this.getClass().getName());

    before(Site site):call(* edu.thu.cslab.footwith.server.SiteManager.addSite(edu.thu.cslab.footwith.server.Site)) && args(site){
        logger.info("add site : {}",site.getSiteName());
    }

    before(int siteID):call(* edu.thu.cslab.footwith.server.SiteManager.deleteSite(int)) && args(siteID){
        logger.info("delete site : {}",siteID);
    }

    before(int siteID,Site site):call(* edu.thu.cslab.footwith.server.SiteManager.editSite(int,edu.thu.cslab.footwith.server.Site)) && args(siteID,site){
        logger.info("modify site : {}",siteID);
        if (site.getSiteName()!=null){
            logger.info("    change siteName to {}",site.getSiteName());
        }
        if (site.getLocation()!=null){
            logger.info("    change location to {}",site.getLocation());
        }
        if (site.getRate()!=-1){
            logger.info("    change rate to {}",site.getRate());
        }
        if (site.getBrief()!=null){
            logger.info("    change brief to {}",site.getBrief());
        }
    }

}
