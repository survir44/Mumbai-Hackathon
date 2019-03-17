var express = require('express');
var router = express.Router();
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


router.post('/register',(req,res)=>{
	//console.log("connection");
	var username= req.body.username;
	var password = req.body.password;
	var name = req.body.name;
	
	connection.query('INSERT INTO user(username,password,name) VALUES(?,?,?)',[username,password,name],function(error,results,fields){
	if (error){
	//console.log(error);
	res.sendStatus(400);
	}
	else
	{
		res.sendStatus(200);
		//console.log("Data Inserted");
	}
	});
});

router.post('/edit',(req,res)=>{
	//console.log("connection");
	var username = req.body.username;
	var name = req.body.name;
	var email = req.body.email;
	var mobile_no = req.body.mobile_no;
	var gender = req.body.gender;
	var address = req.body.address;
	
	connection.query('UPDATE user SET name=?,email=?,mobile_no=?,gender=?,address=? WHERE username=?',[name,email,mobile_no,gender,address,username],function(error,results,fields){
	if (error){
	console.log(error);
	res.sendStatus(400);
	}
	else
	{
		res.sendStatus(200);
		console.log("Data Inserted");
	}
	});
});

router.post('/view',(req,res)=>{
	//console.log("connection");
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


module.exports=router;
