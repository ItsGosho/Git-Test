import Mongoose from 'mongoose';
import MongooseConstants from '../../constants/mongoose.constants';


const Schema = Mongoose.Schema;
const Type = Schema.Types;
const {Models} = MongooseConstants;


const productModel = new Schema({
    name: {
        type: Type.String,
        required: [true, 'Name is required!']
    },
    description: {
        type: Type.String
    },
    price: {
        type: Type.Number,
        min: [0, `Price must be between 0 and ${Number.MAX_VALUE}`],
        max: [Number.MAX_VALUE, `Price must be between 0 and ${Number.MAX_VALUE}`],
        default: 0
    },
    image: {
        type: Type.String
    },
    category: {
        type: Type.ObjectId,
        ref: Models.CATEGORY
    },
    isBought: {
        type: Type.Boolean,
        default: false
    },
    creator: {
        type: Type.ObjectID,
        ref: Models.USER,
        required: [true,'Creator is not set!']
    },
    buyer: {
        type: Type.ObjectID,
        ref: Models.USER
    }
});

productModel.post('save', function (product) {
    console.log(`-> `.red + `Product has been created/updated with name: ${product.name}`.cyan);
});

let ProductModel = Mongoose.model(Models.PRODUCT, productModel);

export default ProductModel;