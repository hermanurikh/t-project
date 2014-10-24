
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
<script>window.logins=[{"login":"gurikh","s":"475dc6f2020a987cf1344602b53e3531","page":"\/"}]</script><script> window.from_tab=0;</script><script>window.current_login="gurikh";</script><body class="locale-ru_RU">
<div class="lang-place" style="display:none;"><a href="?change_lang=ru">ru</a><a href="?change_lang=en">en</a><a href="?change_lang=de">de</a></div>
<div id="vds-overlay" style="display: none;"></div>
<div id="vds-wait" style="display: none;">
    <div id="loader" class="loader-32 fl"></div>
    <div class="caption-wrap border-l">
        <div id="caption">
            Пожалуйста, подождите
        </div>
    </div>
</div>

<script>
    function recheck( el, id ){
  if ( el.checked ){
    var el2 = document.getElementById( id );
    if ( el2.checked ) el2.checked = false;
  }
}

</script>


<div class="header"><div style="width:902px;"><div><table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
    <tr><td>

        <img src='/images/ajax-loader.gif' style='display:none'>

        <div class="main-header">
            <div class="inner-wrap">
                <div class="logotype">
                </div>
                <div class="nav-wrap">
                    <ul class="nav">
                        <li><a href="/news/">Профиль</a></li>
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
                            <div class="username">gurikh<span class="shad">&nbsp;</span></div>
                            <div class="user-balance">1 р.</div>
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
        <a href="/domains/" class="main-menu-item">
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
        <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="../login.html"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a></div>
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

            <form action="../controllers/NewOptionServlet" method="POST" accept-charset="CP1252">
            <div class="js-body info__body">
                <h2 class="js-h">Создание новой опции</h2><div>
                <div class="js-table form-horizontal support-issue-form">

                    <div class="js-row control-group">
                        <label class="js-caption control-label">Название опции:</label>
                        <div class="js-td controls jq-validate-container">


                            <input type="text" class="js-input big-input" name="name">
                        </div>
                    </div>
                    <div class="js-row control-group">
                    <label class="js-caption control-label">Ежемесячная цена (в рублях):</label>
                    <div class="js-td controls jq-validate-container">
                        <input type="text" class="js-input big-input" name = "price">
                    </div>
                </div>
                    <div class="js-row control-group">
                        <label class="js-caption control-label">Цена подключения (в рублях):</label>
                        <div class="js-td controls jq-validate-container">
                            <input type="text" class="js-input big-input" name="initialPrice">
                        </div>
                    </div>
                    <div class="js-row control-group">
                    <label class="js-caption control-label"></label>

                </div>

                    <h2 class="js-h">Выберите опции, которые будут обязательны для данной опции</h2><div>



                    <div id="list_database">
                        <div style="">
                            <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                            <tr class="ui-table-header">

                                <th class="header_s_checkbox" width="12" align="center"></th>
                                <th class="header_s" style="width:150px;" id="table_header_database">Опция</th>
                                <th class="header_s" style="width:100px;" id="table_header_type">Цена</th>
                                <th class="header_s" style="width:100px;" id="table_header_point_access">Цена подключения</th>
                            </tr>

                            <!--начало элемента таблицы-->
                            <c:forEach var="option" items="${optionsList}">
                                <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                    <td name="tcell" class="simplecell_checkbox" align="left"><input type="checkbox" onchange="recheck (this, '${option.id}600' );" name="cb" id=${option.id}300 value=${option.id}></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>${option.name}</span><br></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${option.price}</span></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${option.initialPrice}</span></td>

                                </tr>
                            </c:forEach>
                            <!--конец элемента таблицы-->
                            </tbody>
                            </table></div></div>


                    <h2 class="js-h">Выберите опции, которые будут несовместимы с данной опцией</h2><div>



                    <div id="list_database">
                        <div style="">
                            <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                            <tr class="ui-table-header">

                                <th class="header_s_checkbox" width="12" align="center"></th>
                                <th class="header_s" style="width:150px;" id="table_header_database">Опция</th>
                                <th class="header_s" style="width:100px;" id="table_header_type">Цена</th>
                                <th class="header_s" style="width:100px;" id="table_header_point_access">Цена подключения</th>
                            </tr>

                            <!--начало элемента таблицы-->
                            <c:forEach var="option" items="${optionsList}">
                                <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                    <td name="tcell" class="simplecell_checkbox" align="left"><input type="checkbox" onchange="recheck (this, '${option.id}300' );" name="cb2" id=${option.id}600 value=${option.id}></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>${option.name}</span><br></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${option.price}</span></td>
                                    <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${option.initialPrice}</span></td>

                                </tr>
                            </c:forEach>
                            <!--конец элемента таблицы-->
                            </tbody>
                            </table></div></div>



                    <div class="js-row control-group">
                    <label class="js-caption control-label"></label>
                    <div class="js-td controls jq-validate-container">
                        <input type="submit" value="Создать опцию" />
                        </div>
                </div></div>
            </div></div>
            </form>

            <div class="clear"></div>




            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>



</body>
</html>
