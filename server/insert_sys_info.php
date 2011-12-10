<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');


//设置变量
$Message = 		$_POST["Message"];
$Type = 		$_POST["Type"];
if ($Type == 1)
{
	$Symbol = 		$_POST["Symbol"];
	$Users_Id =		$_POST["Users_Id"];
	$Trip_Id = 		$_POST["Trip_Id"];
}
//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
	
if($Type == 0)
{
	//插入sys_info
	echo $Message.' '.$Type;
	mysql_query("SET NAMES utf8");	
	$query_content = "INSERT INTO sys_info(Message, Type) 
				  VALUES('".$Message."',$Type)";  
	mysql_query('SET NAMES UTF8');					 
	mysql_query($query_content,$link)
		or die("Cant't insert into sys_info！").mysql_errno().mysql_error();
}
else
{
	//插入sys_info
	mysql_query("SET NAMES utf8");	
	$query_content = "INSERT INTO sys_info(Message, Type,Users_Id,Trip_Id) 
				  VALUES('".$Message."',$Type,'".$Users_Id."',$Trip_Id)";  
	mysql_query('SET NAMES UTF8');					 
	mysql_query($query_content,$link)
		or die("Cant't insert into sys_info！").mysql_errno().mysql_error();
	//记录此id值
	$Sys_Info_Id = mysql_insert_id();
	// 从 $Users_Id中得到各个ID
	$len = strlen($Users_Id);
	echo "Sys_Info_Id = $Sys_Info_Id";
	for($i = 0 ; $i < $len ;$i++)
	{
		$ID = '';
		while($i < $len && $Users_Id[$i]!= $Symbol)
		{
			if ($Users_Id[$i]  != $Symbol)
			{
				echo $Users_Id[$i].'------------'.$Symbol."<br/>";
			}
			$ID = $ID.$Users_Id[$i];
			
			$i++;
		}
		echo "i = $i  ID = $ID"."<br/>";
		//插入communication表
		mysql_query("SET NAMES utf8");	
		$query_content = "INSERT INTO communication(Message, Type,ID,Trip_Id) 
						  VALUES($Sys_Info_Id,$Type,'".$ID."',$Trip_Id)";  
		mysql_query('SET NAMES UTF8');					 
		mysql_query($query_content,$link)
			or die("Cant't insert into communication！").mysql_errno().mysql_error();
	
	}
}		
mysql_close($link);	
?>