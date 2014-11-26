/**
 * Created by german on 12.11.14.
 */

$(document).ready(function(){
   var jVal = {
       'number' : function() {
           var number = $('#number');
           var numberDiv = $('#numberDiv');
           var errorMessage = $('#error-custom-message-1');
           var errorMessage2 = $('#error-contract-exists');
           if (number.val() == null) return false;
           var contractNumber = number.val();
           if (numberDiv.val() == null) return false;
           if (errorMessage.val() == null) return false;
           var answer;
           if (number.val().length != 15) { //если неправильное к-во
               //jVal.errors = true;
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               if (errorMessage2.val() != null) {
                   errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               }
               numberDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');

           }
           else { //если правильное к-во
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               if (errorMessage2.val() == null) {
                   numberDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
               }
               else {
                   answer = $.ajax({
                       type: "GET",
                       url: 'cp_employee_check_number/' + contractNumber,
                       async: false
                   }).responseText;
                       if (answer == "true") {
                           errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                           numberDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
                       }
                       else {
                           jVal.errors = true;
                           errorMessage2.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
                           numberDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');
                       }
               }
           }
       },
       'login' : function() {
           var login = $('#login');
           var loginDiv = $('#loginDiv');
           var errorMessage = $('#error-custom-message-2');
           var errorMessage2 = $('#error-user-not-exists');
           if (login.val() == null) return false;
           if (loginDiv.val() == null) return false;
           if (errorMessage.val() == null) return false;
           if (login.val().length < 2 || login.val().length > 15) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               loginDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error')
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               jVal.loginAjax();
           }
       },
       'loginAjax' : function() {
           var login = $('#login');
           var loginDiv = $('#loginDiv');
           var errorMessage = $('#error-user-not-exists');
           if (login.val() == null) return false;
           if (loginDiv.val() == null) return false;
           if (errorMessage.val() == null) return false;
           if (login.val().length >= 2 || login.val().length <= 15) {
               var loginName = login.val();
               var answer = $.ajax({
                   type: "GET",
                   url: 'cp_employee_check_user/' + loginName,
                   async: false
               }).responseText;
                   if (answer == "true") {
                       errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
                       loginDiv.removeClass('ui-ajaxvalidate-error').addClass('ui-ajaxvalidate-valid');
                   }
                   else {
                       jVal.errors = true;
                       errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
                       loginDiv.removeClass('ui-ajaxvalidate-valid').addClass('ui-ajaxvalidate-error');
                   }
           }
           else return false;
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
           else if (userName.val().length < 1 || userName.val().length > 30) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'price' : function() {
           var userName = $('#price');
           var errorMessage = $('#error-custom-message-5');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length == 0 || userName.val().length > 6 || userName.val() < 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'initialPrice' : function() {
           var userName = $('#initialPrice');
           var errorMessage = $('#error-custom-message-6');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length == 0 || userName.val().length > 6 || userName.val() < 0) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
           }
       },
       'balance' : function() {
           var userName = $('#balance');
           var errorMessage = $('#error-custom-message-balance');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length != 0) {
               if (userName.val() > 400000 || userName.val() < 0) {
                   jVal.errors = true;
                   errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               }
               else {
                   errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');}
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
           else if (userName.val().length < 1 || userName.val().length > 30) {
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
           var errorMessage2 = $('#error-user-exists');
           if (userName.val() == null) return false;
           if (errorMessage.val() == null) return false;
           else if (userName.val().length < 2 || userName.val().length > 15) {
               jVal.errors = true;
               errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
              if (errorMessage2 != null) {
                  errorMessage2.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
              }
           }
           else {
               errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               jVal.userLoginAjax();
           }
       },
       'userLoginAjax' : function() {
           var userName = $('#userLogin');
           var errorMessage = $('#error-user-exists');
           if (errorMessage.val() == null) return false;
           if (userName.val().length >= 2 || userName.val().length <= 15) {
               var loginName = userName.val();
               var answer = $.ajax({
                   type: "GET",
                   url: 'cp_employee_check_user/' + loginName,
                   async: false
               }).responseText;
               if (answer == "false") {
                   errorMessage.removeClass('error-custom-message-incorrect').addClass('error-custom-message');
               }
               else {
                   jVal.errors = true;
                   errorMessage.removeClass('error-custom-message').addClass('error-custom-message-incorrect');
               }
           }
           else return false;
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
       'ajaxValidateOptions' : function() {
           var vds1 = $("#vds-overlay");
           var vds2 = $("#vds-wait");
           var exDiv = $('#exceptions23');
           var exMessages = $('#exMessages');
           if (exDiv.val() == null) return false;
           exDiv.hide();
           exMessages.empty();
           var answer = $.ajax({
               url: 'cp_ajax_validate_options',
               type: 'POST',
               async:    false,
               data: $('#jForm').serialize(),
               success: function(data) {
                   data.forEach(function(elem, index, array) {
                       exMessages.append("<span class='error-custom-message-incorrect'>" + elem + "</span>");
                   });
               }
           }).responseText;
           if (answer != "") {
               jVal.errors = true;
               exDiv.show();
           }
           vds1.hide();
           vds2.hide();
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
            jVal.balance();
            jVal.userLogin();
            jVal.userPassword();
            jVal.price();
            jVal.initialPrice();
            jVal.ajaxValidateOptions();
            jVal.sendIt();
       return false;
    });

    var price = $('#price');
    var initialPrice = $('#initialPrice');
    var balance = $('#balance');

    $('#number').change(jVal.number);
    $('#login').change(jVal.login);
    $('#name').change(jVal.userName);
    $('#surname').change(jVal.surName);
    $('#date').change(jVal.birthday);
    $('#userLogin').change(jVal.userLogin);
    $('#userPassword').change(jVal.userPassword);
    $('input[name="cb"]').change(jVal.tariff);
    price.bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    price.change(function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    price.change(jVal.price);
    initialPrice.bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    initialPrice.change(function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    initialPrice.change(jVal.initialPrice);
    balance.bind("change keyup input click", function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    balance.change(function() {
        if (this.value.match(/[^0-9]/g)) {
            this.value = this.value.replace(/[^0-9]/g, '');
        }
    });
    balance.change(jVal.balance);


});