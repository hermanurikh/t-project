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

    <script type="text/javascript" src="/locale/ru_RU/LC_MESSAGES/_global.json?c458e1acb7"></script>
    <script type="text/javascript" src="/locale/ru_RU/LC_MESSAGES/index.json?85a3d254c6"></script>
    <link rel="stylesheet" type="text/css" href="../css/cp_file1.css" />
    <link rel="stylesheet" type="text/css" href="../css/cp_file2.css" />
    <link rel="stylesheet" type="text/css" href="../css/cp_file3.css" />
    <link rel="stylesheet" type="text/css" href="../css/cp_file4.css" />
    <link rel="stylesheet" type="text/css" href="../css/cp_file5.css" />
    <link rel="stylesheet" type="text/css" href="../css/cp_file6.css" />

    <script type="text/javascript" src="../scripts/jquery.js"></script>

</head>


<body class="locale-ru_RU">
<div class="lang-place" style="display:none;"><a href="?change_lang=ru">ru</a><a href="?change_lang=en">en</a><a href="?change_lang=de">de</a>
</div>
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
        function recheck(id) {
            document.getElementById(id).value = "";
        }
    </script>

<script type="text/javascript">
    function redirect() {
    location.href = "cp_employee_main.jsp";
    }
</script>
<script>
    $(document).ready(function()
    {
    if (document.getElementById('found').value == "false") {
    var o = document.getElementById('exceptions');
    o.style.display = 'block';
    }
    });
</script>

<div class="header">
    <div style="width:902px;">
        <div>
            <table border="0" cellspacing="0" cellpadding="0" class="wrap-table" style="width:900px;">
                <tr>
                    <td>



                        <div class="main-header">
                            <div class="inner-wrap">
                                <div class="logotype"  onclick="redirect()">
                                </div>
                                <div class="nav-wrap">
                                    <ul class="nav">
                                        <li><a href="cp_employee_profile.jsp">Профиль</a></li>
                                        <!--<li class="last-child"><a href="mailto:herman.urikh@aengel.ru">Служба поддержки</a></li>-->
                                    </ul>
                                </div>
                            </div>
                            <div id="isValid" style="display:none">
                                                        <input id="found" value=${found}>

                                                    </div>
                            <div class="right">
                                <div class="account-selector dobble">
                                    <div class="main">
                                        <div class="info">
                                            <!--<a id="avatar_thumb" href="/info" class="avatar no-avatar thumbnail-small"></a>-->
                                            <div class="username">${currentUserU.login}<span class="shad">&nbsp;</span>
                                            </div>
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

                    <a href="../controllers/EmployeeAllContractsServlet" class="main-menu-item">
                        <i class="np_icon logmanager"></i>
                        <span class="href_line">Контракты</span>
                    </a>

                    <a href="../controllers/UsersServlet" class="main-menu-item">
                        <i class="np_icon managers"></i>
                        <span class="href_line">Пользователи</span>
                    </a>
                    <a href="cp_employee_user_find.jsp" class="main-menu-item">
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
                    <div><a class="js-return-to-old return-to-old ui-corner-all ui-button-text-only" href="../controllers/LogoutServlet"><i class="icon-back-arrow"></i><span>Выход из аккаунта</span></a>
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

                    <form action="../controllers/EmployeeFindUserServlet" method="post" accept-charset="CP1252">
                        <div class="js-body info__body">
                            <h2 class="js-h">Поиск пользователя</h2>

                            <div class="js-row control-group" id="exceptions" style="display:none"><div>

                                                                                <span class="small_signature">Пользователь не найден.</span> <br>

                                                                            </div>
                                                                            </div>



                            <div>
                                <div class="js-table form-horizontal support-issue-form">

                                    <div class="js-row control-group">
                                        <label class="js-caption control-label">Введите номер контракта:</label>
                                        <div class="js-td controls jq-validate-container">
                                            <input type="text" class="js-input big-input" id="nameFind" name="number" onfocus="recheck ( 'loginFind' );">
                                        </div>
                                    </div>


                                    <div class="js-row control-group">
                                        <label class="js-caption control-label">Или логин:</label>
                                        <div class="js-td controls jq-validate-container">
                                            <input type="text" class="js-input big-input" id="loginFind" name="login" onfocus="recheck ( 'nameFind' );">
                                        </div>
                                    </div>

                                    <div class="js-row control-group">
                                        <label class="js-caption control-label"></label>

                                    </div>
                                    <div class="js-row control-group">
                                        <label class="js-caption control-label"></label>
                                        <div class="js-td controls jq-validate-container">
                                            <span><input type="submit" value="Найти" /></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>

                    <div class="clear"></div>




                    <div id="test"></div>
                </div>
            </td>
        </tr>
    </table>
</div>



</body>

</html>