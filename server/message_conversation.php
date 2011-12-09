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
$Message = $_POST["Message"];

//连接本地服务器
$link = mysql_connect('localhost','root','');
if(!$link)
	die('Can\'t connect mysql!').mysql_errno().mysql_error();
//选择数据库
mysql_select_db('banlv',$link)
	or die("Can't select database!").mysql_errno().mysql_error();
	
//看是否在customer上
$query_content = "SELECT * FROM customer WHERE Sender = '".$Sender."' 
				  AND Reciever = '".$Reciever."' OR Sender = '".$Reciever."' 
				  AND Reciever = '".$Sender."'";
//修正编码
mysql_query('SET NAMES UTF8');	
//执行
$result = mysql_query($query_content,$link);
$cnt = mysql_num_rows($result);
if($cnt == 0)
{
	//插入 conversation 中
	$query_content = "INSERT INTO conversation(Sender,Reciever,Message) 
					  VALUES('".$Sender."','".$Reciever."','".$Message."')";
	mysql_query('SET NAMES UTF8');	
	mysql_query($query_content,$link)
	or die("Can't insert to conversation!").mysql_errno().mysql_error();
	$Pointer = mysql_insert_id();

	//把AB插入 customer 中
	$query_content = "INSERT INTO customer(Sender,Reciever,First_Pointer,Last_Pointer) 
					  VALUES('".$Sender."','".$Reciever."',$Pointer,$Pointer)";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Can't insert to customer!").mysql_errno().mysql_error();
		
	//把BA插入 customer 中
	$query_content = "INSERT INTO customer(Sender,Reciever,First_Pointer,Last_Pointer) 
					  VALUES('".$Reciever."','".$Sender."',$Pointer,$Pointer)";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Can't insert to customer!").mysql_errno().mysql_error();	
}
else 
{
	$row = mysql_fetch_array($result,MYSQL_BOTH);
	$One_Customer_Id = $row["Customer_Id"];
	
	$row = mysql_fetch_array($result,MYSQL_BOTH);
	$Other_Customer_Id = $row["Customer_Id"];
	$Con_Id = $row["Last_Pointer"];
	
	//插入 conversation 中
	$query_content = "INSERT INTO conversation(Sender,Reciever,Message) 
					  VALUES('".$Sender."','".$Reciever."','".$Message."')";
	mysql_query('SET NAMES UTF8');	
	mysql_query($query_content,$link)
	or die("Can't insert to conversation!").mysql_errno().mysql_error();
	//记录这次插入的ID
	$Pointer = mysql_insert_id();
	
	//更新上一次记录的Con_Id
	$query_content = "UPDATE conversation SET  Next_Pointer = $Pointer WHERE Con_Id = $Con_Id";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Can't UPDATE conversation!").mysql_errno().mysql_error();
	//更新AB的Last_Pointer
	$query_content = "UPDATE customer SET  Last_Pointer = $Pointer WHERE
					  Customer_Id = $One_Customer_Id OR Customer_Id = $Other_Customer_Id ";
	mysql_query('SET NAMES UTF8');
	mysql_query($query_content,$link)
		or die("Can't UPDATE customer!").mysql_errno().mysql_error();
}
$Table = 'customer';
Output($link,$Table);
$Table = 'conversation';
Output($link,$Table);



?>