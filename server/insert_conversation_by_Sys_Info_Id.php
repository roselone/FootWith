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
$Message = 		$_POST["Message"];
$Sender = 		$_POST["Sender"];
$Sys_Info_Id = 	$_POST["Sys_Info_Id"];
//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
	

//搜索表中 ID为Sys_Info_Id的记录
$query_content = "SELECT * FROM sys_info WHERE Sys_Info_Id = $Sys_Info_Id"; 
mysql_query('SET NAMES UTF8');
$result = mysql_query($query_content,$link);	
$row = mysql_fetch_array($result,MYSQL_BOTH);

if($row["First_Pointer"] == 0)
{
	//把新纪录插入conversation 中
	$query_content = "INSERT INTO conversation(Sender,Message) 
					  VALUES('".$Sender."','".$Message."') ";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Cant't insert into conversation").mysql_errno().mysql_error();	
	$Pointer = mysql_insert_id();
	
	//更新 sys_info 表的Last_Pointer
	$query_content = "UPDATE sys_info SET Last_Pointer = $Pointer , First_Pointer = $Pointer
					  WHERE Sys_Info_Id = $Sys_Info_Id";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Cant't update sys_info's Last_Pointer OR First_Pointer").mysql_errno().mysql_error();	
		
}
else
{
	//记住上一个记录的ID
	$Last_Pointer = $row["Last_Pointer"];
	//把新纪录插入conversation 中
	$query_content = "INSERT INTO conversation(Sender,Message) 
					  VALUES('".$Sender."','".$Message."') ";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Cant't insert into conversation").mysql_errno().mysql_error();	
	$Pointer = mysql_insert_id();
	
	//更新上一个记录的Next_Pointer
	$query_content = "UPDATE conversation SET Next_Pointer = $Pointer 
					  WHERE Con_Id = $Last_Pointer";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Cant't update conversation's Next_Pointer").mysql_errno().mysql_error();
	//更新 sys_info 表的Last_Pointer
	$query_content = "UPDATE sys_info SET Last_Pointer = $Pointer 
					  WHERE Sys_Info_Id = $Sys_Info_Id";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Cant't update sys_info's Last_Pointer").mysql_errno().mysql_error();		
	
	
}	
	
mysql_close($link);	
?>