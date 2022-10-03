let names = ["Lars", "Carla", "Jan", "Michelle", "Peter", "Bo", "Frederick"];

const listNames = document.querySelector("#listNames2");
listNames.innerHTML = '<ul>' + names.map(function (el) {
    return '<li>' + el + '</li>';
}).join('') + '</ul>';

function addName() {
    let nameValue = document.querySelector("#myInput").value;
    names.push(nameValue);
    listNames.innerHTML = '<ul>' + names.map(function (el) {
        return '<li>' + el + '</li>';
    }).join('') + '</ul>';
    console.log(nameValue);
}

let cars = [
    { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
    { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
    { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
    { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
    { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

const carTable = document.querySelector("#carTable");
function makeCarTable(arrayInput) {
    carTable.innerHTML = '<table className="table table-dark table-striped">' +
        '<thead><tr><th scope="col">id</th><th scope="col">Year</th><th scope="col">Make</th>' +
        '<th scope="col">Model</th><th scope="col">Price</th></tr></thead><tbody>' +
        arrayInput.map(function (el) {
            return '<tr>' +
                '<td>' + el.id + '</td>' +
                '<td>' + el.year + '</td>' +
                '<td>' + el.make + '</td>' +
                '<td>' + el.model + '</td>' +
                '<td>' + el.price + '</td>' +
                '</tr>';
        }).join('') + '</tbody></table>';
}

makeCarTable(cars);

function cheapCarsTable() {
    let priceInput = document.querySelector("#carInput").value;
    let cheapCars = cars.filter(el => el.price < priceInput);
    makeCarTable(cheapCars);
}
