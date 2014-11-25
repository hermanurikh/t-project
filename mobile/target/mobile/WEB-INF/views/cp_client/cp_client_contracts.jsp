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
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/wait.js"></script>
</head>
<body class="locale-ru_RU">
<div id="vds-overlay" style="display: none;"></div>
<div id="vds-wait" style="display: none;">
    <div id="loader" class="loader-32 fl"></div>
    <div class="caption-wrap border-l">
        <div id="caption">
            Пожалуйста, подождите
        </div>
    </div>
</div>
<script type="text/javascript">
    function redirect() {
    location.href = "cp_client_main";
    }
    function showMenu(id) {
        $('#overlay_new').toggle();
        $('#'+id).toggle();
    }
</script>

<div class="header"><div style="width:902px;"><div><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
    <tr><td>

        <img src='/images/ajax-loader.gif' style='display:none'>

        <div class="main-header">
            <div class="inner-wrap">
                <div class="logotype"  onclick="redirect()">
                </div>
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

            <!--начало-->
            <div class="ui-widget-overlay ui-front" id="overlay_new" style="display:none;"></div>
            <c:forEach var="contract" items="${contractsUserList}">

            <div id="new_user${contract.id}" class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable ui-widget-shadow" tabindex="-1" role="dialog" aria-describedby="ftp_user_dialog" aria-labelledby="ui-id-7" style="position: absolute; height: auto; width: 640px; top: 99.5px; left: 360px; display: none;">
                <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-7" class="ui-dialog-title">${language.JSP_CONTRACTS_DETAILED_VIEW_CONTRACT} ${contract.number}</span>
                    <button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close" role="button" onclick="showMenu('new_user${contract.id}')" aria-disabled="false" title=""><span class="ui-button-icon-primary ui-icon ui-icon-closethick"></span><span class="ui-button-text"></span>
                    </button>
                </div>
                <div id="ftp_user_dialog" class="ui-dialog-content ui-widget-content" style="width: auto; min-height: 76px; max-height: none; height: auto;">
                    <div class="form-horizontal npp_ftp-dialog_middle">

                        <div class="form-horizontal">
                            <div class="control-group">

                                <div id="info-data" class="controls">
                                    <!-- user info -->
                                    <div id="table-description0">
                                        <c:set var="amount" value="${0}" />
                                        <h3>${language.JSP_CONTRACTS_DETAILED_VIEW_CONTRACT} ${contract.number}</h3>
                                        <table class="npp_info info-table">
                                            <tbody>
                                            <tr>
                                                <td>
                                                    <div class="help-underline help-underline-light">
                                                        <div class="help-underline-caption js-caption">${language.JSP_CONTRACTS_DETAILED_VIEW_TARIFF}</div>
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
                                                        <div class="help-underline-caption js-caption">${language.JSP_CONTRACTS_DETAILED_VIEW_PRICE} </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="info-data js-def">
                                                        <label>${contract.tariff.price} ${language.JSP_BALANCE_MONTHLY_CURRENCY}</label>
                                                        <c:set var="amount" value="${amount + contract.tariff.price}" />
                                                    </div>
                                                </td>
                                            </tr>


                                            </tbody>
                                        </table>
                                    </div>
                                    <div id="table-description1">
                                        <h3>${language.JSP_CONTRACTS_DETAILED_VIEW_OPTIONS}</h3>
                                        <table class="npp_info info-table">
                                            <tbody>
                                            <c:forEach var="option" items="${contract.options}">
                                                <tr>
                                                    <td>
                                                        <div class="help-underline help-underline-light">
                                                            <div class="help-underline-caption js-caption">${option.name}</div>
                                                        </div>
                                                    </td>
                                                    <td>
                                                        <div class="info-data js-def">
                                                            <label>${option.price} ${language.JSP_BALANCE_MONTHLY_CURRENCY}</label>
                                                            <c:set var="amount" value="${amount + option.price}" />
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>

                                            <tr>
                                                <td>
                                                    <div class="help-underline help-underline-light">
                                                        <div class="help-underline-caption js-caption">${language.JSP_CONTRACTS_DETAILED_VIEW_OVERALL_PRICE}</div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="info-data js-def">
                                                        <label>${amount} ${language.JSP_BALANCE_MONTHLY_CURRENCY}</label>
                                                    </div>
                                                </td>
                                            </tr>


                                            <tr>
                                                <td>
                                                    <div class="help-underline help-underline-light">
                                                        <div class="help-underline-caption js-caption">${language.JSP_CONTRACTS_DETAILED_VIEW_IS_BLOCKED}: </div>
                                                    </div>
                                                </td>
                                                <td>
                                                    <div class="info-data js-def">
                                                        <label>
                                                            <c:choose>
                                                                <c:when test="${contract.blocked}">
                                                                    <c:choose>
                                                                        <c:when test="${contract.employee != null}">
                                                                            ЗАБЛОКИРОВАН АДМИНИСТРАТОРОМ
                                                                        </c:when>
                                                                        <c:otherwise>Заблокирован</c:otherwise>
                                                                    </c:choose>
                                                                </c:when>
                                                                <c:otherwise>Активен</c:otherwise>
                                                            </c:choose>
                                                        </label>
                                                    </div>
                                                </td>
                                            </tr>
                                            <tr>
                                                <td>

                                                    <c:choose>
                                                        <c:when test="${!contract.blocked}">
                                                            <a href="cp_client_change_contract?contractId=${contract.id}"><span>${language.JSP_CONTRACTS_ACTION_CHANGE}</span> <br></a>
                                                        </c:when>
                                                    </c:choose>



                                                </td>
                                            </tr>





                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                        </div>



                    </div>
                </div>
            </div>
            </c:forEach>
            <!--конец-->



            <div class="info info_small fr info-last">

                <h2>Список контрактов</h2>
                                                                <div id="list_database">
                                                                    <div style="">
                                                                        <table class="ui-table ui-table-hover ui-table-striped" style="width:100%">
                                                                            <tbody>
                                                                                <tr class="ui-table-header">

                                                                                    <th class="header_s" style="width:80px;" id="table_header_database">Номер</th>
                                                                                    <th class="header_s" style="width:80px;" id="table_header_point_access">Тариф</th>
                                                                                    <th class="header_s" style="width:100px;" id="table_header_point">Опции</th>
                                                                                    <th class="header_s" style="width:100px;" id="table_header_point">Статус</th>
                                                                                    <th class="header_s" style="width:100px;" id="table_header_point">Действия</th>
                                                                                </tr>

                                                                                <!--начало элемента таблицы-->
                                                                                <c:forEach var="contract" items="${contractsUserList}">
                                                                                    <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 80px"><span>${contract.number}</span>
                                                                                            <br>
                                                                                        </td>

                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 80px;"><span>${contract.tariff.name}</span>
                                                                                        </td>
                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top;">
                                                                                            <div class="href_icon">
                                                                                                <c:forEach var="option" items="${contract.options}">
                                                                                                    <span>${option.name}</span>
                                                                                                    <br>
                                                                                                </c:forEach>
                                                                                            </div>
                                                                                        </td>
                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;">
                                                                                            <c:choose>
                                                                                                <c:when test="${contract.blocked}">
                                                                                                    <c:choose>
                                                                                                        <c:when test="${contract.employee != null}">
                                                                                                        <span style="color: rgb(204, 51, 51);">Заблокирован администратором</span>
                                                                                                        </c:when>
                                                                                                        <c:otherwise><span>Заблокирован</span></c:otherwise>
                                                                                                    </c:choose>
                                                                                                    </c:when>
                                                                                                <c:otherwise><span style="color: rgb(22, 128, 43)">Активен</span></c:otherwise>
                                                                                            </c:choose>

                                                                                        </td>


                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top;">
                                                                                            <div class="href_icon">
                                                                                                <a onclick="showMenu('new_user${contract.id}')"><span>Подробней</span><br> </a>
                                                                                                <c:choose>
                                                                                                    <c:when test="${contract.blocked}">
                                                                                                        <c:choose>
                                                                                                            <c:when test="${contract.employee == null}">
                                                                                                                <a href="cp_client_block_contract?contractId=${contract.id}" onclick="wait()"><span>Разблокировать</span> <br></a>
                                                                                                            </c:when>
                                                                                                        </c:choose>
                                                                                                    </c:when>
                                                                                                    <c:otherwise>
                                                                                                        <a href="cp_client_change_contract?contractId=${contract.id}" onclick="wait()"><span>Изменить</span> <br></a>
                                                                                                        <a href="cp_client_block_contract?contractId=${contract.id}" onclick="wait()"><span>Заблокировать</span> <br></a>
                                                                                                    </c:otherwise>
                                                                                                </c:choose>


                                                                                            </div>
                                                                                        </td>

                                                                                    </tr>
                                                                                </c:forEach>
                                                                                <!--конец элемента таблицы-->





                                                                            </tbody>
                                                                        </table>
                                                                    </div>
                                                                </div>


            </div>
            <div class="clear"></div>
            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>
</body>
</html>
