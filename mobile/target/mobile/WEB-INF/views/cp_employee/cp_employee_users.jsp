
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
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file6.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file9.css" />

</head>
<script>window.logins=[{"login":"gurikh","s":"475dc6f2020a987cf1344602b53e3531","page":"\/"}]</script><script> window.from_tab=0;</script><script>window.current_login="gurikh";</script><body class="locale-ru_RU">
<div class="lang-place" style="display:none;"><a href="?change_lang=ru">ru</a><a href="?change_lang=en">en</a><a href="?change_lang=de">de</a></div>
<div id="vds-overlay" style="display: none;"></div>


<script type="text/javascript">
    function redirect() {
    location.href = "cp_employee_main";
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
                        <li><a href="cp_employee_profile">Профиль</a></li>
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
<div class="middle"><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
    <tr>
        <td class="content np_menu">
    <div id="np_menu_id"  class="wrap-for-hover">

        <a href="cp_employee_contracts" class="main-menu-item">
            <i class="np_icon logmanager"></i>
            <span class="href_line">Контракты</span>
        </a>

        <a href="cp_employee_users" class="main-menu-item">
            <i class="np_icon managers"></i>
            <span class="href_line">Пользователи</span>
        </a>
        <a href="cp_employee_user_search" class="main-menu-item">
            <i class="np_icon domains"></i>
            <span class="href_line">Поиск пользователя</span>
        </a>

        <a href="cp_employee_tariffs" class="main-menu-item">
            <i class="np_icon tariff"></i>
            <span class="href_line">Тарифы</span>
        </a>

        <a href="cp_employee_options" class="main-menu-item">
            <i class="np_icon mysql"></i>
            <span class="href_line">Опции</span>
        </a>

        <div class="np_menu-line"></div>
        <c:url var="logoutAction" value="/j_spring_security_logout"></c:url>
        <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="${logoutAction}"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a></div>
        &nbsp;</div></td><!--np_menu-->
    <td class="np_content">



        <div class="primary_div npp_index">





            <fieldset class="primary_div_fieldset">
                <div class="main_caption">
                    Опции

                </div>
                <div class="element" style="display:;">
                    <table class="element__table element-main-table">
                        <tbody>
                        <tr>
                            <td class="element__block">

                                <div class="info-wrap">
                                    <div>
                                        <a href="cp_employee_new_user"><span>Создать нового пользователя</span></a>
                                    </div>

                                </div>
                            </td>

                        </tr>
                        </tbody>
                    </table>
                </div>

                <h2>Список пользователей</h2>
                <div id="list_database">
                    <div style="">
                        <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                        <tr class="ui-table-header">

                            <th class="header_s" style="width:100px;" id="table_header_database">Логин</th>
                            <th class="header_s" style="width:100px;" id="table_header_database">Баланс</th>
                            <th class="header_s" style="width:100px;" id="table_header_type">Фамилия</th>
                            <th class="header_s" style="width:100px;" id="table_header_point_access">Имя</th>
                            <th class="header_s" style="width:100px;" id="table_header_point">Действия</th>
                        </tr>

                        <!--начало элемента таблицы-->
                        <c:forEach var="user" items="${usersList}">
                            <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px"><span>${user.login}</span><br></td>
                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${user.balance}</span></td>
                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${user.surname}</span></td>
                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${user.name}</span></td>
                                <td class="simplecell" name="tcell" style="vertical-align: top;">
                                    <div class="href_icon">
                                        <a href="cp_employee_new_contract?id=${user.id}"><span>Создать контракт</span><br> </a>
                                        <a href="cp_employee_user_add_balance?id=${user.id}"><span>Пополнить баланс</span><br> </a>
                                        <a href="cp_employee_user_data_change?id=${user.id}"><span>Изменить</span><br> </a>
                                        <a href="cp_employee_user_delete?id=${user.id}"><span>Удалить</span> </a>
                                    </div>
                                </td>
                            </tr>
                        </c:forEach>
                        <!--конец элемента таблицы-->






                        </tbody>
                        </table></div></div>

            </fieldset>

            <div class="clear"></div>




            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>
</body>
</html>