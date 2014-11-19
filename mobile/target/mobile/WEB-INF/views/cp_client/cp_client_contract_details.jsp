<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=utf-8" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
        <%@ page isELIgnored="false" %>

            <head>
                <meta http-equiv="X-UA-Compatible" content="IE=8,9,10">
                <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
                <title>Панель управления аккаунтом.</title>
                <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file1.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file2.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file3.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file4.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file5.css"/>
                <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file6.css"/>
            </head>

            <body class="locale-ru_RU">
                <script type="text/javascript">
                    function redirect() {
                        location.href = "cp_client_main";
                    }
                </script>

                <div class="header">
                    <div style="width:902px;">
                        <div>
                            <table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
                                <tr>
                                    <td>

                                        <img src='/images/ajax-loader.gif' style='display:none'>

                                        <div class="main-header">
                                            <div class="inner-wrap">
                                                <div class="logotype" onclick="redirect()">
                                                </div>
                                                <div class="nav-wrap">
                                                    <ul class="nav">
                                                        <li><a href="cp_client_profile">Профиль</a>
                                                        </li>
                                                        <li class="last-child"><a href="mailto:herman.urikh@aengel.ru">Служба поддержки</a>
                                                        </li>
                                                    </ul>
                                                </div>
                                            </div>
                                            <div class="right">
                                                <div class="account-selector dobble">
                                                    <div class="main">
                                                        <div class="info">
                                                            <!--<a id="avatar_thumb" href="/info" class="avatar no-avatar thumbnail-small"></a>-->
                                                            <div class="username">${currentUserU.login}<span class="shad">&nbsp;</span>
                                                            </div>
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
                                                    </div>
                                                    <div class="triangle">
                                                        <div></div>
                                                    </div>
                                                </div>


                                    </td>
                                </tr>
                            </table>
                            </div>
                            </div>
                        </div>
                        <div class="middle">
                            <table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
                                <tr>
                                    <td class="content np_menu">
                                        <div id="np_menu_id" class="wrap-for-hover">

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
                                            <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="${logoutAction}"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a>
                                            </div>
                                            &nbsp;</div>
                                    </td>
                                    <!--np_menu-->
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


                                            <fieldset class="primary_div_fieldset">




                                                <div class="form-horizontal">
                                                    <div class="control-group">

                                                        <div id="info-data" class="controls">
                                                            <!-- user info -->
                                                            <div id="table-description0">
                                                                <h3>Просмотр контракта ${contract.number}</h3>
                                                                <table class="npp_info info-table">
                                                                    <tbody>
                                                                        <tr>
                                                                            <td>
                                                                                <div class="help-underline help-underline-light">
                                                                                    <div class="help-underline-caption js-caption">Подключенный тариф</div>
                                                                                </div>
                                                                            </td>
                                                                            <td>
                                                                                <div class="info-data js-def">
                                                                                    <label>${contract.tariff.name}</label>
                                                                                </div>
                                                                            </td>
                                                                        </tr>
                                                                        <tr>
                                                                            <td>
                                                                                <div class="help-underline help-underline-light">
                                                                                    <div class="help-underline-caption js-caption">Стоимость, в месяц</div>
                                                                                </div>
                                                                            </td>
                                                                            <td>
                                                                                <div class="info-data js-def">
                                                                                    <label>${contract.tariff.price}</label>
                                                                                </div>
                                                                            </td>
                                                                        </tr>


                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                            <div id="table-description1">
                                                                <h3>Подключенные опции</h3>
                                                                <table class="npp_info info-table">
                                                                    <tbody>
                                                                        <c:forEach var="option" items="${optionsList}">
                                                                            <tr>
                                                                                <td>
                                                                                    <div class="help-underline help-underline-light">
                                                                                        <div class="help-underline-caption js-caption">${option.name}</div>
                                                                                    </div>
                                                                                </td>
                                                                                <td>
                                                                                    <div class="info-data js-def">
                                                                                        <label>${option.price} р./мес.</label>
                                                                                    </div>
                                                                                </td>
                                                                            </tr>
                                                                        </c:forEach>

                                                                        <tr>
                                                                            <td>
                                                                                <div class="help-underline help-underline-light">
                                                                                    <div class="help-underline-caption js-caption">Суммарная стоимость тарифа с опциями</div>
                                                                                </div>
                                                                            </td>
                                                                            <td>
                                                                                <div class="info-data js-def">
                                                                                    <label>${totalAmount} р./мес.</label>
                                                                                </div>
                                                                            </td>
                                                                        </tr>


                                                                        <tr>
                                                                            <td>
                                                                                <div class="help-underline help-underline-light">
                                                                                    <div class="help-underline-caption js-caption">Состояние контракта: </div>
                                                                                </div>
                                                                            </td>
                                                                            <td>
                                                                                <div class="info-data js-def">
                                                                                    <label>${isItBlocked}</label>
                                                                                </div>
                                                                            </td>
                                                                            </tr>
                                                                            <tr>
                                                                                <td>

                                                                                   <a href="cp_client_change_contract?contractId=${contract.id}"><span>Изменить</span> <br></a>

                                                                                   </td>
                                                                                    </tr>





                                                                    </tbody>
                                                                </table>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </fieldset>


                                        </div>
                                        <div class="clear"></div>
                                        <div id="test"></div>
                        </div>
                        </td>
                        </tr>
                        </table>
                    </div>
            </body>

</html>