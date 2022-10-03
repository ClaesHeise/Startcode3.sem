// let outerDiv = document.querySelector("#outer");


let para = document.querySelector("#paragraph");

document.querySelector("#outer").onclick = function(e) {
    const id = e.target.id;
    para.innerText = ("Hi, from id nr:", id)
    console.log('Hi from id nr:', id)
}
