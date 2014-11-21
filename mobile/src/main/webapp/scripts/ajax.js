/**
 * Created by german on 21.11.14.
 */

$(document).ready(function(){
    var jVal = {
        'number' : function() {
            var number = $('#number');
            var numberDiv = $('#numberDiv');
            var errorMessage = $('#error-contract-exists');
            if (number.val() == null) return false;
            if (numberDiv.val() == null) return false;
            if (errorMessage.val() == null) return false;
            if (number.val().length == 15) {
                var contractNumber = number.val();
                $.get('cp_employee_check_number/' + contractNumber, function(answer) {
                    if (answer == true) {
                        errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                        numberDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
                    }
                    else {
                        jVal.errors = true;
                        errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
                        numberDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');
                    }
                });
            }
            else return false;
        },
        'login' : function() {
            var login = $('#login');
            var loginDiv = $('#loginDiv');
            var errorMessage = $('#error-user-not-exists');
            if (login.val() == null) return false;
            if (loginDiv.val() == null) return false;
            if (errorMessage.val() == null) return false;
            if (login.val().length >= 2 || login.val().length <= 15) {
                var loginName = login.val();
                $.get('cp_employee_check_user/' + loginName, function(answer) {
                    if (answer == true) {
                        errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                        loginDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
                    }
                    else {
                        jVal.errors = true;
                        errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
                        loginDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');
                    }
                });
            }
            else return false;
        },
        'sendIt' : function (){
            if(!jVal.errors) {
                $('#jForm').submit();
            }
        }

    };
    $('#send').click(function (){
        jVal.errors = false;
        jVal.number();
        jVal.login();
        jVal.sendIt();

        return false;
    });


    $('#number').change(jVal.number);
    $('#login').change(jVal.login);
});