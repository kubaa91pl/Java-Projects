function like(i)
{
    alert('Ta gitara została przez Ciebie polubiona');
    var querry2= "UPDATE guitar SET guitar.Like = guitar.Like+1 WHERE tabela.id = 1;";
    router.get('/guitars', function(req, res){
  db.connect(function(err){
        if(!err)
          console.log('Database is connected');
        else
          console.log("Error connecting database ... ");
      }
  );


  db.query(querry2, function(error, dane){
    res.render('guitars.jade', { title: 'guitars', dane: dane });
  });


});

module.exports = router;

}
