<?php
if($_SERVER["REQUEST_METHOD"]=="POST"){
	require 'connection.php';
	switchU();
}

function switchU()
{
	global $connect;

  $userid = $_POST["userID"];
  $query = "  Update ingredients
              INNER JOIN userings ON userings.userID = '$userid'
              SET heldAmt = userAmt
              WHERE userings.ingredientid = ingredients.ingredientid";
	mysqli_query($connect, $query) or die (mysqli_error($connect));
	mysqli_close($connect);
}
?>
