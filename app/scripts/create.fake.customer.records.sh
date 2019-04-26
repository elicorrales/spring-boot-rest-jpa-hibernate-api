#!/bin/bash

for c in 1 2 3 4 5 6 7 8 9 10 11 12 13 14;
do
	fname="First${c}";
	lname="Last${c}";
	email="${fname}.${lname}@someplace.com";
	echo "insert into customer(fname,lname,email) values ('$fname','$lname','$email');" >> create.fake.customers.sql;
	customer_id=$c;
	for o in 1 2 3 4 5 6 7 8 9 10 11 12 13 14;
	do
		number="$customer_id-$(($(date +%s%N)/1000000))";
		description="Faker Order #${o} For $fname.$lname";
		echo "insert into orders(number,description,status,customer_id) values('$number','$description','New',$customer_id);" >> create.fake.customers.sql;
	done;
done;
