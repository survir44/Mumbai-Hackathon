var request = require("request");
var cheerio = require("cheerio");
var mysql = require("mysql");
var express = require('express');
var router = express.Router();
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));

var url = "https://www.ndtv.com/topic/india-election";

var connection = mysql.createConnection({
	host:'localhost',
	user:'pmauser',
	password:'qwerty',
	database:'veto'
});

connection.connect(function(error){
	if(error){
		console.log('Error in connection');
	}else{
		console.log('Connected....');
	}
});

var sql = "DELETE FROM `news` WHERE 1";
	 connection.query(sql,function(error,rows,fields){
		if(!!error){
			console.log('Error in query');
		}
		
	 });



request(url,function(err,response,html){
	if(!err){
		var $ = cheerio.load(html);
		
$('.result_for').next().children().children().each((i,el)=>{
	var head = $(el).children().children().children().text().replace(/\s\s+/g,'');

	//console.log(head);
	
	var n = $(el).children().next().next().next().text().replace(/\s\s+/g,'');
	//console.log(s);
	
	var sql = "INSERT INTO `news`(`head`, `n`) VALUES (?,?)";
	 connection.query(sql,[head,n],function(error,rows,fields){
		if(!!error){
			console.log('Error in query');
		}
		
	 });
	
	if(i>13){
		return false;
	}
	
	})
	
	
//console.log(a.text().replace(/\s\s+/g,''));


}});


router.post('/', (req, res) =>{
	console.log("connection");
	//Query to select the tuple of the user
	connection.query('SELECT * FROM news', function(error, results, fields) {
    if (error)
	{
		console.log(123);
		res.status(400);
    }
	else
	{
		//console.log(results);
		res.status(200).send(results);
			/*res.status(200).send({
				"voter": results[0].voter,
				"per": results[0].per,
				"stateper": results[0].stateper
			});*/
    }
    });
});

module.exports=router;
