<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	updateIng();
}

function updateIng()
{
	global $connect;

  $id = $_POST["ingredientid"];

  $query = " Update ingredients SET heldAmt = heldAmt+1.0  WHERE ingredientid ='$id'; ";
	mysqli_query($connect, $query) or die (mysqli_error($connect));
	mysqli_close($connect);
}
?>
