<!DOCTYPE unspecified PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
	
<?php

	$title = "PHP_CMS";
	echo "<title>$title</title>";
?>

</head>
<body>

<?php
	
	$company_create_link = "forms/f_create_company.php";
	$view_company_link = "forms/f_sel_company.php";

	echo '<h2 style="font-family: arial, helvetica, sans-serif;" >
					Content Management System
				</h2>';
	echo '<div align="left",
					  style="
					  font-family: arial, helvetica, sans-serif; 
					  font-style: sans-serif; 
					  padding-left: 50px;
				">';
	
	echo '<a href="'.$company_create_link.'">
					<span style="color: maroon; ">Create New Company</span>
					</a>';
	echo '<br /><br />';
	
	echo '<a href="'.$view_company_link.'">
					<span style="color: maroon; ">View Company Employees</span>
					</a>';
	echo '<br /><br />';
	
	echo '</div>';
?>


</body>
</html>
