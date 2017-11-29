Authentication = Class.extend({
	loggined: false,
	login: "",
	password: "",
	
	Login: function() {
		var inner_login = $("#popinlog").val();
		var inner_password = $("#popinpass").val();
		if (!inner_login) {
			alert("login field is empty");
			return false;
		}
		if (!inner_password) {
			alert("password field is empty");
			return false;
		}
		this.login = inner_login;
		this.password = inner_password;
		var result = serverProxy.getSessionIdFromMatchMaker(this.login, this.password);
		if (result[0]) {
			PopInHide();
			$("#bt_login").disabled = true;
			$("#bt_registy").disabled = true;
			$("#bt_logout").disabled = false;
			this.loggined = true;
			return true;
		} else {
			alert(result[1]);
			return false;
		}
	},
	
	Registry: function() {
		var inner_login = $("#popreglog").val();
		var password1 = $("#popregpass1").val();
		var password2 = $("#popregpass2").val();
		if (!inner_login) {
			alert("login field is empty");
			return false;
		}
		if (!password1) {
			alert("enter password field is empty");
			return false;
		}
		if (!password2) {
			alert("confirm password field is empty");
			return false;
		}
		if (password1 == password2) {
			this.login = inner_login;
			this.password = password1;
			var result = serverProxy.registryFromMatchMaker(this.login, this.password1);
			if (result[0]) {
				serverProxy.getSessionIdFromMatchMaker(this.login, this.password);
				PopUpHide();
				this.loggined = true;
				return true;
			} else {
				alert(result[1]);
				return false;
			}
		} else {
			alert("ERROR! Passwords don't match!");
			return false;
		}
	}, 
	
	Logout: function() {
		this.loggined = false;
		$("#bt_login").removeAttr('disabled');
		$("#bt_registy").removeAttr('disabled');
		$("#bt_logout").attr('disabled', 'disabled');
	}
});
auth = new Authentication();

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
