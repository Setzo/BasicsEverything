<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php
	$title = "Create Company";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php

	include __DIR__.'/../controller/proj_name.php';

	if(!isset($_POST['c_name']) && !isset($_POST['c_ceo_name']) && !isset($_POST['revenue'])) {
	
		include __DIR__.'/../forms/pure/f_pure_create_company.php';
	}
	
	elseif ($_POST['c_name'] == "" || $_POST['c_ceo_name'] == "" || $_POST['revenue'] == ""){
		
		echo "One of the fields is empty<br><br>";
		include __DIR__.'/../forms/pure/f_pure_create_company.php';
	}
	
	else {
		
		require_once __DIR__.'/../controller/c_database_connection.php';
		
		$conn = Database::getConnection();
		
		$sql_insert = 'INSERT INTO companies '.
				       '(c_name, c_ceo_name, revenue) '.
				       'VALUES (\''.$_POST['c_name'].'\',\''.$_POST['c_ceo_name'].'\','.intval($_POST['revenue']).')';
		
		header("Location: /$project_name/index.php");
	}
	
	
	echo "<a href='".$homepage_path."'>Return to Homepage</a>";

?>


</body>
</html>
