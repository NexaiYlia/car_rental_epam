USE epam_db;

INSERT INTO users(email, user_password, role)
	VALUES ('usermail123@mail.com', md5('12345678'), 1),
		   ('admin@gmail.com', md5('administrator'), 2);