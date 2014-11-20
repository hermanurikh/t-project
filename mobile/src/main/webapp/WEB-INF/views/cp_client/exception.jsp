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
    <script>window.page_data = {};</script>
    <script type="text/javascript" src="/locale/ru_RU/LC_MESSAGES/_global.json?c458e1acb7"></script>
    <script type="text/javascript" src="/locale/ru_RU/LC_MESSAGES/index.json?85a3d254c6"></script>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="../../../css/cp_file6.css"/>
</head>
<body class="locale-ru_RU">
<script type="text/javascript">
    function redirect() {
    location.href = "cp_client_main.jsp";
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
                        <li><a href="cp_client_profile.jsp">Профиль</a></li>
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

        <a href="../controllers/UserAllContractsServlet" class="main-menu-item">
            <i class="np_icon documents"></i>
            <span class="href_line">Контракты</span>
        </a>

        <a href="cp_client_balance.jsp" class="main-menu-item">
            <i class="np_icon balance"></i>
            <span class="href_line">Баланс</span>
        </a>

        <a href="cp_client_profile.jsp" class="main-menu-item">
                    <i class="np_icon crontab"></i>
                    <span class="href_line">Информация об аккаунте</span>
                </a>

        <div class="np_menu-line"></div>
        <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="../controllers/LogoutServlet"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a></div>
        &nbsp;</div></td><!--np_menu-->
    <td class="np_content">
        <script src="/js/underscore-min.js"></script>
        <script src="/js/d3.js"></script>
        <script src="/js/path.js"></script>
        <script src="/js/punycode.js"></script>
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

               <div class="info info_small fr info-last">
                               <!--<div class="info__header">-->
                                   <h2>В процессе работы приложения возникла ошибка. Текст исключения ниже:</h2>
                               <div>

                               <span class="small_signature" id="access_ip_info" style="display:"> <br> <br>
                               ${exception} <br>
                               ${exception.message}

                               </span>



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
