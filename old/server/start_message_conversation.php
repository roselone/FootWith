<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<td>Messaging!!!</td>
<form action="message_conversation.php" method="post">
Enter Sender: <input type="text" name="Sender" />
<br>Enter Reciever: <input type="text" name="Reciever" /></br>
Enter Message: <input type="text" name="Message" />
<input type="submit" />
</form>


<td>Find The Record</td>
<form action="search_message_conversation.php" method="post">
Enter Sender: <input type="text" name="Sender" />
<br>Enter Reciever: <input type="text" name="Reciever" /></br>
<input type="submit" />
</form>


<td>search_trip_by_ID</td>
<form action="search_trip_by_ID.php" method="post">
Enter ID: <input type="text" name="ID" />
<input type="submit" />
</form>


<td>insert_sys_info</td>
<form action="insert_sys_info.php" method="post">
<br>Enter Message: <input type="text" name="Message" /></br>
<br>Enter Type: <input type="int" name="Type" /></br>
<td>if Type == 0 you don't need to sign below</td>

<br>Enter Symbol: <input type="text" name="Symbol" /></br>
<br>Enter Users_Id: <input type="text" name="Users_Id" /></br>
<br>Enter Trip_Id: <input type="int" name="Trip_Id" /></br>

<input type="submit" />
</form>

<td>insert_communication</td>
<form action="insert_communication.php" method="post">
Enter ID: <input type="text" name="ID" />
<br>Enter Type: <input type="int" name="Type" /></br>

<input type="submit" />
</form>


<td>insert_conversation_by_Sys_Info_Id</td>
<form action="insert_conversation_by_Sys_Info_Id.php" method="post">
Enter Sys_Info_Id: <input type="int" name="Sys_Info_Id" />
<br>Enter Sender: <input type="text" name="Sender" /></br>
<br>Enter Message: <input type="text" name="Message" /></br>
<input type="submit" />
</form>
