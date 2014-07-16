var express = require('express'),
    request = require('request'),
    async = require('async'),
    app = express();

// where our json data lives
var data = {"s3": "https://gist.githubusercontent.com/cjwebb/aef1f4fb2ca6d01f8b63/raw/0b6eb2c9b55a6720ccf41ee4ff8cca053cfda063/product-s3.json",
	    "s2": "https://gist.githubusercontent.com/cjwebb/2d7fce88ce6594325bec/raw/fe025c2eafb8aeca953999f10663b83863a14d25/product-s2.json",
	    "s1": "https://gist.githubusercontent.com/cjwebb/814c6337b0f04f1cfeba/raw/dc9b297a96c0bd8870436413e51efa2a36168308/product-s1.json",
	    "a1": "https://gist.githubusercontent.com/cjwebb/c26c42e03ea8573efd4c/raw/75479f6f2d218ac6212e4f4b53fc7e30746228bd/article-a1.json"}

var fetchProduct = function(item, cb) {
	request.get(data[item], {json:true}, function(error, response, body){
		cb(null, body);
	});
};

app.get('/', function(req, res){
	request.get(data['a1'], {json:true}, function(error, response, body){
		async.map(body['product_list'], fetchProduct, function(err, results){
			body['product_list'] = results.filter(function(n){ return n }); // mutate all the state!!	
			res.send(body);	
		});
	});
});

app.listen(3000);

