const mongoose = require('mongoose')
const { Schema } = mongoose

const collectionSchema = new Schema({
    floorPrice: String,
    averagePrice: String,
    totalSupply: Number,
    numOwners: Number,
    name: String,
    totalVolume: Number,
    sevenDaySales: Number,
    date: { type: Date, default: Date.now},    
})

const collection = mongoose.model('collections', collectionSchema)

module.exports = collection;