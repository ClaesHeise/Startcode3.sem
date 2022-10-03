const url = 'https://jsonplaceholder.typicode.com/users/';

async function getUser(url) {
    fetch(url)
        .then((response) => response.json())
        .then((data) => getSinglePerson(data));
}

async function getUsers() {
    fetch(url)
        .then((response) => response.json())
        .then((data) => getAllUsers(data));
}

async function whichUser() {
    let userInput = document.querySelector('#userInput').value;
    getUser(url+userInput);
}

async function allUsers() {
    getUsers();
}

const getSinglePerson = function (request) {
    let user = request;
    console.log(user);
    const list = document.querySelector("#listingUser");
    list.innerHTML = '<ul>' +
        '<li>' + 'name:' + user.name + '</li>' +
        '<li>' + 'Phone:' + user.phone + '</li>' +
        '<br><li>' + 'Address' + '</li>' +
        '<li>' + user.address.street + '</li>' +
        '<li>' + user.address.city + '</li>' +
        '<li>' + user.address.zipcode + '</li>' +
        '<li>' + user.address.geo.lat + ',' + user.address.geo.lng + '</li>';
    + '</ul>';
}

const getAllUsers = function (request) {
    let users = request;
    console.log(users);
    let listElements = '';
    users.forEach( user => {
        let listEl = '<ul>' + '<li>' + 'name:' + user.name + '</li>' +
            '<li>' + 'Phone:' + user.phone + '</li>' +
            '<br><li>' + 'Address' + '</li>' +
            '<li>' + user.address.street + '</li>' +
            '<li>' + user.address.city + '</li>' +
            '<li>' + user.address.zipcode + '</li>' +
            '<li>' + user.address.geo.lat + ',' + user.address.geo.lng + '</li>'
            + '</ul>';
            listElements += listEl;
    });
    const list = document.querySelector("#listingUsers");
    list.innerHTML = listElements;
}

getUser(url + 5);