
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<form action="register_and_confirm.php" method="post">
Enter your name: <input type="text" name="ID" />
<br>Enter your password: <input type="text" name="PASSWORD" /></br>
Enter your sex: <input type="text" name="SEX" />
<br>Enter your request: <input type="text" name="REQUEST" /></br>
<input type="submit" />
</form>


<td>insert_trip</td>
<form action="insert_trip.php" method="post">
Enter your ID: <input type="text" name="ID" />
<br>Enter your Time_Start: <input type="text" name="Time_Start" /></br>
Enter your Time_End: <input type="text" name="Time_End" />
<br>Enter your Plan: <input type="text" name="Plan" /></br>
<br>Enter your Message: <input type="text" name="Message" /></br>
<input type="submit" />
</form>
<td>insert_trip_joint</td>
<form action="insert_trip_joint.php" method="post">
Enter your Trip_Id: <input type="int" name="Trip_Id" />
<br>Enter your ID: <input type="text" name="ID" /></br>

<input type="submit" />
</form>
<td>insert_trip_interested</td>
<form action="insert_trip_interested.php" method="post">
Enter your Trip_Id: <input type="int" name="Trip_Id" />
<br>Enter your ID: <input type="text" name="ID" /></br>

<input type="submit" />
</form>
<td>search_trip_by_Trip_Id</td>
<form action="search_trip_by_Trip_Id.php" method="post">
<br>Enter Trip_Id you search for: <input type="int" name="Trip_Id" /></br>
<input type="submit" />
</form>

<td>search_trip_by_Plan</td>
<form action="search_trip_by_Plan.php" method="post">
<br>Enter your Plan: <input type="text" name="Plan" /></br>
<br>Enter your Time_Start: <input type="int" name="Time_Start" /></br>
<br>Enter your Time_End: <input type="int" name="Time_End" /></br>
<input type="submit" />
</form>

<td>search_trip_joint_interested_by_Trip_Id</td>
<form action="search_trip_joint_interested_by_Trip_Id.php" method="post">
<br>Enter Trip_Id you search for: <input type="int" name="Trip_Id" /></br>
<input type="submit" />
</form>


<td>search_trip_by_Record_Time</td>
<form action="search_trip_by_Record_Time.php" method="post">
<br>Enter Add_Date : <input type="int" name="Add_Date" /></br>
<br>Enter Base_Date : <input type="text" name="Base_Date" /></br>
<input type="submit" />
</form>

<td>change_status</td>
<form action="change_status.php" method="post">
Enter your Trip_Id: <input type="text" name="Trip_Id" />
<br>Enter your Status: <input type="text" name="Status" /></br>
<input type="submit" />
</form>



