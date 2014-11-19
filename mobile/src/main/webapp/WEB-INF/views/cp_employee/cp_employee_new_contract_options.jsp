
<!DOCTYPE html>
<html>
<%@ page contentType="text/html;charset=utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page isELIgnored="false" %>
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=8,9,10" >
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>Панель управления аккаунтом.</title>
    <link rel="shortcut icon" href="/favicon.ico" type="image/x-icon"/>
    <script type="text/javascript" src="../../../scripts/jquery.js"></script>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file1.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file2.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file3.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file4.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file5.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file6.css"/>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/cp_file9.css" />
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/jquery.maskedinput.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/validate.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/numberMask.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/scripts/focus.js"></script>

</head>
<body class="locale-ru_RU">
<div class="lang-place" style="display:none;"><a href="?change_lang=ru">ru</a><a href="?change_lang=en">en</a><a href="?change_lang=de">de</a></div>


<script type="text/javascript">
    function redirect() {
    location.href = "cp_employee_main";
    }
</script>
<script type="text/javascript">
    jQuery(function($){
        $("#number").mask("(999) 999-99-99");
    });
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
                        <li><a href="cp_employee_profile">Профиль</a></li>
                        <!--<li class="last-child"><a href="mailto:herman.urikh@aengel.ru">Служба поддержки</a></li>-->
                    </ul>
                </div>
            </div>
            <div id="isValid" style="display:none">
                            <input id="areExceptions" value=${areExceptions}>

                        </div>
            <div class="right">
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



            <form action="cp_employee_contract_created" id="jForm" method="POST" accept-charset="CP1252">
                <div class="js-body info__body">
                    <h2 class="js-h">Создание нового контракта</h2><div>
                    <div class="js-table form-horizontal support-issue-form">

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Номер контракта (10 цифр):</label>
                            <div id="numberDiv" class="ui-field ui-ajaxfield ui-ajaxvalidate ui-corner-all"><!--ui-field отвечает за иконку, без него она уезжает-->
                                <input type="text" id="number" class="js-input big-input" name="number" value=${contractNumber}><!-- class="js-input big-input"-->
                                <div class="ui-ajaxvalidate-icon"></div>
                            </div>
                            <span class="error-custom-message" id="error-custom-message-1">В качестве номера необходимо использовать число из 10 цифр.</span>
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
                                                    <span class="error-custom-message-incorrect">${ex.message}</span>
                                                    </c:forEach>
                                                </div>
                                                </div>

                        <h2 class="js-h">Выберите опции для тарифа</h2><div>
                        <form:errors class="error-custom-message-incorrect" path="options"/>

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
                            <input type="submit" id="send" value="Создать контракт" />
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
