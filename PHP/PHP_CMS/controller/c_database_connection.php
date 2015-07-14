<?php

class Database {
	
	private static $connection;
	
	private static $database;
	
	private function __construct() {
		
		require_once __DIR__.'/../controller/sec_c_db_config/sec_c_db_config.php';
		Database::$connection = new mysqli($db_info['hostname'], $db_info['username'], $db_info['password'], $db_info['database_name']);
	}
	
	public static function getConnection() {
		
		if(isset($database)) {
			return Database::$connection;
		}
		
		else {
			$database = new Database();
			return Database::$connection;
		}
	}
}

?>
