<?php

require_once 'connect.php';

$ar = $mysqli->query("SELECT workers.id,
							workers.name,
							workers.surname,
							companies.cname
					FROM workers
					LEFT OUTER JOIN companies ON
					workers.company = companies.id");

echo "<table>
		<tr>
			<td>ID</td>
			<td>Name</td>
			<td>Surname</td>
			<td>Company</td>
		</tr>";

while($arr = mysqli_fetch_array($ar, MYSQL_ASSOC)) {
	
	echo "
		<tr>
			<td>{$arr['id']}</td>
			<td>{$arr['name']}</td>
			<td>{$arr['surname']}</td>
			<td>{$arr['cname']}</td>
		</tr>";
}

echo "</table>"


?>
