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

            <div class="npp_balance_header element">
            			<table class="element__table element-main-table">
            				<tbody><tr>
            					<td id="npp_balance_state-actions" class="element__block">
            						<a href="cp_client_increase_balance" class="link-unstyle link-color-parent">
            							<table class="element__table">
            								<tbody><tr>
            									<td class="element__td-img"><img src="${pageContext.request.contextPath}/images/balance-pay.png"></td>
            									<td class="element__simple">
            										<div>
            											<div class="npp_balance_state-block state_default" style="display: block;">
            												<span class="link">Оплатить услуги</span>
            												<div class="npp_balance_state-desc small-text">Пополнить баланс на 100 рублей</div>
            											</div>
            										</div>

            										<div class="npp_balance_state-block state_suspended" style="display: none;">
            											<div class="muted-text">
            												<table>
            													<tbody><tr>
            														<td>
            															<a href="cp_client_increase_balance">Оплатить услуги</a>
            														</td>
            														<td>&nbsp;/&nbsp;</td>
            														<td>
            															<a href="#/delay">Отложенный платёж</a>
            														</td>
            													</tr>
            													<tr>
            														<td>
            															<div class="npp_balance_state-desc small-text">
            																Пополнить баланс на 500 рублей
            															</div>
            														</td>
            														<td></td>
            														<td>
            															<div class="npp_balance_state-desc small-text">
            																на 10 дней
            																<small id="delay_pay_sum"></small>
            															</div>
            														</td>
            													</tr>
            												</tbody></table>
            											</div>
            										</div>
            									</td>
            								</tr>
            							</tbody></table>
            						</a>
            					</td>
            					<td class="element__vline">&nbsp;</td>
            					<td class="element__vline-right"></td>
            					<td id="npp_balance_state-desc" class="element__block">
            						<div class="npp_balance_state-block state_default" style="display: none;">
            							<div class="npp_balance_state-title small-text">Текущий баланс</div>
            							<h3 class="npp_balance_state-desc"></h3>
            						</div>

            						<div class="npp_balance_state-block state_suspended" style="display: block;">
            							<div class="npp_balance_state-title small-text important-text">Текущий баланс</div>
            							<h3 class="npp_balance_state-desc important-text">${currentUserU.balance} р.</h3>
            						</div>
            					</td>
            				</tr>
            			</tbody></table>
            		</div>



            <div class="clear"></div>
            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>
</body>
</html>