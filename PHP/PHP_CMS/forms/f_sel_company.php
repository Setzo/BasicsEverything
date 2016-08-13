<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php
	$title = "Select Company";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php

	$act_link = "f_company_emp_list.php";

	require_once __DIR__.'/../controller/c_database_connection.php';

	$conn = Database::getConnection();
	
	$select = "SELECT id, c_name FROM companies";
	
	$values = $conn->query($select);
	
	if(isset($_GET['err']) && $_GET['err'] == "npe") {
	
		echo "<strong>Employee ID out of reach. Please select an existing employee.</strong><br><br>";
	}
	
	echo '<form action="'.$act_link.'" method="post" >';
	
	echo "<select name='company'>";
	
	while ($v = mysqli_fetch_array ($values, MYSQL_ASSOC)) {
		
		echo "<option value='".$v['id']."'>".$v['c_name']."</option>";
	}
	
	echo '<input class="submit" type="submit" name="submit" value="Submit"/>';
	
	echo "</form>";
	
	include __DIR__.'/../controller/proj_name.php';
	echo "<a href='".$homepage_path."'>Return to Homepage</a>";
?>


</body>
</html>
