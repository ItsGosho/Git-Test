const Router = require('express').Router();
const RoutingURLs = require('../constants/routing.urls');
const ProductServices = require('../services/product.services');
const ViewPaths = require('../constants/view.path.constants');

Router.route(RoutingURLs.HOME)
    .get(async (request, response) => {
        let products = await ProductServices.findAll();

        response.render(ViewPaths.OTHER.HOME, {products});
    });


module.exports = Router;