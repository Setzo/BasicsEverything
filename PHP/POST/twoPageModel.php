<?php

echo $_POST['name']." ".$_POST['age'];

if(strcmp("Setzo", $_POST['name']) == 0) {
	header("Location: index.php");
}

?>
