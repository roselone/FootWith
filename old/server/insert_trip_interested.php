<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');


//设置变量
$Trip_Id = 	$_POST["Trip_Id"];
$ID = 		$_POST["ID"];

//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
//修正编码
mysql_query('SET NAMES UTF8');	
//把新记录插入trip_interested表中
$query_content = "INSERT INTO trip_interested(Trip_Id,ID)
				  VALUES($Trip_Id,'".$ID."')";
mysql_query($query_content,$link)
	or die("Cant't insert into trip_interested").mysql_errno().mysql_error();	
//修正编码
mysql_query('SET NAMES UTF8');	
//罗列表trip_interested
$query_content = "SELECT * FROM trip_interested";
$result = mysql_query($query_content,$link);
//记录字段数
$cnt = mysql_num_fields($result);
while($row = mysql_fetch_row($result))
{
	for($i = 0; $i <$cnt ;$i++)
		echo $row[$i].' ';
}				 
mysql_close($link);
?>