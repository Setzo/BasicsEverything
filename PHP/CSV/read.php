<?php

$index = 0;
$file = fopen("test.csv", "r"); //r - reading

while (!feof($file)) {
	
	$line = fgets($file);
	$data[$index] = explode(",", $line);
	
	$index++;
}

fclose($file);

$rows = sizeof($data);

echo $rows."<br>";

//echo $data[0][0];

for($i = 0; $i < $rows; $i++) {
	
	echo implode(",", $data[$i])."<br>";
}

?>
