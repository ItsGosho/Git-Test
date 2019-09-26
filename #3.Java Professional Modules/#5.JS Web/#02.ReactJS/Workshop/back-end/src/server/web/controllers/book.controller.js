import Express from "express";
import {BookRoutingURLs} from "../../constants/web/routing.urls.constants";

const Router = Express.Router();

Router.post(BookRoutingURLs.CREATE, (request, response) => {
    /*
    * TODO: !AUTHOR & GENRES няма валидация
    *  1.Дали user-a е логнат
    *  2.Дали user-a е [ADMIN]
    *  3.Дали на book-a TITLE-a e String,Length > 2 ,Doesnt Exist
    *  4.Дали на book-a DESCRIPTION-a е String,Between 10 & 200
    *  5.Дали на book-a PRICE-a е Number,Value > 0
    *  6.Дали на book-a IMAGE-a е String,Starts with http:// || https://
    *  7.Взимам Request Body-то (request.body)
    *  8.Respond-вам successful с "Book has been created!" и вече запазената книга
    * */

    const {title, description, price, image, author, genres} = request.body;

    console.log();
});

Router.post(BookRoutingURLs.EDIT, (request, response) => {
    /*
    * TODO: !AUTHOR & GENRES няма валидация
    *  1.Дали user-a е логнат
    *  2.Дали user-a е [ADMIN]
    *  3.Дали има книга с това ID
    *  4.Дали на book-a TITLE-a e String,Length > 2 ,Doesnt Exist
    *  5.Дали на book-a DESCRIPTION-a е String,Between 10 & 200
    *  6.Дали на book-a PRICE-a е Number,Value > 0
    *  7.Дали на book-a IMAGE-a е String,Starts with http:// || https://
    *  8.Взимам Request Body-то (request.body)
    *  9.Respond-вам successful с "Book has been edit!" и вече запазената книга
    * */
});

Router.get(BookRoutingURLs.ALL, (request, response) => {
    /*
    * TODO:
    *  1.Дали user-a е логнат
    *  2.Взимам всички книги от базата
    *  8.Respond-вам successful с "Books has been fetched!" и книгите
    * */
});

Router.post(BookRoutingURLs.REVIEW, (request, response) => {
    /*
    * TODO:
    *  1.Дали user-a е логнат
    *  2.Дали има книга с това ID
    *  3.Дали REVIEW-то е поне 4 символа
    *  4.Създавам обекта така:
    *  let reviewObj = {
        review,
        createdBy: username
       }
     * 6.И го пъхам в на book-a reviews масива
    *  7.Respond-вам successful с "Review has been published!"
    * */
});

Router.post(BookRoutingURLs.LIKE, (request, response) => {
    /*
    * TODO:
    *  1.Дали user-a е логнат
    *  2.Дали има книга с това ID
    *  3.Дали вече НЕ я е харесал
    *  4.И във на book likes просто пъхам username-a му
    *  5.Respond-вам successful с "Liked successfully!"
    * */
});

Router.post(BookRoutingURLs.LIKE, (request, response) => {
    /*
    * TODO:
    *  1.Дали user-a е логнат
    *  2.Дали има книга с това ID
    *  3.Дали вече я е харесал
    *  4.И във на book likes просто махам username-a му
    *  5.Respond-вам successful с "Like removed!"
    * */
});

Router.post(BookRoutingURLs.DELETE, (request, response) => {
    /*
    * TODO:
    *  1.Дали user-a е логнат
    *  2.Дали е [ADMIN]
    *  3.Дали има книга с това ID
    *  4.Махам книгата
    *  5.Respond-вам successful с "Book removed!"
    * */
});