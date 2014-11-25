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
</head>
<body class="locale-ru_RU">
<script type="text/javascript">
    function redirect() {
    location.href = "cp_client_main";
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
                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>
                                                                                            <c:choose>
                                                                                                <c:when test="${contract.blocked}">
                                                                                                    <c:choose>
                                                                                                        <c:when test="${contract.employee != null}">
                                                                                                        Заблокирован администратором
                                                                                                        </c:when>
                                                                                                        <c:otherwise>Заблокирован</c:otherwise>
                                                                                                    </c:choose>
                                                                                                    </c:when>
                                                                                                <c:otherwise>Активен</c:otherwise>
                                                                                            </c:choose>
                                                                                        </span>
                                                                                        </td>


                                                                                        <td class="simplecell" name="tcell" style="vertical-align: top;">
                                                                                            <div class="href_icon">
                                                                                                <a href="cp_client_contract_details?contractId=${contract.id}"><span>Подробней</span><br> </a>
                                                                                                <c:choose>
                                                                                                    <c:when test="${contract.blocked}">
                                                                                                        <c:choose>
                                                                                                            <c:when test="${contract.employee == null}">
                                                                                                                <a href="cp_client_block_contract?contractId=${contract.id}"><span>Разблокировать</span> <br></a>
                                                                                                            </c:when>
                                                                                                        </c:choose>
                                                                                                    </c:when>
                                                                                                    <c:otherwise>
                                                                                                        <a href="cp_client_change_contract?contractId=${contract.id}"><span>Изменить</span> <br></a>
                                                                                                        <a href="cp_client_block_contract?contractId=${contract.id}"><span>Заблокировать</span> <br></a>
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
