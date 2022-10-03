// // Print out random number from 1 to 10.
// let randomNumber = Math.floor(Math.random() * 10 + 1)
// //
// console.log("Random number: ", randomNumber)
//
// let a = 5
//
// let b = 3
//
// let addTogether = a + b
//
// console.log("Add together: ",addTogether)
//
// let floatNumber = 3.4
//
// let roundUp = Math.ceil(floatNumber)
//
// console.log("Round up: ", roundUp)
//
// let roundDown = Math.floor(floatNumber)
//
// console.log("Round down: ", roundDown)
//
// let cph = "Copenhagen"
//
// let bis = "Business"
//
// let aca = "Academy"
//
// const cba = (stringArray) => {
//     let output = ""
//     stringArray.forEach((text) => {
//         output = output + text + " "
//     });
//     return output
// }
//
// let strArray = [cph, bis, aca]
//
// console.log(cba(strArray))
//
// console.log("String to upper case: ", cba(strArray).toUpperCase());
// console.log("String to Lower case: ", cba(strArray).toLowerCase());
//
// cph = "Lyngby"
//
// strArray.shift()
// strArray.unshift(cph)
//
// console.log("Changed to lyngby: ", cba(strArray))
//

let boys = ["Peter", "lars", "Ole"];
let girls = ["Janne", "Hanne", "Sanne"];

let all = boys.concat(girls);

let arrayString = all.join(", ");

console.log("All array: ", arrayString);

all.push("Lone", "Gitte");

console.log("Add to array: ", all.join(", "));

all.unshift("Hans", "Kurt");

console.log("Add to front of array: ", all.join(", "));

all.shift();

console.log("Remove from front of array: ", all.join(", "));

all.pop();

console.log("Remove from back of array: ", all.join(", "));

// Make when it wants to print
all.splice(3, 2)

console.log("Remove from middle of array: ", all.join(", "));

all = boys.concat(girls);

all.reverse();

console.log("Reverse array: ", all.join(", "));

all.sort();

console.log("Sort array: ", all.join(", "));

let upperCaseNames = all.join(", ").toUpperCase();

console.log("Uppercase Array (names): ", upperCaseNames);

all.filter(el => el.startsWith("L")).concat(all.filter(el => el.startsWith("I")))

console.log("Only some names: ", all.join(", "));

// document.querySelector("#output").innerText = ("All array: ", arrayString);
document.querySelector("#output").innerText = ("Array: ", all.join(", "));

var msgPrinter = function(msg,delay){
    setTimeout(function(){
        console.log(msg);
    },delay);
};

console.log("aaaaaaaaaa");
msgPrinter ("bbbbbbbbbb",2000);
console.log("dddddddddd");
msgPrinter ("eeeeeeeeee",1000);
console.log("ffffffffff");

let names = ["Lars", "Carla", "Jan", "Michelle", "Peter", "Bo", "Frederick"];

let only3LetterNames = names.filter(el => el.length <= 3);

console.log("Names with the length 3 or less:", only3LetterNames);

names.forEach(el => console.log(el));

only3LetterNames.forEach(el => console.log(el));

const uppercasedNames = names.map(el => el.toUpperCase());

console.log("Uppercased names using map:", uppercasedNames);

const listNames = document.querySelector("#listNames");
listNames.innerHTML = '<ul>' + names.map(function (el) {
    return '<li>' + el + '</li>';
}).join('') + '</ul>';

let cars = [
    { id: 1, year: 1997, make: 'Ford', model: 'E350', price: 3000 },
    { id: 2, year: 1999, make: 'Chevy', model: 'Venture', price: 4900 },
    { id: 3, year: 2000, make: 'Chevy', model: 'Venture', price: 5000 },
    { id: 4, year: 1996, make: 'Jeep', model: 'Grand Cherokee', price: 4799 },
    { id: 5, year: 2005, make: 'Volvo', model: 'V70', price: 44799 }
];

let newerCars = cars.filter(el => el.year > 1999);

console.log("Cars never than 1999", newerCars);

let volvos = cars.filter(el => el.model === "Volvo");

console.log("Volvo's:", volvos);

let cheapCars = cars.filter(el => el.price < 5000);

console.log("Cheap cars:", cheapCars);

let carsToSQL = cars.map(el => "INSERT INTO Cars (id,year,make,model,price) VALUES ("+el.id+", "+el.year+", "+el.make+", "+el.model+", "+el.price+");");

console.log("Cars to SQL:", carsToSQL[0])

const button1 = document.querySelector("#btn1");
const button2 = document.querySelector("#btn2");
const button3 = document.querySelector("#btn3");
const div1 = document.querySelector("#div1");
const colors = ["blue", "red", "green", "yellow"];


const divSelector = document.querySelectorAll("div").forEach((el) => {
    el.style.color = "blue";
});

// button1.addEventListener('click', () => {
//     let randomNmb = Math.floor(Math.random() * 4)
//     div1.classList.add(colors[randomNmb]);
// });

button1.addEventListener('click', () => {
    changeColor(div1);
});
button2.addEventListener('click', () => {
    changeColor(div2);
});
button3.addEventListener('click', () => {
    changeColor(div3);
});

function changeColor(element) {
    let randomNmb = Math.floor(Math.random() * 4)
    element.style.color = colors[randomNmb];
    // this.style.color = "red";
    // element.style.color = "blue";
}



