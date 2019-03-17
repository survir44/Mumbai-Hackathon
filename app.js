var express = require('express');
var request = require("request");
var cheerio = require("cheerio");
var router = express.Router();
var app = express();
var bodyParser = require('body-parser');
app.use(bodyParser.json());
app.use(bodyParser.urlencoded({
    extended: true
}));


var login = require('./routes/login');
var leaders_bio = require('./routes/leaders_bio');
var profile = require('./routes/profile');
var news = require('./routes/news');


app.use('/login',login);
app.use('/leaders_bio',leaders_bio);
app.use('/profile',profile);
app.use('/news',news);

module.exports=app;
