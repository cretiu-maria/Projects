const cron = require('node-cron')
const sdk = require('api')('@opensea/v1.0#bg4ikl1mk428b');

const express = require('express')
const mongoose = require('mongoose');
const collectionRouter = require('./collections/Controllers');
const collectionModel = require('./collections/Model');
const COLLECTIONS = require('./collections')

const app = express();

const fetchData = () => { 
  for (const collection of COLLECTIONS){
  sdk.retrievingCollectionStats({collection_slug: collection})
  .then((res) => {
    const { stats } = res;
    console.log(stats);
    collectionModel
      .create({ 
        floorPrice: stats.floor_price,
        averagePrice: stats.average_price,
        totalSupply: stats.total_supply,
        numOwners: stats.num_owners,
        totalVolume: stats.total_volume,
        name: collection.toString(),
        sevenDaySales: stats.seven_day_sales,
      })
      .then((response) => console.log(response))
      .catch((error) => console.log(error));
  })
  .catch((err) => console.error(err));
//get data from OS
console.log('exec');
};
};
 
  
const startServer = () => {
    app.listen(4000,() => console.log('server started'))
};

const startDataBase = () =>{
    mongoose.connect('mongodb://localhost:27017', () => console.log('database started'))
};

const initRoutes = () => {
    app.use((req, res, next) => {
      res.header('Access-Control-Allow-Origin', '*');
      res.header('Access-Control-Allow-Headers', '*');
      res.header('Access-Control-Allow-Methods', '*');
      next();
    });
    app.use(express.json());
    app.use('/api/collections', collectionRouter);
  }

const startApp = () => {
    startServer()
    startDataBase()
    initRoutes()
}

startApp()

//cron.schedule('*/15 * * * *',fetchData)