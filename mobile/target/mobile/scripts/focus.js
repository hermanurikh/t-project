/**
 * Created by german on 12.11.14.
 */

$(document).ready(function() {
    var number = $('#number');
    var numberDiv = $('#numberDiv');
    var login = $('#login');
    var loginDiv = $('#loginDiv')

   number.focus(function () {
        numberDiv.addClass('ui-state-focus');
    });
    number.blur(function () {
        numberDiv.removeClass('ui-state-focus');
    });
    login.focus(function () {
        loginDiv.addClass('ui-state-focus');
    });
    login.blur(function () {
        loginDiv.removeClass('ui-state-focus');
    });
});
