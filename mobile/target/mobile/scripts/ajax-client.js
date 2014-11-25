/**
 * Created by german on 21.11.14.
 */

    function getOptions(id, tariff_name, JSP_POSSIBLE_OPTIONS_FOR_TARIFF, JSP_CONTRACTS_OPTION,
                        JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
                        JSP_NO_OPTIONS_FOR_TARIFF, JSP_NO_OPTIONS_TOGETHER, JSP_NO_OPTIONS_INCOMPATIBLE) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    var div = $("#bigOptionDiv");
    div.hide();
    setTimeout(doAjax(id, tariff_name, JSP_POSSIBLE_OPTIONS_FOR_TARIFF, JSP_CONTRACTS_OPTION,
        JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
        JSP_NO_OPTIONS_FOR_TARIFF, JSP_NO_OPTIONS_TOGETHER, JSP_NO_OPTIONS_INCOMPATIBLE), 1000);
    selectOptions();
}
function getOptionsForOption(id, JSP_CONTRACTS_OPTION, JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
                             JSP_NO_OPTIONS_TOGETHER, JSP_NO_OPTIONS_INCOMPATIBLE) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    vds1.show();
    vds2.show();
    setTimeout(getOptionsTogether(id, JSP_CONTRACTS_OPTION, JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
        JSP_NO_OPTIONS_TOGETHER), 1000);
    setTimeout(getOptionsIncompatible(id, JSP_CONTRACTS_OPTION, JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
        JSP_NO_OPTIONS_INCOMPATIBLE), 1000);
    var div = $("#bigOptionDiv");
    div.show();
}



function doAjax (tariff_id, tariff_name, JSP_POSSIBLE_OPTIONS_FOR_TARIFF, JSP_CONTRACTS_OPTION,
                 JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
                 JSP_NO_OPTIONS_FOR_TARIFF, JSP_NO_OPTIONS_TOGETHER, JSP_NO_OPTIONS_INCOMPATIBLE) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    var tariffName = tariff_name;
    var tariffHeader = $("#optionsForTariff");
    tariffHeader.empty();
    tariffHeader.append(JSP_POSSIBLE_OPTIONS_FOR_TARIFF + tariffName);
    $.ajax({
        url: 'cp_get_options_for_tariff/' + tariff_id,
        type: 'GET',
        success: function (data) {
            var bigDiv1 = $("#list_database2");
            var bigDiv = $("#optionTable tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span class='error-custom-message-incorrect'> " + JSP_NO_OPTIONS_FOR_TARIFF + "</span>");
            }
            else {
                bigDiv.append(
                        "<tr class='ui-table-header' id = 'options-header'>" +
                        "<th class='header_s_checkbox' width='12' align='center'></th>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>" + JSP_CONTRACTS_OPTION + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>" + JSP_CONTRACTS_OPTION_PRICE + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>" + JSP_CONTRACTS_OPTION_INITIAL_PRICE + "</th>" +
                        "</tr>"
                );
                data.forEach(function (elem, index, array) {
                    bigDiv.append(
                            "<tr name='trow' class='ui-table-data-row ui-state-even ui-selected' onclick=\"getOptionsForOption('" + elem.id + "', '" +
                                JSP_CONTRACTS_OPTION  + "', '" + JSP_CONTRACTS_OPTION_PRICE + "', '" + JSP_CONTRACTS_OPTION_INITIAL_PRICE +
                                "', '" + JSP_NO_OPTIONS_TOGETHER + "', '" + JSP_NO_OPTIONS_INCOMPATIBLE +  "');\">" +
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
function getOptionsTogether (id, JSP_CONTRACTS_OPTION, JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
                             JSP_NO_OPTIONS_TOGETHER) {
    $.ajax({
        url: 'cp_get_optionsTogether_for_option/' + id,
        type: 'GET',
        success: function (data) {
            var bigDiv = $("#optionTogetherTable tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span> " + JSP_NO_OPTIONS_TOGETHER + "</span>");
            }
            else {
                bigDiv.append(
                        "<tr class='ui-table-header'>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>" + JSP_CONTRACTS_OPTION + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>" + JSP_CONTRACTS_OPTION_PRICE + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>" + JSP_CONTRACTS_OPTION_INITIAL_PRICE + "</th>" +
                        "</tr>"
                );
                data.forEach(function (elem, index, array) {
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
function getOptionsIncompatible (id, JSP_CONTRACTS_OPTION, JSP_CONTRACTS_OPTION_PRICE, JSP_CONTRACTS_OPTION_INITIAL_PRICE,
                                 JSP_NO_OPTIONS_INCOMPATIBLE) {
    var vds1 = $("#vds-overlay");
    var vds2 = $("#vds-wait");
    $.ajax({
        url: 'cp_get_optionsIncompatible_for_option/' + id,
        type: 'GET',
        success: function (data) {
            var bigDiv = $("#optionIncompatibleTable tbody");
            bigDiv.empty();
            if (data == "") {
                bigDiv.append("<span> " + JSP_NO_OPTIONS_INCOMPATIBLE + "</span>");
            }
            else {
                bigDiv.append(
                        "<tr class='ui-table-header'>" +
                        "<th class='header_s' style='width:150px;' id='table_header_database'>" + JSP_CONTRACTS_OPTION + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_type'>" + JSP_CONTRACTS_OPTION_PRICE + "</th>" +
                        "<th class='header_s' style='width:100px;' id='table_header_point_access'>" + JSP_CONTRACTS_OPTION_INITIAL_PRICE + "</th>" +
                        "</tr>"
                );
                data.forEach(function (elem, index, array) {
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

