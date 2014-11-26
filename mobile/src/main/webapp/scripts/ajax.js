/**
 * Created by german on 25.11.14.
 */
function getOptions(id, tariff_name) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    var div = $("#bigOptionDiv");
    div.hide();
    setTimeout(doAjax(id, tariff_name), 1000);
    selectOptions();
}

function getOptionsForOption(id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    setTimeout(getOptionsTogether(id), 1000);
    setTimeout(getOptionsIncompatible(id), 1000);
    var div = $("#bigOptionDiv");
    div.show();
}



function doAjax(tariff_id, tariff_name) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    var tariffName = tariff_name;
    var tariffHeader = $("#optionsForTariff");
    tariffHeader.empty();
    tariffHeader.append("Доступные опции для тарифа " + tariffName);
    $.ajax({
        url: 'cp_get_options_for_tariff/' + tariff_id,
        type: 'GET',
        success: function(data) {
            var bigDiv1 = $("#list_database2");
            var bigDiv = $("#optionTable").find("tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span class='error-custom-message-incorrect'> К данному тарифу невозможно подключить опции.</span>");
            } else {
                bigDiv.append(
                        "<tr class='ui-table-header' id = 'options-header'>" +
                        "<th class='header_s_checkbox' width='12' align='center'></th>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                        "</tr>"
                );
                data.forEach(function(elem, index, array) {
                    bigDiv.append(
                            "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected' onclick=getOptionsForOption(" + elem.id + ")>" +
                            "<td name='tcell' class='simplecell_checkbox' align='left'>" +
                            "<input type='checkbox' name='cb3' id=" + elem.id + " value=" + elem.id + "></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                            "</tr>");
                });
            }
            bigDiv1.show();
            vds1.hide();
            vds2.hide();
        }
    });
}

function getOptionsTogether(id) {
    $.ajax({
        url: 'cp_get_optionsTogether_for_option/' + id,
        type: 'GET',
        success: function(data) {
            var bigDiv = $("#optionTogetherTable tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span> Для данной опции нет других обязательных к подключению опций.</span>");
            } else {
                bigDiv.append(
                        "<tr class='ui-table-header'>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                        "</tr>"
                );
                data.forEach(function(elem, index, array) {
                    bigDiv.append(
                            "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected'>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                            "</tr>");
                });

            }
        }
    });

}

function getOptionsIncompatible(id) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    $.ajax({
        url: 'cp_get_optionsIncompatible_for_option/' + id,
        type: 'GET',
        success: function(data) {
            var bigDiv = $("#optionIncompatibleTable tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span> Для данной опции нет других невозможных к подключению опций.</span>");
            } else {
                bigDiv.append(
                        "<tr class='ui-table-header'>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>Опция</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>Цена</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>Цена подключения</th>" +
                        "</tr>"
                );
                data.forEach(function(elem, index, array) {
                    bigDiv.append(
                            "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected'>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 150px'><span>" + elem.name + "</span><br></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.price + "</span></td>" +
                            "<td class='simplecell' name='tcell' style='vertical-align: top; width: 100px'><span>" + elem.initialPrice + "</span></td>" +
                            "</tr>");
                });
            }
            vds1.hide();
            vds2.hide();
        }
    });

}

function selectOptions() {
    $('#tariff-header').hide();
    $('#list_database').hide();
    $('#tariff-helper').hide();
    $('#backToTariffButton').show();
}

function backToTariff() {
    $('#tariff-header').show();
    $('#list_database').show();
    $('#tariff-helper').show();
    $('#backToTariffButton').hide();

}