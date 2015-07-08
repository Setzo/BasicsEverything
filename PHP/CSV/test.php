<?php

class Utilities {
	
	public static function writeTableOut($values) {
		
		echo "<br><table>
		<tr>
			<td>Id</td>
			<td>Imie</td>
			<td>Nazwisko</td>
			<td>Wiek</td>
		</tr>";
		
		while ($v = mysqli_fetch_array ($values)) {
		
			echo "<tr>
			<td>{$v['id']}</td>
			<td>{$v['imie']}</td>
			<td>{$v['nazwisko']}</td>
			<td>{$v['wiek']}</td>
			</tr>";
		}
		echo "</table>";
	}
	
	public static function getConnection() {
		
		$host = "localhost";
		$user = "setzo";
		$password = "pswd";
		$db = "letsgophp";
		
		return new mysqli($host, $user, $password, $db);
	}
	
	public static function writeFileOut($file) {
		
		$index = 0;
		
		while (!feof($file)) {
		
			$line = fgets($file);
			$data[$index] = explode(",", $line);
		
			$index++;
		}
		
		fclose($file);
		
		$rows = sizeof($data);
		
		echo "<table>
		<tr>
			<td>Id</td>
			<td>Imie</td>
			<td>Nazwisko</td>
			<td>Wiek</td>
		</tr>";
		
		for($i = 0; $i < $rows; $i++) {
			echo "<tr><td>".implode("</td><td>", $data[$i])."</td></tr>";
		}
		
		echo "</table>";
	}
	
	public static function writeToFile($file, $values) {
		
		foreach ($values as $value) {
			fputcsv($file, $value);
		}
		
		fclose($file);
	}
}

Utilities::writeFileOut(fopen("test.csv", "r"));

$conn = Utilities::getConnection();

$select = "select * from people";

$val = $conn->query($select);

Utilities::writeTableOut($val);

$val = $conn->query($select);

Utilities::writeToFile(fopen('test.csv', 'w'), $val);

?>
