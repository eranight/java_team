$(document).ready(function() {
    //Скрыть PopIn при загрузке страницы
    PopInHide();
    //Скрыть PopUp при загрузке страницы
    PopUpHide();
});
//Функция отображения PopIn
function PopInShow() {
    $("#popin").show();
}
//Функция скрытия PopIn
function PopInHide() {
    $("#popin").hide();
}
//Функция отображения PopUp
function PopUpShow() {
    $("#popup").show();
}
//Функция скрытия PopUp
function PopUpHide() {
    $("#popup").hide();
}
function Compare() {
    var pass1 = document.getElementById("popregpass1").value;
    var pass2 = document.getElementById("popregpass2").value;
    if (pass1 != pass2) {
        alert("ERROR! Passwords don't match!");
		return false;
    }
	return true;
}