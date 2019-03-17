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
        console.log('Not connected to MySql.\n');
    }
});

router.post('/', (req, res) =>{
	var name= req.body.name;
	 
	//Query to select the tuple of the user
	connection.query('SELECT * FROM info WHERE name = ?',[name], function (error, results, fields) {
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
			res.status(200).send(results[0]);
        }
		else
		{
			//User does not exist
			res.sendStatus(400);
		}
    }
    });
});

module.exports=router;
