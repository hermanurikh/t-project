
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
    <link rel="stylesheet" type="text/css" href="../css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file6.css"/>

</head>
<body class="locale-ru_RU">
<div class="lang-place" style="display:none;"><a href="?change_lang=ru">ru</a><a href="?change_lang=en">en</a><a href="?change_lang=de">de</a></div>
<div id="vds-overlay" style="display: none;"></div>
<script type="text/javascript">
    function redirect() {
    location.href = "cp_employee_main.html";
    }
</script>





<div class="header"><div style="width:902px;"><div><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
    <tr><td>

        <img src='/images/ajax-loader.gif' style='display:none'>

        <div class="main-header">
            <div class="inner-wrap">
                <div class="logotype" onclick="redirect()">
                    </div>
                <div class="nav-wrap">
                    <ul class="nav">
                        <li><a href="cp_employee_profile.jsp">Профиль</a></li>
                        <!--<li class="last-child"><a href="mailto:herman.urikh@aengel.ru">Служба поддержки</a></li>-->
                    </ul>
                </div>
            </div>
            <div class="right">
                <script>var cp_pages = new Array();cp_pages.push({'caption': 'Домены и поддомены', 'name': 'domains'});cp_pages.push({'caption': 'Сайты', 'name': 'sites'});cp_pages.push({'caption': 'Каталог CMS', 'name': 'cms'});cp_pages.push({'caption': 'Файловый менеджер', 'name': 'fileman'});cp_pages.push({'caption': 'Базы данных MySQL', 'name': 'mysql'});cp_pages.push({'caption': 'Почтовый менеджер', 'name': 'mailman'});cp_pages.push({'caption': 'Пользователи ПУ/FTP', 'name': 'managers'});cp_pages.push({'caption': 'Резервные копии', 'name': 'backup'});cp_pages.push({'caption': 'Crontab', 'name': 'crontab'});cp_pages.push({'caption': 'Jabber сервер', 'name': 'jabber'});cp_pages.push({'caption': 'Безопасность', 'name': 'security'});cp_pages.push({'caption': 'Логи', 'name': 'logmanager'});cp_pages.push({'caption': 'Дополнительные услуги', 'name': 'services'});cp_pages.push({'caption': 'Тариф', 'name': 'tariff'});cp_pages.push({'caption': 'Финансы', 'name': 'balance'});cp_pages.push({'caption': 'Документы', 'name': 'documents'});cp_pages.push({'caption': 'Уведомления', 'name': 'sms'});cp_pages.push({'caption': 'Бонусы', 'name': 'bonuses'});</script>
                <div class="account-selector dobble">
                <div class="main">
                    <div class="info">
                        <!--<a id="avatar_thumb" href="/info" class="avatar no-avatar thumbnail-small"></a>-->
                        <div class="username">${currentUserU.login}<span class="shad">&nbsp;</span></div>

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

        <a href="../controllers/EmployeeAllContractsServlet" class="main-menu-item">
            <i class="np_icon logmanager"></i>
            <span class="href_line">Контракты</span>
        </a>

        <a href="../controllers/UsersServlet" class="main-menu-item">
            <i class="np_icon managers"></i>
            <span class="href_line">Пользователи</span>
        </a>
        <a href="../WEB-INF/views/cp_employee/cp_employee_user_find.jsp" class="main-menu-item">
            <i class="np_icon domains"></i>
            <span class="href_line">Поиск пользователя</span>
        </a>

        <a href="../controllers/TariffsServlet" class="main-menu-item">
            <i class="np_icon tariff"></i>
            <span class="href_line">Тарифы</span>
        </a>

        <a href="../controllers/AllOptionsServlet" class="main-menu-item">
            <i class="np_icon mysql"></i>
            <span class="href_line">Опции</span>
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


<fieldset class="primary_div_fieldset">




                <div class="form-horizontal">
                    <div class="control-group">

                        <div id="info-data" class="controls">
                            <!-- user info -->
                            <div id="table-description0"><h1>Профиль аккаунта ${currentUserU.login}</h1><h3>${currentUserU.name} ${currentUserU.surname}</h3><table class="npp_info info-table"><tbody><tr>
                                <td>
                                    <div class="help-underline help-underline-light">
                                        <div class="help-underline-caption js-caption">Дата рождения</div>
                                    </div>
                                </td>
                                <td>
                                    <div class="info-data js-def"><label>${currentUserU.birthday}</label></div>
                                </td>
                            </tr><tr>
                                <td>
                                    <div class="help-underline help-underline-light">
                                        <div class="help-underline-caption js-caption">Паспорт</div>
                                    </div>
                                </td>
                                <td>
                                    <div class="info-data js-def"><label>${currentUserU.passport}</label></div>
                                </td>
                            </tr></tbody></table></div><div id="table-description1"><h3>Контактная информация</h3><table class="npp_info info-table"><tbody><tr>
                            <td>
                                <div class="help-underline help-underline-light">
                                    <div class="help-underline-caption js-caption">Эл. почта</div>
                                </div>
                            </td>
                            <td>
                                <div class="info-data js-def"><label><a class="link link-inverse" href="mailto:${currentUserU.email}">${currentUserU.email}</a></label></div>
                            </td>
                        </tr><tr>
                            <td>
                                <div class="help-underline help-underline-light">
                                    <div class="help-underline-caption js-caption">Почтовый адрес</div>
                                </div>
                            </td>
                            <td>
                                <div class="info-data js-def"><label>${currentUserU.address}</label></div>
                            </td>
                        </tr></tbody></table></div></div>
                    </div>
                </div>
            </fieldset>


<div class="clear"></div>




<div id="test"></div>
</div>
</td></tr>
</table>
</div>




<script type="text/javascript" src="/js/awstats_misc_tracker.js"></script><noscript><img src="/js/awstats_misc_tracker.js.php?nojs=y" height=20 width=0 border=0></noscript>
</body>
</html>
