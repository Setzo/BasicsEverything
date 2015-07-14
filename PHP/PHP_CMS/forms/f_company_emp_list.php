<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php
	$title = "Company Information";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php
	
	$edit_employee_record_link = "f_edit_emp.php";

	require_once __DIR__.'/../controller/c_database_connection.php';
	
	$cmpny = $_POST['company'];
	
	$conn = Database::getConnection();
	
	$company_info_query = "SELECT * FROM companies WHERE id=".$cmpny."";
	$company_emp_query = "SELECT * FROM workers WHERE w_company_id=".$cmpny."";
	
	echo '<table>
				<tr>
					<td>Company Name</td>
					<td>Company CEO Name</td>
					<td>Company Revenue</td>
				</tr>';

	$c_info_values = $conn->query($company_info_query);
	
	$cmpny_name;
	
	while($v = mysqli_fetch_array($c_info_values, MYSQLI_ASSOC)) {
		
		echo "<tr>
					<td>{$v['c_name']}</td>
					<td>{$v['c_ceo_name']}</td>
					<td>{$v['revenue']}</td>
				</tr>";
		$cmpny_name = $v['c_name'];
	}
	
	echo '<table><br><br>';
	
	
	//////////////////////////////////////////////////////////////////////////////////////////////
	
	$c_emp_list_values = $conn->query($company_emp_query);
	
	$cnt = 0;
	
	echo '<table>
				<caption>'.$cmpny_name.' Employee List</caption>
				<tr>
					<td>Name</td>
					<td>Job</td>
					<td>Salary</td>
					<td>Edit</td>
				</tr>';
	
	while($v = mysqli_fetch_array($c_emp_list_values, MYSQLI_ASSOC)) {
				
		$cnt++;
		echo "<tr>
					<td>{$v['w_name']}</td>
					<td>{$v['w_jobtype']}</td>
					<td>{$v['w_salary']}</td>
					<td><a href='{$edit_employee_record_link}?id={$v['w_id']}'>Edit</a></td>
				</tr>";
	}
	
	echo "<table>";
	
	echo "<br><br>&nbspCompany has $cnt employees.<br><br>";
	
	include __DIR__.'/../controller/proj_name.php';
	echo "<a href='".$homepage_path."'>Return to Homepage</a>";
	
?>


</body>
</html>
