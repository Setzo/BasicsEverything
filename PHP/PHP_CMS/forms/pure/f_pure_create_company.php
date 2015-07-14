<?php

echo '<form action="f_create_company.php" method="post">
		
			Company name:<br>
			<input type="text" name="c_name" value="'.$_POST['c_name'].'" />
	
			<br>
			Company CEO name:<br>
			<input type="text" name="c_ceo_name" value="'.$_POST['c_ceo_name'].'" />
	
			<br>
			Company revenue:<br>
			<input type="text" name="revenue" value="'.$_POST['revenue'].'" />
	
			<br><br>
			<input type="submit" value="Submit" />
	
		</form>';
?>
