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
function Login() {
	if ($("#popinlog").val() && $("#popinpass").val()) {
		serverProxy.getSessionIdFromMatchMaker();
		PopInHide();
		return true;
	} else {
		alert("something is empty");
		return false;
	}
}
function Registry() {
	var pass1 = $("#popregpass1").val();
	var pass2 = $("#popregpass2").val();
	if ($("#popreglog").val() && pass1 && pass2) {
		if (pass1 != pass2) {
			alert("ERROR! Passwords don't match!");
			return false;
		} else {
			PopUpHide();
			return true;
		}
	} else {
		alert("something is empty");
		return false;
	}
}