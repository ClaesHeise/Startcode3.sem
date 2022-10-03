let display = document.querySelector("#display");

let calcOutput = "";

document.querySelector("#buttons").onclick = function(e) {
    if(e.target.innerText !== "=") {
        calcOutput = calcOutput + e.target.innerText;
        display.innerHTML = calcOutput;
    }
    else{
        display.innerHTML = eval(calcOutput);
        calcOutput = "";
    }
    console.log('input was:', calcOutput);
}