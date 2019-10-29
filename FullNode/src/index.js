'use strict';

let harvester = require("harvesterjs"),
    Joi = require('joi');

let config = {
    port: 8080,
    mongo : { url : 'mongodb://localhost:27017/test' }
};

const app = harvester({
    adapter: 'mongodb',
    connectionString: config.mongo.url,
    oplogConnectionString: config.mongo.url,
//}).resource('country', {
//    name: Joi.string().description("Country name")
}).resource('state', {
    name: Joi.string(),
    //links: { country: 'country' }
    links: { country:{ ref: 'country', baseUri: 'http://localhost:8081' }}
}).resource('city', {
    name: Joi.string(),
    links: { state: 'state' }
})
.after('city', function(req,res){
    console.log("City called")
    return this
})
.listen(config.port);

require("./initialLoad")(app)

console.log("Started")