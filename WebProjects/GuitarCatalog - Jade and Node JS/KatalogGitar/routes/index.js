var express = require('express');
var router = express.Router();
var mysql = require('mysql');

var db = mysql.createConnection({
  host: 'localhost',
  user: 'root',
  password: 'Kuba123Nizynski123',
  database: 'katalog_g'
});

/* GET home page. */
router.get('/', function(req, res, next) {
    db.connect(function(err){
        if(!err)
          console.log('Database is connected');
        else
          console.log("Error connecting database ... ");
      }
  );
  res.render('index', { title: 'Katalog Gitar' });
});


router.get('/guitars', function(req, res){
  db.connect(function(err){
        if(!err)
          console.log('Database is connected');
        else
          console.log("Error connecting database ... ");
      }
  );

  var querry1 = "SELECT guitar.Model, guitar. Brand, guitar.Body, guitar.Neck, guitar.Pickups, guitar.Made, guitar.Description, guitar.Likes, guitar.Price, guitar.id FROM guitar";
  db.query(querry1, function(error, dane){
    res.render('guitars.jade', { title: 'guitars', dane: dane });
  });


});


module.exports = router;
