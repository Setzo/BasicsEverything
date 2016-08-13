<?php

$ar = $_GET['name'];	//space = %20

echo $ar;

echo "<form action='twoPageModel.php' method='post'>
		Enter Name
		<input type='text' name ='name'/>
		Enter Age
		<input type='text' name ='age'/>
		<br>
		<input type='submit' />
	</form>";

?>
