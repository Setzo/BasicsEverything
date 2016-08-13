<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php
	$title = "Edit Employee Record";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php

require_once __DIR__.'/../controller/c_database_connection.php';

$is_done = false;

$conn = Database::getConnection();

if(isset($_POST['w_name']) && isset($_POST['w_jobtype']) && isset($_POST['w_salary']) && isset($_POST['company'])) {
	
	$is_done = true;
	
	$update_emp_info_query = "UPDATE workers SET w_name='{$_POST['w_name']}', w_jobtype='{$_POST['w_jobtype']}', w_salary=".intval($_POST['w_salary'])." WHERE w_id={$_POST['w_id']}";

	$update_emp_info_query_values = $conn->query($update_emp_info_query);
}

$emp_info_query = "SELECT w_name, w_jobtype, w_salary, w_company_id FROM workers WHERE w_id={$_GET['id']}";

$emp_info_query_values = $conn->query($emp_info_query);

$v = mysqli_fetch_array($emp_info_query_values, MYSQL_ASSOC);

//if(!isset($v)) {
//	
//	header('Location: f_sel_company.php?err=npe');
//}


if(!$is_done) {
	
	echo '<form action="f_edit_emp.php" method="post">
	
				Employee name:<br>
				<input type="text" name="w_name" value="'.$v['w_name'].'" />
	
				<br>
				Job:<br>
				<input type="text" name="w_jobtype" value="'.$v['w_jobtype'].'" />
	
				<br>
				Salary:<br>
				<input type="text" name="w_salary" value="'.$v['w_salary'].'" />
						
				<input type="hidden" name="company" value="'.$v['w_company_id'].'"/>
				<input type="hidden" name="w_id" value="'.$_GET['id'].'"/>
						
				<br><br>
				<input type="submit" value="Submit" />
	
			</form>';
} else {
	
	echo '<form action="f_company_emp_list.php" method="post">
	
				<input type="hidden" name="company" value="'.$_POST['company'].'"/>
	
				<input type="submit" value="Back" />
	
			</form>';
}

include __DIR__.'/../controller/proj_name.php';
echo "<a href='".$homepage_path."'>Return to Homepage</a>";

?>


</body>
</html>
