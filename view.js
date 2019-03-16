var express = require('express');
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

// MySQL Connection
var mysql = require('mysql');
var connection = mysql.createConnection({
    host: 'localhost',
    user: 'pmauser',
    password: 'qwerty',
    database: 'veto'
});
connection.connect(function(err) {
    if (!err) {
        console.log('Connected to MySql!\n');
    } else {
        console.log(err);
    }
});


app.post('/',(req,res)=>{
	console.log("connection");
	var username = req.body.username;
	
	//fetching deatails from profile table
	connection.query('SELECT name,gender,email,mobile_no,address FROM user where username=?',[username],function(error,results,fields){
	if(error)
	{
		console.log(error);
		res.sendStatus(400);
	}
	else
	res.send(results[0]);
	});
});

//Port Listening
app.listen(8080, (req, res) => {
    console.log("Listening on 8080");


https://github.com/survir44/Mumbai-Hackathon/invitations
});
