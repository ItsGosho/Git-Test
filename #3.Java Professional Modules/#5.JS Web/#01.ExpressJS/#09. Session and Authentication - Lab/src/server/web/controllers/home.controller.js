const Router = require('express').Router();
const RoutingURLs = require('../../constants/routing.urls');
const ProductServices = require('../../services/product.services');
const ViewPaths = require('../../constants/view.path.constants');
const Role = require('../middlewares/role.middleware');
const JWTHelper = require('../helpers/jwt.helper');

Router.get(RoutingURLs.BASE.HOME, async (request, response) => {
    let products = await ProductServices.findAll();

    response.render(ViewPaths.OTHER.HOME, {products,user: request.user});
});


module.exports = Router;