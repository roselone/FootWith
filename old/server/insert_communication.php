<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');

//全局输出函数
function Output($mysql_link)
{
//修正编码
mysql_query('SET NAMES UTF8');	
//输出所有数据
$query_content = "SELECT * FROM sys_info";
$result = mysql_query($query_content,$mysql_link);
//记录字段数
$cnt = mysql_num_fields($result);
while($row = mysql_fetch_row($result))
{
	for($i = 0;$i < $cnt ;$i++)
		echo $row[$i].' ';
	printf("<br/>");
	
}
}

//设置变量
$Type = 	$_POST["Type"];
$ID = 		$_POST["ID"];
$Message =  $_POST["Message"];

//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
//插入sys_info
mysql_query("SET NAMES utf8");	
$query_content = "INSERT INTO communication(Type, ID,Message) 
				  VALUES($Type,'".$ID."','".$Message."')";  
mysql_query('SET NAMES UTF8');					 
mysql_query($query_content,$link)
	or die("Cant't insert into communication！").mysql_errno().mysql_error();
Output($link);
mysql_close($link);
?>