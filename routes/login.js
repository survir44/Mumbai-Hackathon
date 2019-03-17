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

router.post('/', (req, res) =>{
	var username= req.body.username;
    var password = req.body.password;
	
	console.log("connection");
	 
	//Query to select the tuple of the user
	connection.query('SELECT * FROM user WHERE username = ?',[username], function(error, results, fields) {
    if (error)
	{
		console.log("error");
		res.status(400);
    }
	
	else
	{
		if(results.length >0)
		{
			//User exists
			if(results[0].password == password)
			{
				//User password match
				res.sendStatus(200);		
			}
	        else{
            //Users password do not match
			console.log("error");
            res.sendStatus(400);
			}
        }
		else
		{
			//User does not exist
			console.log("error");
			res.sendStatus(400);
		}
    }
    });
});


module.exports=router;
