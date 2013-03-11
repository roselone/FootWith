<?php
//使用utf8解析页面
header('content-type;text/html;charset=utf-8');

$Trip_Id = $_POST['Trip_Id'];
$Status = $_POST['Status'];
//连接数据库
$link = mysql_connect('localhost','root','');
//选择数据库
mysql_select_db('banlv',$link)
	or die("Failed to select banlv").mysql_errno().mysql_error();
//修正编码
mysql_query('SET NAMES UTF8');	
//修改数据
$query_content = "UPDATE trip SET Status = '".$Status."' WHERE Trip_Id = $Trip_Id";
mysql_query($query_content,$link)
	or die("Failed to update trip's Status").mysql_errno().mysql_error();
echo "SUCESS";


?>