const BodyParser = require('body-parser');
const HandleBars = require('express-handlebars');
const MultiPart = require('connect-multiparty');
const FolderPaths = require('../constants/folder.path.constants');

module.exports = (app) => {
    app.use(MultiPart());
    app.use(BodyParser.urlencoded({extended: true}));

    app.use(require('../web/controllers/home.controller'));
    app.use(require('../web/controllers/category.controller'));
    app.use(require('../web/controllers/product.controller'));

    app.use('/static', require('express').static('src/resources/public'));

    app.set('views', FolderPaths.VIEW_FOLDER);

    app.engine('.hbs', HandleBars({
        defaultLayout: 'index',
        extname: '.hbs',
        layoutsDir: `${FolderPaths.VIEW_FOLDER}\\layouts`,
        partialsDir: `${FolderPaths.VIEW_FOLDER}\\partials`,
    }));

    app.set('view engine', '.hbs');

};