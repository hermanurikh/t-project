function validateForm() {
    var x = document.forms["myForm"]["name"].value;
    if (x == null || x == "") {
        alert("Необходимо заполнить поле Имя!");
        return false;
    }
    var y = document.forms["myForm"]["surname"].value;
    if (y == null || y == "") {
        alert("Необходимо заполнить поле Фамилия!");
        return false;
    }

    var a = document.forms["myForm"]["birthday"].value;
    if (a == null || a == "") {
        alert("Необходимо заполнить поле Дата Рождения!");
        return false;
    }
    var b = document.forms["myForm"]["passport"].value;
    if (b == null || b == "") {
        alert("Необходимо заполнить поле Паспорт!");
        return false;
    }
    var с = document.forms["myForm"]["address"].value;
    if (с == null || с == "") {
        alert("Необходимо заполнить поле адрес!");
        return false;
    }
    var d = document.forms["myForm"]["email"].value;
    if (d == null || d == "") {
        alert("Необходимо заполнить поле e-mail!");
        return false;
    }
    var e = document.forms["myForm"]["balance"].value;
    if (e == null || e == "") {
        alert("Необходимо заполнить поле баланс!");
        return false;
    }
    var f = document.forms["myForm"]["password"].value;
    if (f == null || f == "") {
        alert("Необходимо заполнить поле пароль!");
        return false;
    }
    var g = document.forms["myForm"]["role"].value;
    if (g == null || g == "") {
        alert("Необходимо заполнить поле - роль пользователя!");
        return false;
    }




}