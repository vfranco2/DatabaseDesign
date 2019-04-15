<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	createIngredient();
}

function createIngredient()
{
	global $connect;

	$ingredientName = $_POST["ingredientName"];
	$ingredientLink = $_POST["ingredientLink"];
	$heldAmt = $_POST["heldAmt"];

	$query = " Insert into ingredients(ingredientName, ingredientLink, heldAmt) values ('$ingredientName','$ingredientLink','$heldAmt');";

	mysqli_query($connect, $query) or die (mysqli_error($connect));
	mysqli_close($connect);
}
?>
