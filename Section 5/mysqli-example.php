<?php
// Packt MySQL 8 Recipes

// Official documentation: http://php.net/manual/en/mysqli.quickstart.prepared-statements.php

/* Configuration for mysqli object */
$MYSQL_HOST = 'localhost';
$MYSQL_USERNAME = 'hr_user';
$MYSQL_PASSWORD = 'secretpassword';
$MYSQL_DATABASE = 'hr';

/* Build the mysqli object */
$mysqli = new mysqli($MYSQL_HOST, $MYSQL_USERNAME, $MYSQL_PASSWORD, $MYSQL_DATABASE);

if ($mysqli->connect_errno) {
	die("Error: mysqli was unable to create a MySQL connection");
}

/* Prepare the statement */
if (!($stmt = $mysqli->prepare("SELECT first_name, last_name FROM employees WHERE employee_id > ?"))) {
    echo "Prepare failed: (" . $mysqli->errno . ") " . $mysqli->error;
}

/* Bind the placeholder */
$id = 100;
if(!$stmt->bind_param("i", $id)) {
	  echo "Bind failed: (" . $mysqli->errno . ") " . $mysqli->error;
}

/* Execute the statement */
if (!$stmt->execute()) {
    echo "Execute failed: (" . $mysqli->errno . ") " . $mysqli->error;
}

/* Bind the result */
$first_name = NULL;
$last_name = NULL;
if (!$stmt->bind_result($first_name, $last_name)) {
    echo "Binding output parameters failed: (" . $stmt->errno . ") " . $stmt->error;
}

/* For each row, fetch the result into the bound variables */
while ($stmt->fetch()) {
    printf("first_name = %s, last_name = %s<br/>", $first_name, $last_name);
}

/* We finished, close the statement */
$stmt->close();

?>
