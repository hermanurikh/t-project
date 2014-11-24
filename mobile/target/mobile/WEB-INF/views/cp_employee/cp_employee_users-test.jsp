<div class="ui-widget-overlay ui-front" id="overlay_new" style="display:none;"></div>

<!-- Начало вставляемого дива -->
<div id="new_user" class="ui-dialog ui-widget ui-widget-content ui-corner-all ui-front ui-draggable ui-resizable ui-widget-shadow" tabindex="-1" role="dialog" aria-describedby="ftp_user_dialog" aria-labelledby="ui-id-7" style="position: absolute; height: auto; width: 640px; top: 99.5px; left: 360px; display: none;">
    <div class="ui-dialog-titlebar ui-widget-header ui-corner-all ui-helper-clearfix"><span id="ui-id-7" class="ui-dialog-title">Новый пользователь</span>
        <button class="ui-button ui-widget ui-state-default ui-corner-all ui-button-icon-only ui-dialog-titlebar-close" role="button" onclick="showMenu()" aria-disabled="false" title=""><span class="ui-button-icon-primary ui-icon ui-icon-closethick"></span><span class="ui-button-text"></span>
        </button>
    </div>
    <div id="ftp_user_dialog" class="ui-dialog-content ui-widget-content" style="width: auto; min-height: 76px; max-height: none; height: auto;">
        <div class="form-horizontal npp_ftp-dialog_middle">

            <form action="cp_employee_create_user" id="jForm" method="POST" accept-charset="CP1252">
                <div class="js-body info__body">
                    <div>

                        <div style="display:none">
                            <input id="isContract" value=${userExists}>
                        </div>

                        <div class="js-row control-group" id="exceptions" style="display:none">
                            <c:forEach var="exception" items="${exList}">
                                <small>${exception.message}</small>
                            </c:forEach>

                        </div>
                    </div>



                    <div class="js-table form-horizontal support-issue-form">

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Имя:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" id="name" class="js-input big-input" name="name">
                                <span class="error-custom-message" id="error-custom-message-4">Имя не может быть нулевым.</span>
                            </div>

                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Фамилия:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" id="surname" class="js-input big-input" name="surname">
                                <span class="error-custom-message" id="error-custom-message-5">Фамилия не может быть нулевой.</span>
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Дата рождения (дд-мм-гггг):</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="date" id="date" class="js-input big-input" name="birthday">
                                <span class="error-custom-message" id="error-custom-message-6">Пожалуйста, введите дату в указанном формате не раньше 01.01.1900 и не позднее сегодняшнего дня.</span>
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Паспорт:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" class="js-input big-input" name="passport">
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Адрес:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" class="js-input big-input" name="address">
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">E-mail:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="email" class="js-input big-input" name="email">
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Начальный баланс:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" class="js-input big-input" name="balance">
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Логин:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="text" id="userLogin" class="js-input big-input" name="login">
                                <span class="error-custom-message" id="error-custom-message-7">Логин должен содержать от 2 до 15 знаков.</span>
                                <span class="error-custom-message" id="error-user-exists">Введенный логин уже существует!</span>
                            </div>
                        </div>

                        <div class="js-row control-group">
                            <label class="js-caption control-label">Пароль:</label>
                            <div class="js-td controls jq-validate-container">
                                <input type="password" id="userPassword" class="js-input big-input" name="password">
                                <span class="error-custom-message" id="error-custom-message-8">Пароль должен содержать от 6 до 20 знаков.</span>
                            </div>
                        </div>

                        <table class="ui-table ui-table-hover ui-table-striped" style="width:100%">
                            <tbody>
                            <tr class="ui-table-header">

                                <th class="header_s_checkbox" width="12" align="center">Роль:
                                    <!--<input id="main_checkbox" type="radio">-->
                                </th>
                                <th class="header_s" style="width:150px;" id="table_header_database"></th>
                            </tr>
                            <!--начало элемента таблицы-->
                            <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">
                                <td name="tcell" class="simplecell_checkbox" align="left">
                                    <input type="radio" id="cb" name="cb" value="1">
                                </td>
                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>Клиент</span>
                                    <br>
                                </td>
                            </tr>
                            <tr name="trow" class="ui-table-data-row ui-state-even ui-selected">
                                <td name="tcell" class="simplecell_checkbox" align="left">
                                    <input type="radio" name="cb" value="2">
                                </td>
                                <td class="simplecell" name="tcell" style="vertical-align: top; width: 150px"><span>Администратор</span>
                                    <br>
                                </td>
                            </tr>
                            <!--конец элемента таблицы-->
                            </tbody>
                        </table>

                        <span class="error-custom-message" id="error-custom-message-3">Пожалуйста, выберите одну из ролей.</span>


                        <div class="js-row control-group">
                            <label class="js-caption control-label"></label>

                        </div>
                        <div class="js-row control-group">
                            <label class="js-caption control-label"></label>
                            <div class="js-td controls jq-validate-container">
                                <input type="submit" id="send" value="Создать пользователя" />
                            </div>
                        </div>
                    </div>
                </div>
        </form>

    </div>
</div>
</div>