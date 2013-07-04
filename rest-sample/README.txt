#To Do
MYSQL
*IP Address:
	INET_ATON() and INET_NTOA() functions.
	http://dev.mysql.com/doc/refman/5.0/en/miscellaneous-functions.html#function_inet-aton
	mysql> SELECT INET_ATON('10.0.5.9');
        	-> 167773449
	For this example, the return value is calculated as 10×2563 + 0×2562 + 5×256 + 9.
	mysql> SELECT INET_NTOA(167773449);
        	-> '10.0.5.9'

*MAC Address:
	use bigint unsigned (8 bytes) then you can:
		select hex(mac_addr) from log;
		and
		insert into log (mac_addr) values (x'000CF15698AD');
	store as binary to save space:

