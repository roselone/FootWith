<?php
header('content-type;text/html;charset=utf-8');
//修正编码
mysql_query('SET NAMES UTF8');	
//设置变量
$ID = 			$_POST["ID"];
$Time_Start = 	$_POST["Time_Start"];
$Time_End = 	$_POST["Time_End"];
$Plan = 		$_POST["Plan"];
$Message = 		$_POST["Message"];

//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
//修正编码
mysql_query('SET NAMES UTF8');	
//把新记录插入trip表中
mysql_query('SET NAMES UTF8');
$query_content = "INSERT INTO trip(ID,Time_Start,Time_End,Plan,Message)
				  VALUES('".$ID."',$Time_Start,$Time_End,'".$Plan."','".$Message."')";
mysql_query($query_content,$link)
	or die("Cant't insert into trip").mysql_errno().mysql_error();	
//修正编码
mysql_query('SET NAMES UTF8');	
//罗列表trip
$query_content = "SELECT * FROM trip";
$result = mysql_query($query_content,$link);
//记录字段数
$cnt = mysql_num_fields($result);
while($row = mysql_fetch_row($result))
{
	for($i = 1; $i <$cnt ;$i++)
		echo $row[$i].' ';
}				 
mysql_close($link);
?>