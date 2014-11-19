<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Панель управления аккаунтом.</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file6.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file9.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validate.js"></script>
</head>
<body class="locale-ru_RU">
<script type="text/javascript">
    function redirect() {
    location.href = "cp_client_main";
    }
</script>
<script>
    $(document).ready(function()
    {
    if (document.getElementById('areExceptions').value == "true") {
    var o = document.getElementById('checkedTariff');
    o.style.display = 'block';
    }
    });
</script>


<div class="header"><div style="width:902px;"><div><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
    <tr><td>

        <img src='/images/ajax-loader.gif' style='display:none'>

        <div class="main-header">
            <div class="inner-wrap">
                <div class="logotype"  onclick="redirect()">
                </div>

                <div style="display:none"> <input id="areExceptions" value=${areExceptions} > </div>
                <div class="nav-wrap">


                    <ul class="nav">
                        <li><a href="cp_client_profile">Профиль</a></li>
                        <li class="last-child"><a href="mailto:herman.urikh@aengel.ru">Служба поддержки</a></li>
                    </ul>
                </div>
            </div>
            <div class="right">

                <div class="account-selector dobble">
                    <div class="main">
                        <div class="info">
                            <!--<a id="avatar_thumb" href="/info" class="avatar no-avatar thumbnail-small"></a>-->
                            <div class="username">${currentUserU.login}<span class="shad">&nbsp;</span></div>
                            <div class="user-balance">${currentUserU.balance} р.</div>
                        </div>
                        <div class="slide-down" style="display:none;">
                            <div style="display:block;">
                                <ul></ul>
                                <div class="buttons-wrap">
                                    <a href="/info/" class="account-add link">
                                        <span>Профиль аккаунта</span>
                                    </a>
                                    <a href="/logout/" class="exit link">
                                        <span>Выход</span>
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div
                            ><div class="triangle"><div></div>
                </div>
                </div>


    </td></tr></table></div></div></div>
<div class="middle"><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;"><tr><td class="content np_menu">
    <div id="np_menu_id"  class="wrap-for-hover">

        <a href="cp_client_contracts" class="main-menu-item">
            <i class="np_icon documents"></i>
            <span class="href_line">Контракты</span>
        </a>

        <a href="cp_client_balance" class="main-menu-item">
            <i class="np_icon balance"></i>
            <span class="href_line">Баланс</span>
        </a>

        <a href="cp_client_profile" class="main-menu-item">
            <i class="np_icon crontab"></i>
            <span class="href_line">Информация об аккаунте</span>
        </a>

        <div class="np_menu-line"></div>
        <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
        <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="${logoutAction}"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a></div>
        &nbsp;</div></td><!--np_menu-->
    <td class="np_content">
        <div class="primary_div npp_index">

            <div style="display:none;">
                <div id="template_HotActionsDialog">
                    <div class="form-horizontal npp_index-hot_actions_dialog">
                        <div class="control-group">
                            <table class="ui-table ui-table-striped ui-table-expanded dialog" id="tabl">
                            </table>
                        </div>

                        <div class="form-actions">
                            <button class="btn" id="save_button">Сохранить настройки</button>
                        </div>
                    </div>
                </div>
            </div>

             <div class="primary_div npp_index">



                        <form action="cp_client_contract_change_options" id="jForm" method="GET" accept-charset="CP1252">
                            <div class="js-body info__body">
                                <h2 class="js-h">Изменение контракта с номером ${contract.number}</h2><div>

                                <div class="js-table form-horizontal support-issue-form">


                                    <h2 class="js-h">Выберите тариф для контракта </h2><div>
                                    <span class="error-custom-message" id="error-custom-message-3">Пожалуйста, выберите один из тарифов.</span>
                                    <small id="checkedTariff" style="display:none">Пожалуйста, отметьте один из тарифов.</small>



                                    <div id="list_database">
                                        <div style="">
                                            <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                                            <tr class="ui-table-header">

                                                <th class="header_s_checkbox" width="12" align="center"><!--<input id="main_checkbox" type="radio">--></th>
                                                <th class="header_s" style="width:150px;" id="table_header_database">Тариф</th>
                                                <th class="header_s" style="width:100px;" id="table_header_type">Цена</th>
                                            </tr>

                                            <!--начало элемента таблицы-->
                                            <c:forEach var="tariff" items="${tariffsList}">
                                                <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                                    <td name="tcell" class="simplecell_checkbox" align="left"><input type="radio" id="cb" name="cb" value=${tariff.id}></td>
                                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>${tariff.name}</span><br></td>
                                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${tariff.price}</span></td>

                                                </tr>
                                            </c:forEach>
                                            <!--конец элемента таблицы-->
                                             </tbody>
                                            </table></div></div>


                                    <div class="js-row control-group">
                                        <label class="js-caption control-label"></label>

                                    </div><div class="js-row control-group">
                                    <label class="js-caption control-label"></label>
                                    <div class="js-td controls jq-validate-container">
                                        <input type="submit" id="send" value="Выбрать опции для тарифа" />
                                    </div>
                                </div></div>
                            </div></div>
                        </form>

                        <div class="icon-wrap">
                                                    <img src="${pageContext.request.contextPath}/images/ip-icon.png">
                                                    <a href="cp_client_block_contract?contractNumber=${contract.number}">${action}</a>
                                                    <br>
                                                    <small>В данный момент блокировка ${paramIsBlocked}.</small>
                                                </div>


            <div class="clear"></div>
            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>
</body>
</html>