<?php

if($_SERVER["REQUEST_METHOD"]=="POST"){
	include 'connection.php';
	refreshRecipe();
}

function refreshRecipe()
{
	global $connect;

	$query = " select recipeName, recipeLink
from ingredients
inner join reftable
on reftable.ingredientID = ingredients.ingredientID
inner join recipes
on recipes.recipeID = reftable.recipeID
where heldAmt >= reqAmt
group by recipeName having count(reqAmt) >=8; ";

	$result = mysqli_query($connect, $query);
	$number_of_rows = mysqli_num_rows($result);

	$temp_array  = array();

	if($number_of_rows > 0) {
		while ($row = mysqli_fetch_assoc($result)) {
			$temp_array[] = $row;
		}
	}

	header('Content-Type: application/json');
	echo json_encode(array("recipes"=>$temp_array));
	mysqli_close($connect);
}
?>
