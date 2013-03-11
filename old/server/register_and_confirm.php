<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');

//全局输出函数
function Output($mysql_link)
{
//修正编码
mysql_query('SET NAMES UTF8');	
//输出所有数据
$query_content = "SELECT * FROM user";
$result = mysql_query($query_content,$mysql_link);
//记录字段数
$cnt = mysql_num_fields($result);
while($row = mysql_fetch_row($result))
{
	for($i = 0;$i < $cnt ;$i++)
		echo $row[$i].' ';
	printf("<br/>");
	echo "cnt = $cnt";
}
}

//设置变量
$request_id = $_POST["ID"];
$request_password = $_POST["PASSWORD"];
$request_sex = $_POST["SEX"];
$request_request = $_POST["REQUEST"];

//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link) 
	or die("Cant't choose a database").mysql_errno().mysql_error();	
//修正编码
mysql_query("SET NAMES utf8");	
//搜索表
$query_content = "SELECT * FROM user WHERE ID = '".$request_id."'";
$result = mysql_query($query_content,$link);

//记录请求id的记录数
$cnt = mysql_num_rows($result);

//判断请求
if($request_request == 'REGISTER')
{
	if($cnt == 1)
		echo "Already have !";
	else
	{
		//修正编码
		mysql_query("SET NAMES utf8");		
		//插入新注册的id password
		$query_content = "INSERT INTO user VALUES('".$request_id."','".
						  $request_password."','".$request_sex."')";        					 								
		$result = mysql_query($query_content,$link)
			or die("Cant't insert a new user！").mysql_errno().mysql_error();	
		echo "Successfully Registered!";
	}
	 Output($link);
	 
}	
elseif($request_request == 'CONFIRM')
{
	if($cnt == 1)
		echo "Already have !";
	else 
		echo "Haven't record!";
	Output($link);
}
else 
	die("Wrong Request!!");
//输出现有的记录

mysql_close($link);
?>