<?php
define('hostname', 'localhost:3306');
define('user', 'root');
define('password', '');
define('databaseName', 'noods');


$connect = mysqli_connect(hostname, user, password, databaseName);
?>
