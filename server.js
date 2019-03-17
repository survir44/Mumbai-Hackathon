var app = require('./app');

app.get("/", (req,res)=>{
return res.send("Welcome to VETO");
});

//port activation
app.listen(8080, (req, res) => {
	console.log("Listening on 8080");
});
