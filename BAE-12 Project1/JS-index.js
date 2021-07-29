'use strict';

const baseURL = "http://localhost:8080";

axios.get(`${baseURL}/`).then(res => {
    console.log(res);
    console.log("DATA: ", res.data);

}).catch(err => console.log(err));

const getallSection = document.querySelector("#getAllSection");
const getAllFilms = () => {
    axios.get(`${baseURL}/getAllFilms`).then(res => {
        const films = res.data;
        getallSection.innerHTML = "";

        films.forEach(film => renderFilm(film,getallSection));

    }).catch(err => console.log(err));
}
const renderFilm = (film,divSection) => {
    const newFilm = document.createElement('div');

    const filmTitle = document.createElement('h3');
    filmTitle.innerText = film.title;
    newFilm.appendChild(filmTitle)

    const filmID = document.createElement("p");
    filmID.innerText = `ID: ${film.id}`;
    newFilm.appendChild(filmID);

    const filmDirector = document.createElement("p");
    filmDirector.innerText = `Directed By: ${film.director}`;
    newFilm.appendChild(filmDirector);

    const filmRuntime = document.createElement("p");
    filmRuntime.innerText = `Runtime: ${film.runtime}`;
    newFilm.appendChild(filmRuntime);

    const filmAgeRating = document.createElement("p");
    filmAgeRating.innerText = `Age Rating: ${film.ageRating}`;
    newFilm.appendChild(filmAgeRating);

    const deleteButton = document.createElement('button');
    deleteButton.innerText = "DELETE";
    deleteButton.addEventListener('click', () => deleteFilm(film.id));

    newFilm.appendChild(deleteButton);
    divSection.appendChild(newFilm);

}

const getByIDSection = document.querySelector("#getByIDSection");
const filmID = document.querySelector('#filmID');
function getByID() {

    axios.get(`${baseURL}/getFilm/${filmID.value}`).then(res => {
        const film = res.data;

        renderFilm(film,getByIDSection);

    }).catch(err => console.log(err));
}

const getByNameSection = document.querySelector("#getByNameSection");
const filmName = document.querySelector('#filmName');
function getByName() {

    axios.get(`${baseURL}/getByName/${filmName.value}`).then(res => {
        const film = res.data;

        renderFilm(film[0],getByNameSection);

    }).catch(err => console.log(err));
}

const deleteFilm = id => {
    axios.delete(`${baseURL}/deleteFilm/${id}`)
        .then(res => {
            console.log(res);
            getAllFilms();
            alert("Film Deleted From WatchList")

        }).catch(err => console.log(err));

}
document.querySelector("section#postSection > form#createFilm").addEventListener('submit', (e) => {
    e.preventDefault();

    const form = e.target;

    const data = {
        title: form.title.value,
        director: form.director.value,
        runtime: form.runtime.value,
        ageRating: form.ageRating.value
    }
    axios.post(`${baseURL}/createFilm`, data)
        .then((res) => {
            console.log(res);
            getAllFilms();

            form.reset();
            form.title.focus();
            alert("Film added successfully");

        }).catch(err => console.log(err));

})

document.querySelector("section#updateFilmSection > form#updateFilm").addEventListener('submit', (e) => {
    e.preventDefault();
    const updatedID = document.querySelector('#updatedID');
    const form = e.target;

    const data = {
        id: form.updatedID.value,
        title: form.updatedTitle.value,
        director: form.updatedDirector.value,
        runtime: form.updatedRuntime.value,
        ageRating: form.updatedAgeRating.value
    }
    axios.put(`${baseURL}/putFilm/${data.id}`, data)
        .then((res) => {
            console.log(res);
            getAllFilms();

            form.reset();
            form.updatedID.focus();
            alert("Film updated successfully");

        }).catch(err => console.log(err));

})
getAllFilms();