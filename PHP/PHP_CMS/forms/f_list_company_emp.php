<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php
	$title = "Employee List";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php
	
	echo "<form method='post' action='f_list_company_emp.php'>
			<select name='order'>
				<option value='"."workers.w_name ASC,"."'>Employee name ASC</option>
				<option value='"."workers.w_name DESC,"."'>Employee name DSC</option>
				<option value='"."workers.w_jobtype ASC,"."'>Employee job ASC</option>
				<option value='"."workers.w_jobtype DESC,"."'>Employee job DSC</option>
				<option value='"."workers.w_salary ASC,"."'>Employee salary ASC</option>
				<option value='"."workers.w_salary DESC,"."'>Employee salary DSC</option>
				<option value='"."companies.c_name ASC,"."'>Company name ASC</option>
				<option value='"."companies.c_name DESC,"."'>Company name DSC</option>
				<option value='"."companies.c_ceo_name ASC,"."'>Company CEO name ASC</option>
				<option value='"."companies.c_ceo_name DESC,"."'>Company CEO name DSC</option>
				<option value='"."companies.revenue ASC,"."'>Company revenue ASC</option>
				<option value='"."companies.revenue DESC,"."'>Company revenue DSC</option>
			</select>
			<input class='submit' type='submit' value='Submit' />
		</form>";
	
	echo "<table>
			<caption>Employee List<caption>
			<tr>
				<td>Name</td>
				<td>Job</td>
				<td>Salary</td>
				<td>Company</td>
				<td>Company CEO</td>
				<td>Company Revenue</td>
			</tr>";
	
	require_once __DIR__.'/../controller/c_database_connection.php';
	
	$conn = Database::getConnection();
	
	if(isset($_POST['order'])) {
		$order = $_POST['order'];
	} else {
		$order = "";
	}
	
	$sql = "SELECT workers.w_name, workers.w_salary, workers.w_jobtype, "
				."companies.c_name, companies.c_ceo_name, companies.revenue "
				."FROM workers "
				."LEFT OUTER JOIN companies ON "
				."workers.w_company_id = companies.id "
				."ORDER BY {$order} workers.w_name ASC, workers.w_salary DESC, workers.w_jobtype ASC";
	
	$values = $conn->query($sql);

	while($v = mysqli_fetch_array($values)) {

		echo "<tr>
				<td>{$v['w_name']}</td>
				<td>{$v['w_jobtype']}</td>
				<td>{$v['w_salary']}</td>
				<td>{$v['c_name']}</td>
				<td>{$v['c_ceo_name']}</td>
				<td>{$v['revenue']}</td>
			</tr>";
	}
	
	echo "</table><br>";
	
	include __DIR__ . '/../controller/proj_name.php';
	echo "<a href='" . $homepage_path . "'>Return to Homepage</a>";
?>

</body>
</html>
