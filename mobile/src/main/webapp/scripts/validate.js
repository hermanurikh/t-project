/**
 * Created by german on 12.11.14.
 */

$(document).ready(function(){
   var jVal = {
       'number' : function() {
           var number = $('#number');
           var numberDiv = $('#numberDiv');
           var errorMessage = $('#error-custom-message-1');
           if (number.val().length != 10) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               numberDiv.addClass('ui-ajaxvalidate-error')

           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               numberDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
           }
       },
       'login' : function() {
           var login = $('#login');
           var loginDiv = $('#loginDiv')
           var errorMessage = $('#error-custom-message-2');
           if (login.val().length == 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               loginDiv.addClass('ui-ajaxvalidate-error')
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               loginDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
           }
       },
       'tariff' : function() {
           var errorMessage = $('#error-custom-message-3');
           if ($('input[name="cb"]:checked').length === 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
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
            jVal.tariff();
            jVal.sendIt();
       return false;
    });



    $('#number').change(jVal.number);
    $('#login').change(jVal.login);
    $('input[name="cb"]').change(jVal.tariff);
    $("#number").inputmask("(999)999-99-99");//маска

});