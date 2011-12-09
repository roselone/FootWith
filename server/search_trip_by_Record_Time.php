<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');
//修正编码
mysql_query('SET NAMES UTF8');	
$Add_Day =   $_POST["Add_Date"]; 
$Base_Date = strtotime($_POST["Base_Date"]);
$End_Date = date("Y-m-d H:i:s",mktime(0,0,0,date("m",$Base_Date),
			      date("d",$Base_Date)+$Add_Day,date("Y",$Base_Date)));
$Base_Date = $_POST["Base_Date"];				 
if($Add_Day < 0)				
{
	$Base_Date = $End_Date;
	$End_Date =  $_POST["Base_Date"];
}
//连接服务器
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link)
	or die("Cant't choose a database").mysql_errno().mysql_error();	
//修正编码
mysql_query('SET NAMES UTF8');	
//进行搜索请求
$query_content = "SElECT * FROM trip WHERE UNIX_TIMESTAMP( Record_Time )>
				  UNIX_TIMESTAMP('".$Base_Date."') AND UNIX_TIMESTAMP( Record_Time )<
				  UNIX_TIMESTAMP('".$End_Date."')";
$result = mysql_query($query_content,$link);
//记录字段数
$cnt = mysql_num_fields($result);
while($row = mysql_fetch_array($result,MYSQL_BOTH))
{
	for($i = 0; $i< $cnt ;$i++)
	printf(" %s  ",$row[$i]);
	printf("<br/>");
}
?>