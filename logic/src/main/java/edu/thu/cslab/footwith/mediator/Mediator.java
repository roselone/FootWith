package edu.thu.cslab.footwith.mediator;

import edu.thu.cslab.footwith.exception.TextFormatException;
import edu.thu.cslab.footwith.messenger.JSONHelper;
import edu.thu.cslab.footwith.plan_record.*;
import edu.thu.cslab.footwith.site.Site;
import edu.thu.cslab.footwith.site.SiteManager;
import edu.thu.cslab.footwith.user.User;
import edu.thu.cslab.footwith.user.UserManager;
import edu.thu.cslab.footwith.utility.Util;
import org.json.JSONException;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Vector;

/**
 * Created with IntelliJ IDEA.
 * User: cscg
 * Date: 13-3-16
 * Time: 下午6:22
 * To change this template use File | Settings | File Templates.
 */
public class Mediator {

    public  Mediator(){

    }
    public static void addPlanFromForm(String title,int organizer, int groupNumMax, String siteName1, String siteName2, String startTime, String endTime) throws TextFormatException, SQLException, JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        UserManager um = new UserManager();
        SiteManager sm = new SiteManager();
        PlanManager pm = new PlanManager();
        //System.out.println(siteName);
        Site site1 = SiteManager.seleteSite(siteName1);
        Site site2 = SiteManager.seleteSite(siteName2);
        Vector<Integer> vector =  new Vector<Integer>();
        vector.add(site1.getSiteID());
        vector.add(site2.getSiteID());
        String siteIDs = new JSONHelper().convertToString(vector);
        //User user=um.selectUser(organizer);
        Date date_startTime = Date.valueOf(startTime);
        Date date_endTime = Date.valueOf(endTime);
        //int int_organizer = user.getUserID();
        Plan plan = new Plan(title, siteIDs, date_startTime, date_endTime, organizer, 1, groupNumMax );
        PlanManager.addPlan(plan);
    }
    public static void addSiteFromForm(String siteName, String rate, String location) throws SQLException {
        SiteManager sm = new SiteManager();
        int int_rate = Integer.parseInt(rate);
        Site site = new Site(siteName, location ,int_rate);
        SiteManager.addSite(site);
    }
    public static boolean isValid(String username, String passwd) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        User user=UserManager.selectUser(username);
        if (user==null) return false;
        //System.out.println(user.getUserID());
        if (user.checkPasswd(passwd))
            return true;
        else
            return false;
    }
    public static Vector<String> getAllLocations(){
        Vector<String> allLocations = new Vector<String>();
        allLocations.add("北京");
        allLocations.add("浙江");
        allLocations.add("天津");
        allLocations.add("安徽");
        allLocations.add("上海");
        allLocations.add("福建");
        allLocations.add("重庆");
        allLocations.add("江西");
        allLocations.add("香港");
        allLocations.add("山东");
        allLocations.add("澳门");
        allLocations.add("河南");
        allLocations.add("内蒙古");
        allLocations.add("湖北");
        allLocations.add("新疆");
        allLocations.add("湖南");
        allLocations.add("宁夏");
        allLocations.add("广东");
        allLocations.add("西藏");
        allLocations.add("海南");
        allLocations.add("广西");
        allLocations.add("四川");
        allLocations.add("河北");
        allLocations.add("贵州");
        allLocations.add("山西");
        allLocations.add("云南");
        allLocations.add("辽宁");
        allLocations.add("陕西");
        allLocations.add("吉林");
        allLocations.add("甘肃");
        allLocations.add("黑龙江");
        allLocations.add("青海");
        allLocations.add("江苏");
        allLocations.add("台湾");
        return allLocations;
    }
    public static Vector<String> selectSiteNameWithLocation(String location) throws TextFormatException, SQLException {
        Site site = new Site();
        SiteManager sm = new SiteManager();
        site.setLocation(location);
        Vector<String> siteNames = new Vector<String>();
        Vector<Site> sites = SiteManager.selectSite(site);
        for(int i=0;i<sites.size();i++){
            siteNames.add(sites.get(i).getSiteName());
        }
        return siteNames;
    }
    public static Vector<Plan> selectPlanFromForm(String organizer, String siteName, String startTime, String endTime) throws TextFormatException, SQLException, JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Vector<Plan> plans;
        UserManager um = new UserManager();
        SiteManager sm = new SiteManager();
        PlanManager pm = new PlanManager();
        JSONHelper jh = new JSONHelper();
        User user;
        Site site;
        Plan plan = new Plan();
        Date d_startTime = Date.valueOf(startTime);
        Date d_endTime = Date.valueOf(endTime);
        user = UserManager.selectUser(organizer);
        site = SiteManager.seleteSite(siteName);
        plan.setOrganizer(user.getUserID());
        plan.setStartTime(d_startTime);
        plan.setEndTime(d_endTime);

        plans = PlanManager.selectPlan(plan);
        for(int i=0;i<plans.size();i++){
            if(!jh.isContained(plans.get(i).getSiteIDs(), site.getSiteID())){
                plans.remove(i);
            }
        }

        return plans;
    }

    public static boolean addPlan(Plan plan) throws SQLException, TextFormatException, JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        Plan new_plan = PlanManager.addPlan(plan);
        if(new_plan == null){
            return false;
        }

        int organizer = new_plan.getOrganizer();
        int planID = new_plan.getPlanID();

        User user = new User();
        String orig_plans =UserManager.selectUser(organizer).getPlans();
        user.setPlans(new JSONHelper().addToArray(orig_plans, planID));
        UserManager.editUser(organizer, user);

        return true;
    }
    public static boolean addRecord(Record record) throws SQLException, TextFormatException, JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {

        Record new_record = RecordManager.addRecord(record);
        if(new_record == null){
            return false;
        }

        String userIDs = record.getUserIDs();
        Vector<Integer> userIDVector =new JSONHelper().convertToArray(userIDs);

        UserManager um = new UserManager();
        User user;
        JSONHelper jh = new JSONHelper();
        String orig_records;
        for(int i=0;i<userIDVector.size(); i++){
            user = UserManager.selectUser(userIDVector.get(i));
            orig_records = user.getRecords();
            user.setRecords(jh.addToArray(orig_records, new_record.getRecordID()));
            UserManager.editUser(userIDVector.get(i), user);
        }
        return true;
    }
    // Plan
    public static Plan selectPlan(int planID) throws TextFormatException, SQLException {
        return PlanManager.selectPlan(planID);
    }
    public static Vector<Plan> selectPlan(Plan plan) throws TextFormatException, SQLException {
        return PlanManager.selectPlan(plan);
    }
    public static Vector<String> selectPlan(String plan_string) throws TextFormatException, SQLException {
        Vector<String> plans_all_string = new Vector<String>();
        JSONHelper jh = new JSONHelper();
        HashMap<String,String> plan_map = jh.convertToMap(plan_string);
        Vector<Plan> plans = PlanManager.selectPlan(convertMapToPlan(plan_map));
        for(int i=0;i<plans.size();i++){
            plans_all_string.add(jh.convertToString(convertPlanToMap(plans.get(i))));
        }
        return plans_all_string;
    }
    public static void deletePlan(int planID) throws TextFormatException, SQLException {
        PlanManager.deletePlan(planID);
    }
    public static void editPlan(int planID, Plan new_plan) throws TextFormatException, SQLException, JSONException {
        PlanManager.editPlan(planID, new_plan);
    }
    private static HashMap<String,String> convertPlanToMap(Plan plan){
        HashMap<String,String> plan_map = new HashMap<String, String>();
        plan_map.put("planID", String.valueOf(plan.getPlanID()));
        plan_map.put("title", plan.getTitle());
        plan_map.put("siteIDs", plan.getSiteIDs());
        plan_map.put("startTime", plan.getStartTime().toString());
        plan_map.put("endTime", plan.getEndTime().toString());
        plan_map.put("organizer", String.valueOf(plan.getOrganizer()));
        plan_map.put("participants", plan.getParticipants());
        plan_map.put("budget", String.valueOf(plan.getBudget()));
        plan_map.put("groupNum", String.valueOf(plan.getGroupNum()));
        plan_map.put("groupNumMax", String.valueOf(plan.getGroupNumMax()));
        plan_map.put("talkStreamID", String.valueOf(plan.getTalkStreamID()));
        plan_map.put("isDone", String.valueOf(plan.getIsDone()));
        return plan_map;
    }
    private static Plan convertMapToPlan(HashMap<String,String> plan_map){
        String planID = String.valueOf(-1);
        String title="";
        String siteIDs = "";
        String startTime = null;
        String endTime = null;
        String organizer = String.valueOf(-1);
        String participants = "";
        String budget = String.valueOf(-1);
        String groupNum = String.valueOf(-1);
        String groupNumMax = String.valueOf(-1);
        String talkStreamID = String.valueOf(-1);
        String isDone= String.valueOf(false);

        planID = plan_map.get("planID");
        if(planID==null || Util.isEmpty(planID)){
            planID = String.valueOf(-1);
        }
        title = plan_map.get("title");
        if(title==null || Util.isEmpty(title)){
            title = "";
        }
        siteIDs = plan_map.get("siteIDs");
        if(siteIDs==null || Util.isEmpty(siteIDs)){
            siteIDs = "";
        }
        startTime = plan_map.get("startTime");
        if(startTime==null || Util.isEmpty(startTime)){
            startTime = "1970-01-01";
        }
        endTime = plan_map.get("endTime");
        if(endTime==null || Util.isEmpty(endTime)){
            endTime = "1970-01-01";
        }
        organizer = plan_map.get("organizer");
        if(organizer==null || Util.isEmpty(organizer)){
            organizer = String.valueOf(-1);
        }
        participants = plan_map.get("participants");
        if(participants==null || Util.isEmpty(participants)){
            participants = "";
        }
        budget = plan_map.get("budget");
        if(budget==null || Util.isEmpty(budget)){
            budget = String.valueOf(-1);
        }
        groupNum = plan_map.get("groupNum");
        if(groupNum==null || Util.isEmpty(groupNum)){
            groupNum = String.valueOf(-1);
        }
        groupNumMax = plan_map.get("groupNumMax");
        if(groupNumMax==null || Util.isEmpty(groupNumMax)){
            groupNumMax = String.valueOf(-1);
        }
        talkStreamID = plan_map.get("talkStreamID");
        if(talkStreamID==null || Util.isEmpty(talkStreamID)){
            talkStreamID = String.valueOf(-1);
        }
        isDone = plan_map.get("isDone");
        if(isDone==null || Util.isEmpty(isDone)){
            isDone= String.valueOf(false);
        }
        Plan plan = new Plan(Integer.valueOf(planID), title, siteIDs, Date.valueOf(startTime), Date.valueOf(endTime) , Integer.valueOf(organizer) , participants, Integer.valueOf(budget) , Integer.valueOf(groupNum) , Integer.valueOf(groupNumMax), Integer.valueOf(talkStreamID), Boolean.valueOf(isDone));
        return plan;
    }
    public static void editPlan(int planID, String new_plan_string) throws TextFormatException, SQLException, JSONException {
        JSONHelper jh = new JSONHelper();
        HashMap<String,String> new_plan_map = jh.convertToMap(new_plan_string);
        PlanManager.editPlan(planID, convertMapToPlan(new_plan_map));
    }
    public static boolean joinPlan(int userID,int planID) throws TextFormatException, SQLException, JSONException {
        return PlanManager.joinPlan(userID, planID);
    }
    // Record
    public static Record selectRecord(int recordID) throws TextFormatException, SQLException {
        return RecordManager.selectRecord(recordID);
    }
    public static boolean addRecordFromPlan(Plan plan) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return RecordManager.addRecordFromPlan(plan);
    }
    public static boolean addRecordFromPlan(String plan_string) throws JSONException, NoSuchAlgorithmException, UnsupportedEncodingException {
        JSONHelper jh = new JSONHelper();
        HashMap<String,String> plan_map = jh.convertToMap(plan_string);
        return RecordManager.addRecordFromPlan(convertMapToPlan(plan_map));
    }
    public static void deleteRecord(int recordID) throws TextFormatException, SQLException {
        RecordManager.deleteRecord(recordID);
    }
    public static void addJournal(int recordID, Journal journal) throws SQLException, TextFormatException, JSONException {
        RecordManager.addJournal(recordID,journal);
    }
    public static void addPicture(int recordID, Picture picture) throws SQLException, TextFormatException, JSONException {
        RecordManager.addPicture(recordID, picture);
    }
    public static void endRecord(int recordID,Date date){
        RecordManager.endRecord(recordID, date);
    }
    // Site
    public static int addSite(Site site) throws SQLException {
        return SiteManager.addSite(site);
    }
    public static Vector<Site> getAllSite() throws SQLException {
        return SiteManager.getAllSite();
    }
    public static Site seleteSite(String siteName) throws TextFormatException, SQLException {
        return SiteManager.seleteSite(siteName);
    }
    public static Site seleteSite(int siteID) throws TextFormatException, SQLException {
        return SiteManager.seleteSite(siteID);
    }
    public static Vector<Site> selectSite(Site site) throws TextFormatException, SQLException {
        return SiteManager.selectSite(site);
    }
    public static void deleteSite(String siteName) throws TextFormatException, SQLException {
        SiteManager.deleteSite(siteName);
    }
    public static void deleteSite(int siteID) throws TextFormatException, SQLException {
        SiteManager.deleteSite(siteID);
    }
    public static void editSite(String siteName, Site new_site) throws TextFormatException, SQLException {
        SiteManager.editSite(siteName,new_site);
    }
    public static void editSite(int siteID, Site new_site) throws TextFormatException, SQLException {
        SiteManager.editSite(siteID, new_site);
    }
    // User
    public static int addUser(User user) throws TextFormatException, SQLException {
        return UserManager.addUser(user);
    }
    public static User selectUser(String userName) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return UserManager.selectUser(userName);
    }
    public static User selectUser(int userID) throws TextFormatException, SQLException, NoSuchAlgorithmException, UnsupportedEncodingException {
        return UserManager.selectUser(userID);
    }
    public static void deleteUser(String userName) throws TextFormatException, SQLException {
        UserManager.deleteUser(userName);
    }
    public  void deleteUser(int userID) throws TextFormatException, SQLException {
        UserManager.deleteUser(userID);
    }
    public static void editUser(String userName, User new_user) throws TextFormatException, SQLException {
        UserManager.editUser(userName, new_user);
    }
    public static void editUser(int userID, User new_user) throws TextFormatException, SQLException {
        UserManager.editUser(userID, new_user);
    }

}
