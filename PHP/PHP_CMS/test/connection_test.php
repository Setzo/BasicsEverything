<?php

require_once __DIR__.'/../controller/c_database_connection.php';
//require_once __DIR__.'/../controller/sec_c_db_config/sec_c_db_config.php';

//$conn = new mysqli($db_info['hostname'], $db_info['username'], $db_info['password'], $db_info['database_name']);

$conn = Database::getConnection();

//$values = $conn->query("SELECT * FROM workers");

$values = $conn->query("SELECT c_name FROM companies");

echo "<br><table>
		<tr>
			<td>Id</td>
			<td>Name</td>
			<td>CEO</td>
			<td>Rev</td>
		</tr>";
		
while ($v = mysqli_fetch_array ($values, MYSQL_ASSOC)) {

	echo "<tr>
		<td>{$v['id']}</td>
		<td>{$v['c_name']}</td>
		<td>{$v['c_ceo_name']}</td>
		<td>{$v['revenue']}</td>
	</tr>";
}
echo "</table>";



?>
