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
    <script type="text/javascript" src="../scripts/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="../css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="../css/cp_file6.css"/>
</head>
<body class="locale-ru_RU">
<script type="text/javascript">
    function redirect() {
    location.href = "cp_client_main.jsp";
    }
</script>

<script>
function changeDiv( el ) {
if (document.getElementById(el).style.display == 'none' ) {
document.getElementById(el).style.display = 'block';
}
}
</script>
<script>
    $(document).ready(function()
    {
    if (document.getElementById('areExceptions').value == "true") {
    var o = document.getElementById('exceptions');
    o.style.display = 'block';
    }
    });
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
                <div id="isValid" style="display:none">
                                            <input id="areExceptions" value=${areExceptions}>

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

                <form action="../controllers/UserContractFinalChangeServlet" method="POST" accept-charset="CP1252">
                                <div class="js-body info__body">
                                    <h2 class="js-h">Изменение контракта</h2><div>
                                    <div class="js-table form-horizontal support-issue-form">

                                        <div class="js-row control-group">

                                        <span class="small_signature">Номер контракта: ${contractNumber}</span>

                                        </div>
                                        <div class="js-row control-group" style="display:none;">
                                            <label class="js-caption control-label">Id пользователя и тарифа:</label>
                                            <div class="js-td controls jq-validate-container">
                                                <input type="text" class="js-input big-input" name = "userID" value=${userId}>
                                                <input type="text" class="js-input big-input" name = "tariffID" value=${tariffId}>
                                            </div>
                                        </div>

                                        <div class="js-row control-group" id="exceptions" style="display:none">
                                                                    <h2 class="js-h">В процессе выбора опций возникли ошибки!</h2><div>
                                                                    <c:forEach var="ex" items="${exceptionsList}">
                                                                    <span class="small_signature">${ex.message}</span> <br>
                                                                    </c:forEach>
                                                                </div>
                                                                </div>

                                        <h2 class="js-h">Выберите опции для тарифа ${tariff.name}</h2><div>
                                        <span class="small_signature">Щелкните по опции для просмотра ее необходимых и несовместимых опций.</span>



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
                                                    <tr name="trow" class="ui-table-data-row ui-state-even ui-selected" onclick="changeDiv('${option.id}150')">

                                                        <td name="tcell" class="simplecell_checkbox" align="left"><input type="checkbox" name="cb" id=${option.id} value=${option.id}></td>
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

                                        </div><div class="js-row control-group">
                                        <label class="js-caption control-label"></label>
                                        <div class="js-td controls jq-validate-container">
                                            <input type="submit" value="Изменить контракт" />
                                        </div>
                                    </div></div>
                                </div></div>
                            </form>




                            <c:forEach var="option" items="${optionsList}">
                            <div class="js-table form-horizontal support-issue-form" id="${option.id}150" style="display:none" onclick="document.getElementById('${option.id}150').style.display='none'">
                                <h2 class="js-h">Необходимые опции</h2><div>

                                <div id="list_database3">
                                    <div style="">
                                        <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                                    <tr class="ui-table-header">


                                        <th class="header_s" style="width:150px;" id="table_header_database">Опция</th>
                                        <th class="header_s" style="width:100px;" id="table_header_type">Цена</th>
                                        <th class="header_s" style="width:100px;" id="table_header_point_access">Цена подключения</th>
                                    </tr>

                                    <!--начало элемента таблицы-->
                                    <c:forEach var="optionTogether" items="${option.optionsTogether}">
                                    <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>${optionTogether.name}</span><br></td>
                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${optionTogether.price}</span></td>
                                        <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${optionTogether.initialPrice}</span></td>
                                    </tr>
                                    </c:forEach>

                                    <!--конец элемента таблицы-->
                                    </tbody>
                                    </table></div></div>


                                <div class="js-row control-group">
                                    <label class="js-caption control-label"></label>

                                </div><div class="js-row control-group">
                                <label class="js-caption control-label"></label>

                            </div></div>

                                <h2 class="js-h">Несовместимые опции</h2><div>

                                <div id="list_database4">
                                    <div style="">
                                        <table class="ui-table ui-table-hover ui-table-striped" style="width:100%"><tbody>
                                        <tr class="ui-table-header">


                                            <th class="header_s" style="width:150px;" id="table_header_database">Опция</th>
                                            <th class="header_s" style="width:100px;" id="table_header_type">Цена</th>
                                            <th class="header_s" style="width:100px;" id="table_header_point_access">Цена подключения</th>
                                        </tr>

                                        <!--начало элемента таблицы-->
                                        <c:forEach var="optionIncompatible" items="${option.optionsIncompatible}">
                                            <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">

                                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>${optionIncompatible.name}</span><br></td>
                                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${optionIncompatible.price}</span></td>
                                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 100px;"><span>${optionIncompatible.initialPrice}</span></td>
                                            </tr>
                                        </c:forEach>

                                        <!--конец элемента таблицы-->
                                        </tbody>
                                        </table></div></div>


                                <div class="js-row control-group">
                                    <label class="js-caption control-label"></label>

                                </div><div class="js-row control-group">
                                <label class="js-caption control-label"></label>

                            </div></div>




                            </div>
                            </c:forEach>




            <div class="clear"></div>
            <div id="test"></div>
        </div>
    </td></tr>
</table>
</div>
</body>
</html>