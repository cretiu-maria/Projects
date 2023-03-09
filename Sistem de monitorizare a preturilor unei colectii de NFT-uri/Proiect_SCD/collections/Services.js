
const { response } = require('express');
const collectionModel = require('./Model')

const collectionService = {
    createRecord: (collection, success, fail) => {
        collectionModel.create(collection)
            .then(response => success(response))
            .catch(error => fail(error))
    },

    getRecord: (success, fail) => {
        collectionModel.find()
            .then(response => success(response))
            .catch(error => fail(error))
    },
   
    updateRecord: (newCollection, success, fail) => {
        collectionModel.findByIdAndUpdate(newCollection._id, newCollection)
          .then((response) => success(response))
          .catch((error) => fail(error));
      },

    deleteRecord: (collection, success, fail) => {
        collectionModel.findByIdAndDelete(collection._id)
          .then((response) => success(response))
          .catch((error) => fail(error));
      },
    
    
}

module.exports = collectionService;