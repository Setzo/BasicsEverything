<?php
$hostname = "localhost";
$username = "setzo";
$password = "pswd";

$databaseName = "letsgophp";

$dbConnected = mysql_connect ($hostname, $username, $password);

$dbSelected = mysql_select_db ($databaseName, $dbConnected);

if ($dbConnected) {
	echo "Connected to MySQL<br>";
	
	if ($dbSelected) {
		echo "Connected to DB<br>";
	} else {
		echo "Not connected to DB<br>";
	}
} else {
	echo "Not connected to MySQL<br>";
}

$select = "select * from people";
$val = mysql_query ($select, $dbConnected);

//$insert = "insert into people (imie, nazwisko, wiek) values ('Rakk', 'Test', 6)";
//mysql_query($insert, $dbConnected);

//$delete = "delete from people where id = 5";
//mysql_query($delete, $dbConnected);

$update = "update people set imie='Setzo' where id=3";
mysql_query($update, $dbConnected);

echo "<br><table> 
		<tr>
			<td>Id</td>
			<td>Imie</td>
			<td>Nazwisko</td>
			<td>Wiek</td>
		</tr>";

while ($v = mysql_fetch_array ($val, MYSQL_ASSOC)) {
	
	echo "<tr>
			<td>{$v['id']}</td>
			<td>{$v['imie']}</td>
			<td>{$v['nazwisko']}</td>
			<td>{$v['wiek']}</td>
		</tr>";
}

echo "</table>";

?>
