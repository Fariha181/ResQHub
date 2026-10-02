INSERT INTO hospitals (name, email, password, location, available_icu_beds, available_general_beds) 
VALUES ('Square Hospital', 'info@squarehospital.com', '123456', 'Panthapath', 12, 50)
ON DUPLICATE KEY UPDATE id=id;

INSERT INTO police_stations (station_name, area, contact_number, is_available) 
VALUES ('Dhanmondi Model Thana', 'Dhanmondi', '+8801711000000', true)
ON DUPLICATE KEY UPDATE id=id;