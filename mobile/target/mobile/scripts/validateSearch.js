/**
 * Created by german on 12.11.14.
 */

$(document).ready(function(){
    var jVal = {
        'validate' : function() {
            var number = $('#number');
            var login = $('#login');
            var errorMessage = $('#error-custom-message-1');
            var errorMessage2 = $('#error-custom-message-2');
            if (number.val() == 0 && login.val() == 0) {
                jVal.errors = true;
                errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
                errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
            }
            else if(login.val() != 0 && (login.val().length < 2 || login.val().length > 15)) {
                jVal.errors = true;
                errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                errorMessage2.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
            }
            else {
                errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
            }
        },
        'sendIt' : function (){
            if(!jVal.errors) {
                $('#vds-overlay').show();
                $('#vds-wait').show();
                $('#jForm').submit();
            }
        }

    };
    $('#send').click(function (){
        jVal.errors = false;
        jVal.validate();
        jVal.sendIt();
        return false;
    });
    $('#number').change(jVal.validate);
    $('#login').change(jVal.validate);

});