<?php
header('content-type;text/html;charset=utf-8');
//全局输出函数
function Output($mysql_link,$Table)
{
//修正编码
mysql_query('SET NAMES UTF8');	
//输出所有数据
$query_content = "SELECT * FROM $Table";
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

//赋值变量
$Sender = $_POST["Sender"];
$Reciever = $_POST["Reciever"];

//连接本地服务器
$link = mysql_connect('localhost','root','');
if(!$link)
	die('Can\'t connect mysql!').mysql_errno().mysql_error();
//选择数据库
mysql_select_db('banlv',$link)
	or die("Can't select database!").mysql_errno().mysql_error();
	
//看是否在customer上
$query_content = "SELECT * FROM customer WHERE Sender = '".$Sender."' 
				  AND Reciever = '".$Reciever."'";
//修正编码
mysql_query('SET NAMES UTF8');	
//执行
$result = mysql_query($query_content,$link);
$cnt = mysql_num_rows($result);
if($cnt == 0)
{
	echo "NO RECORD!";	
}
else 
{
	$row = mysql_fetch_array($result,MYSQL_BOTH);
	$Next_Message_Con_Id = $row["First_Pointer"];
	
	do
	{
		$query_content = " SELECT * FROM conversation WHERE Con_Id = $Next_Message_Con_Id";
		mysql_query('SET NAMES UTF8');	
		$result = mysql_query($query_content,$link);
		$row = mysql_fetch_array($result,MYSQL_BOTH);
		$Next_Message_Con_Id = $row["Next_Pointer"];
		echo $row["Sender"]."说：". $row["Message"]."    ".$row["Record_Time"]."<br/>";
	}while($Next_Message_Con_Id != 0);
}

?>