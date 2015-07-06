<?php
$hostname = "localhost";
$username = "root";
$password = "09437";

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
