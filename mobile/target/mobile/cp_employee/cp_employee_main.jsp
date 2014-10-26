
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
        <a href="cp_employee_user_find.html" class="main-menu-item">
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
</div><script>
		$(document).ready(function(){
			$( "#accordion" ).accordion({
				active: 0,
				create: function(e, ui){
					$('h2', ui.header).removeClass('no-link');
					$('i', ui.header).removeClass('ui-icon-arrow-down').addClass('ui-icon-arrow-up');

				},
				beforeActivate: function(e, ui){
					$('h2', ui.newHeader).removeClass('no-link');
					$('i', ui.newHeader).removeClass('ui-icon-arrow-down').addClass('ui-icon-arrow-up');

					$('h2', ui.oldHeader).addClass('no-link');
					$('i', ui.oldHeader).removeClass('ui-icon-arrow-up').addClass('ui-icon-arrow-down');

				}
			});
		});

	</script>



<div class="info info_small fr info-last">
    <div class="info__header">
        <h2>Добро пожаловать, Урих Герман Константинович!</h2>
    </div>
    <div class="info__body info__body_simple quota" style="display:none;">
        <table>
            <tbody>

            <tr>
                <td class='quota-name'><span class="ui-helper-inline">Цена:</span></td>
                <td><span class="ui-helper-inline quota__value">240 руб./мес.</span></td>
            </tr>


            <tr>
                <td colspan='2'>
                    <div class="quota__line" id="quota_PLAN_ITEM_LIMIT_TEXT" data-value="2" data-limit="49"></div>
                </td>
            </tr>
            <tr>
                <td class='quota-name'><span class="ui-helper-inline">Подключенные опции:</span></td>
                <td><span class="ui-helper-inline quota__value"></span></td>
            </tr>
            <tr>
                <td colspan='2'>
                    <div class="quota__line" id="quota_PLAN_ITEM_LIMIT_TEXT" data-value="5" data-limit="100"></div>
                </td>
            </tr>
            </tbody>
        </table>
        <a id="change_tarif" href="/tariff/" class="ui-button ui-button-primary ui-widget ui-state-default ui-corner-all ui-button-text-only" role="button" aria-disabled="false"><span class="ui-button-text">Изменить тариф</span></a>



    </div>
</div>

<div class="clear"></div>




<div id="test"></div>
</div>
</td></tr>
</table>
</div>

<script>
        (function(i,s,o,g,r,a,m){i['GoogleAnalyticsObject']=r;i[r]=i[r]||function(){
        (i[r].q=i[r].q||[]).push(arguments)},i[r].l=1*new Date();a=s.createElement(o),
        m=s.getElementsByTagName(o)[0];a.async=1;a.src=g;m.parentNode.insertBefore(a,m)
        })(window,document,'script','//www.google-analytics.com/analytics.js','ga');
        ga('create', 'UA-31254085-3', 'timeweb.ru');
        ga('send', 'pageview');
      </script>

<!-- Yandex.Metrika counter -->
<script type="text/javascript">
                    (function (d, w, c) {
                        (w[c] = w[c] || []).push(function() {
                            try {
                                w.yaCounter22579897 = new Ya.Metrika({id:22579897
                                        });
                            } catch(e) { }
                        });

                        var n = d.getElementsByTagName("script")[0],
                            s = d.createElement("script"),
                            f = function () { n.parentNode.insertBefore(s, n); };
                        s.type = "text/javascript";
                        s.async = true;
                        s.src = (d.location.protocol == "https:" ? "https:" : "http:") + "//mc.yandex.ru/metrika/watch.js";

                        if (w.opera == "[object Opera]") {
                            d.addEventListener("DOMContentLoaded", f, false);
                        } else { f(); }
                    })(document, window, "yandex_metrika_callbacks");
                    </script>
<noscript><div><img src="//mc.yandex.ru/watch/22579897" style="position:absolute; left:-9999px;" alt="" /></div></noscript>
<!-- /Yandex.Metrika counter -->
<script type="text/javascript" src="/js/awstats_misc_tracker.js"></script><noscript><img src="/js/awstats_misc_tracker.js.php?nojs=y" height=20 width=0 border=0></noscript>
</body>
</html>