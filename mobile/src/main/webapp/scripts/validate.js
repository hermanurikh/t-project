/**
 * Created by german on 12.11.14.
 */

$(document).ready(function(){
   var jVal = {
       'number' : function() {
           var number = $('#number');
           var numberDiv = $('#numberDiv');
           var errorMessage = $('#error-custom-message-1');
           if (number.val() == null) return false;
           if (numberDiv.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (number.val().length != 15) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               numberDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');

           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               numberDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
           }
       },
       'login' : function() {
           var login = $('#login');
           var loginDiv = $('#loginDiv');
           var errorMessage = $('#error-custom-message-2');
           if (login.val() == null) return false;
           if (loginDiv.val() == null) return false;
           if (errorMessage.val() == null) return false;
           if (login.val().length < 2 || login.val().length > 15) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               loginDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error')
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               loginDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
           }
       },
       'tariff' : function() {
           var errorMessage = $('#error-custom-message-3');
           var cb = $('#cb');
           var selector = $('input[name="cb"]:checked');
           if (cb.val() == null) return false;
           if (selector.length === 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'userName' : function() {
           var userName = $('#name');
           var errorMessage = $('#error-custom-message-4');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length == 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'surName' : function() {
           var userName = $('#surname');
           var errorMessage = $('#error-custom-message-5');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length == 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'birthday' : function() {
           var userName = $('#date');
           var errorMessage = $('#error-custom-message-6');
           var flag = false;
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           var parts = userName.val().split('-');
           var enteredDate = new Date(parts[0], parts[1]-1, parts[2]);
           var currentDate = new Date();
           var deadLine = new Date(1900, 0, 1);
           if (enteredDate > currentDate || enteredDate < deadLine) { flag = true }
           if (userName.val().length == 0 || flag == true) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'userLogin' : function() {
           var userName = $('#userLogin');
           var errorMessage = $('#error-custom-message-7');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length < 2 || userName.val().length > 15) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'userPassword' : function() {
           var userName = $('#userPassword');
           var errorMessage = $('#error-custom-message-8');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length < 6 || userName.val().length > 20) {
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
            jVal.userName();
            jVal.surName();
            jVal.birthday();
            jVal.userLogin();
            jVal.userPassword();
            jVal.sendIt();
       return false;
    });



    $('#number').change(jVal.number);
    $('#login').change(jVal.login);
    $('#name').change(jVal.userName);
    $('#surname').change(jVal.surName);
    $('#date').change(jVal.birthday);
    $('#userLogin').change(jVal.userLogin);
    $('#userPassword').change(jVal.userPassword);
    $('input[name="cb"]').change(jVal.tariff);

});