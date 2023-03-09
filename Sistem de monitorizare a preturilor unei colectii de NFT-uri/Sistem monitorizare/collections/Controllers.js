const express = require('express');
const collectionService= require('./Services');


const collectionRouter = express.Router();

collectionRouter.route('/nft-create').post(createCollection);
collectionRouter.route('/nftread').get(getCollection);
collectionRouter.route('/nft-update').put(updateCollection);
collectionRouter.route('/nft-delete').delete(deleteCollection);

function createCollection(request,response){
    const value = request.body;
    collectionService.createRecord(
        value,
        data=>response.status(201).json(data),
        error=>response.status(400).json(error),
    );
}

function getCollection(request,response){
    collectionService.getRecord(
        data=>response.status(200).json(data),
        error=>response.status(400).json(error),
    );
}

function updateCollection(request, response) {
    const value = request.body;
    collectionService.updateRecord(
      value,
      (data) => response.status(201).json(data),
      (error) => response.status(400).json(error)
    );
}

function deleteCollection(request, response) {
    const value = request.body;
    collectionService.deleteRecord(
      value,
      (data) => response.status(201).json(data),
      (error) => response.status(400).json(error)
    );
  }

module.exports=collectionRouter;